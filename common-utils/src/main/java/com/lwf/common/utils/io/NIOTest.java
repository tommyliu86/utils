package com.lwf.common.utils.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-03-09 22:35
 */
public class NIOTest {
    public static void main(String[] args) {
        /**
         * 所有的buffer都没有提供public的class，一般通过allocate 和allocateDirect方法来进行创建buffer对象，
         * 所有的buffer都是提供了抽象类来作为暴露api使用
         */

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1);
        new NioServer(8080).start();

    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static class NioServer {

        private int port;
        private Selector selector;
        private ExecutorService service = Executors.newFixedThreadPool(1);

        public NioServer(int port) {
            this.port = port;
        }

        private void init() {
            ServerSocketChannel ssc = null;
            try {
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress(port));
                selector = Selector.open();
                ssc.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("NioServer started ......");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }

        private void accept(SelectionKey key) {
            try {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
                System.out.println("accept a client : " + sc.socket().getInetAddress().getHostName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void start() {
            this.init();
            while (true) {
                try {
                    int events = selector.select();
                    if (events > 0) {
                        System.out.println("selector events count is " + events);
                        /**
                         * 1.获取到selector上的key之后遍历进行操作，这里为什么要移除？
                         * 2.多线程处理后续的数据
                         */
                        Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                        while (selectionKeys.hasNext()) {
                            SelectionKey key = selectionKeys.next();

                            try {

                                System.out.println("current key is :" + objectMapper.writeValueAsString(key));
                            }catch (Exception e){
                                System.out.println("current key is cancelled");
                                continue;
                            }

                            selectionKeys.remove();
                            if (key.isAcceptable()) {
                                accept(key);
                            } else {
                                service.submit(new NioServerHandler(key));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static class NioServerHandler implements Runnable {

            private SelectionKey selectionKey;

            public NioServerHandler(SelectionKey selectionKey) {
                this.selectionKey = selectionKey;
            }

            @Override
            public void run() {
                try {
                    /**
                     * 当selector处收到selectionKey就绪后的处理
                     */
                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.read(buffer);
                        buffer.flip();
                        System.out.println("收到客户端" + socketChannel.socket().getInetAddress().getHostName() + "的数据：" + new String(buffer.array()));
                        //将数据添加到key中
                        ByteBuffer outBuffer = ByteBuffer.wrap(buffer.array());
                        socketChannel.write(outBuffer);// 将消息回送给客户端
                        selectionKey.cancel();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
