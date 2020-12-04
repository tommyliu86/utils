package com.lwf.common.utils.spring;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-25 15:20
 */
public class traceDemo {
    @Slf4j
    @Aspect
    public class UmpMonitor {

        @Value("${jAppName}")
        private String appName;

        @Value("${profiles.active}")
        private String prefix;

        /**
         * Controller层
         */
        @Around(" execution( * *Controller.*(..))")
        public Object controller(ProceedingJoinPoint joinPoint) throws Throwable {

            InvokeTree.start(Thread.currentThread().getName(), joinPoint.getArgs());
            try {
                return process(joinPoint, "controller_",false);
            } finally {
                InvokeTree.exit();
                log.info(InvokeTree.getCurrentTree().toString());
                InvokeTree.clear();
            }
        }

        /**
         *
         */
        @Around(" execution( * *Service.*(..))")
        public Object serivce(ProceedingJoinPoint joinPoint) throws Throwable {
            return process(joinPoint, "service_",true);
        }

        /**
         *
         */
        @Around(" execution( * *Dao.*(..))")
        public Object dao(ProceedingJoinPoint joinPoint) throws Throwable {
            return process(joinPoint, "dao_",true);
        }
        @Pointcut("execution(public * *Service.*(..))")
        public void rpcClient(){}
        @Pointcut("execution(public * *ServiceImpl.*(..))")
        public void rpcService(){}

        /**
         * 对rpcclient和rpcsercie添加拦截
         * @param joinPoint
         * @return
         * @throws Throwable
         */
        @Around("rpcClient()")
        public Object rpc(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("rpc is start");
            LocalDateTime startTimd= LocalDateTime.now();
            InvokeTree.start(Thread.currentThread().getName(), joinPoint.getArgs());
            try {
                return process(joinPoint, "rpc_",false  );
            } finally {
                InvokeTree.exit();
                log.info(InvokeTree.getCurrentTree().toString());
                LocalDateTime endTime= LocalDateTime.now();
                log.info("rpc invoke total time is(mills) :"+ Duration.between(startTimd,endTime).toMillis());
                InvokeTree.clear();
            }
        }


        /**
         * 具体拦截处理方法
         */
        private Object process(ProceedingJoinPoint joinPoint, String preUmpKey,Boolean umpUp) throws Throwable {
            Object result = null;
            CallerInfo info = null;
            String methodName=null;
            if (umpUp){
                methodName = generateMethodNameByProceedingJoinPoint(joinPoint, prefix + "_", preUmpKey);
            }
            try {
                if (!org.springframework.util.StringUtils.isEmpty( methodName)){
                    info = Profiler.registerInfo(methodName, appName, false, true);
                }
                InvokeTree.enter(methodName, joinPoint.getArgs());
                result = joinPoint.proceed();
            } catch (Throwable e) {
                if (info!=null){
                    Profiler.functionError(info);
                }
                InvokeTree.error(e);
                log.error(methodName + "执行出错!", e);
                throw e;
            } finally {
                //必须执行
                if (info!=null){
                    Profiler.registerInfoEnd(info);
                }
                InvokeTree.exit();
            }
            return result;
        }


        private String generateMethodNameByProceedingJoinPoint(ProceedingJoinPoint joinPoint, String env, String preUmpKey) {
            String className;
            className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = new StringBuilder(preUmpKey).append(env).append(className).append(".").append(joinPoint.getSignature().getName()).toString();
            return methodName;
        }
    }

    /**
     * Created with IntelliJ IDEA.
     *
     * @author: liuwenfei14
     * @date: 2020-05-07 14:01
     */
    @Slf4j
    public static class InvokeTree {
        //线程调用tree
        static ThreadLocal<InvokeTree> localTree = new ThreadLocal<InvokeTree>();
        //根节点
        public InvokeNode rootNode;
        //当前线程正在执行的节点
        public InvokeNode curNode;

        public static InvokeTree getCurrentTree() {
            return localTree.get();
        }

        //调用链路节点
        public static class InvokeNode {
            public InvokeNode(int deep) {
                this.deep = deep;
            }

            //父调用节点
            public InvokeNode parentNode;
            //子调用节点
            public List<InvokeNode> childNodes;
            //当前节点调用方法
            public String invokeMethod;
            //调用的时间
            public LocalDateTime inTime;
            //调用的时间
            public LocalDateTime outTime;
            //深度
            public int deep;
            //本节点调用情况
            public String msg = "";

            public Object[] args;

            public Object result;
        }

        /**
         * 初始化方法
         * @param invokeMethod
         * @param args
         */
        private static void init(String invokeMethod, Object[] args) {
            InvokeTree tree = new InvokeTree();
            InvokeNode rootNode = new InvokeNode(0);
            rootNode.invokeMethod = invokeMethod;
            rootNode.args = args;
            rootNode.inTime =LocalDateTime.now();
            tree.rootNode = rootNode;
            tree.curNode = rootNode;
            localTree.set(tree);
        }

        public static void start(String invokeMethod, Object[] args){
            InvokeTree root = localTree.get();
            if (root!=null){
                enter(invokeMethod,args);
            }else {

                init(invokeMethod,args);
            }

        }
        public static void clear() {
            localTree.remove();
        }
        /**
         * 记录异常信息
         */
        public static void error(Throwable e) {
            try {

                InvokeTree tree = localTree.get();
                if (tree != null) {
                    String errorMsg = new StringBuilder("FAIL!=>").append(e).append(" at ").append(e.getStackTrace()[0].toString()).toString();
                    if (CollectionUtils.isEmpty(tree.curNode.childNodes)) {
                        tree.curNode.msg = errorMsg;
                    } else {
                        String argMsg = "";
                        if (tree.curNode.args != null) {
                            argMsg = ",入参:" + JSON.toJSONString(tree.curNode.args);
                        }
                        tree.curNode.msg = "此节点发生错误,错误信息请看调用链" + argMsg;
                        InvokeNode errorTree = new InvokeNode(tree.curNode.deep);
                        errorTree.invokeMethod = "调用终止!原因:";
                        errorTree.msg = errorMsg;
                        errorTree.inTime =LocalDateTime.now();
                        errorTree.outTime =LocalDateTime.now();
                        tree.curNode.parentNode.childNodes.add(errorTree);
                    }
                }
            }catch (Throwable throwable){
                log.error("UmpMonitor error",throwable);
            }
        }


        /**
         * 退出节点 进入节点和开始程序都需要调用一次
         */
        public static void exit() {
            try {
                InvokeTree tree = localTree.get();
                if (tree != null) {
                    if (StringUtils.isEmpty(tree.curNode.msg)) {
                        tree.curNode.msg = "SUCCESS";

                    }
                    tree.curNode.outTime = LocalDateTime.now();
                    //退出节点只要将当前节点指向父节点即可
                    tree.curNode = tree.curNode.parentNode;
                }
            } catch (Throwable throwable) {
                log.error("UmpMonitor error", throwable);

            }
        }

        /**
         * 进入节点 判断子节点是否有值 有则添加 无则退出
         */
        public static void enter(String invokeMethod, Object[] args) {
            try {
                InvokeTree tree = localTree.get();
                if (tree != null) {

                    InvokeNode parentNode = tree.curNode;

                    InvokeNode newNode = new InvokeNode(parentNode.deep + 1);
                    newNode.invokeMethod = invokeMethod;
                    newNode.args = args;
                    newNode.inTime = LocalDateTime.now();
                    newNode.parentNode = parentNode;


                    if (parentNode.childNodes == null) {
                        parentNode.childNodes = new ArrayList<InvokeNode>();
                    }
                    parentNode.childNodes.add(newNode);

                    tree.curNode = newNode;
                }
            } catch (Throwable throwable) {
                log.error("UmpMonitor error", throwable);

            }
        }



        @Override
        public String toString() {
            try {
                if (rootNode == null) {
                    rootNode = curNode;
                }
                if (rootNode == null) {
                    return "Empty Tree";
                } else {
                    StringBuilder sb = new StringBuilder("InvokeTree print:\n");
                    buildShow(rootNode, "....", sb, true);
                    return sb.toString();
                }
            } catch (Throwable throwable) {
                log.error("UmpMonitor error", throwable);
                return "UmpMonitor error";
            }
        }

        private void buildShow(InvokeNode node, String space, StringBuilder sb, boolean isParentLastNode) {
            try {

                if (node != null) {
                    sb.append(space);
                    if (node.parentNode != null) {
                        sb.append("|-");
                    }
                    sb.append(node.invokeMethod).append("=>RUN[" + node.msg + "]["+ Duration.between(node.inTime,node.outTime).toMillis()+"mills]\n");
                    if (node.deep <= 8) {
                        if (node.childNodes != null && node.childNodes.size() > 0) {
                            for (int i = 0; i < node.childNodes.size(); i++) {
                                InvokeNode chNode = node.childNodes.get(i);
                                buildShow(chNode, space + ((node.parentNode != null
                                                && isParentLastNode) ? "|..." : "...."),
                                        sb, (i != node.childNodes.size() - 1));
                            }
                        }
                    }
                }
            }catch (Throwable throwable){
                log.error("UmpMonitor error",throwable);
            }
        }

        public static void main(String[] args) {
            InvokeTree.start("test", args);
            InvokeTree.start("hello", args);
            InvokeTree.enter("invoke1", args);
            InvokeTree.enter("invokeSub1", args);
            InvokeTree.enter("invokeSub3", args);
            InvokeTree.enter("invokeSub4", args);
            InvokeTree.enter("invokeSub5", args);
            InvokeTree.exit();
            InvokeTree.exit();
            InvokeTree.error(new RuntimeException("There is something run error"));
            InvokeTree.exit();
            InvokeTree.exit();
            InvokeTree.enter("invokeSub2", args);
            InvokeTree.exit();
            InvokeTree.enter("invokeSub2", args);
            InvokeTree.exit();
            InvokeTree.exit();
            InvokeTree.enter("invoke2", args);
            InvokeTree.enter("invoke21", args);
            InvokeTree.error(new NullPointerException());
            InvokeTree.exit();
            InvokeTree.exit();
            InvokeTree.enter("invoke2", args);
            InvokeTree.enter("invoke21", args);
            InvokeTree.exit();
            InvokeTree.exit();
            InvokeTree.exit();
            InvokeTree.exit();
            System.out.println(InvokeTree.getCurrentTree().toString());
        }
    }


    private static class CallerInfo {
    }

    private static class Profiler {

        public static CallerInfo registerInfo(String methodName, String appName, boolean b, boolean b1) {
        return new CallerInfo();
        }

        public static void functionError(CallerInfo info) {
        }

        public static void registerInfoEnd(CallerInfo info) {

        }
    }

}
