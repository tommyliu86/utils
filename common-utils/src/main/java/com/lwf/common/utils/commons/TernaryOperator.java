package com.lwf.common.utils.commons;

import com.alibaba.fastjson.JSON;
import com.lwf.common.utils.json.SkuDto;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-17 13:42
 */
public class TernaryOperator {
    public static void main(String[] args) {
        String json="{\n" +
                "    \"product_id\": \"1004705542\",\n" +
                "    \"shop_id\": \"30609\",\n" +
                "    \"on_shelves_time\": \"1466498616000\",\n" +
                "    \"product_name\": \"CHUKCHI楚克奇大型犬小型犬食盆碗宠物不锈钢碗猫碗狗狗碗宠物狗盆猫盆\",\n" +
                "    \"vender_name\": \"南通携创户外休闲用品有限公司\",\n" +
                "    \"brand_id\": \"5511\",\n" +
                "    \"sku_status\": \"1\",\n" +
                "    \"category_id1\": \"6994\",\n" +
                "    \"category_id\": \"7018\",\n" +
                "    \"category_id2\": \"6998\",\n" +
                "    \"off_shelves_time\": \"1387209631000\",\n" +
                "    \"shop_name\": \"携创宠物用品专营店\",\n" +
                "    \"main_sku_id\": \"1019362991\",\n" +
                "    \"img_dfs_url\": \"jfs/t1/163732/8/1207/136261/5ff52550Ed1cb5e40/1b72a0991ebd4a42.jpg\",\n" +
                "    \"sku_name\": \"CHUKCHI楚克奇大型犬小型犬食盆碗宠物不锈钢碗猫碗狗狗碗宠物狗盆猫盆 不锈钢 M-中型底座22CM\",\n" +
                "    \"shop_category\": \"1344679 1344683\",\n" +
                "    \"category_ext_id\": \"0\",\n" +
                "    \"yn\": \"1\",\n" +
                "    \"vender_id\": \"31804\"\n" +
                "}";
        SkuDto skuDto = JSON.parseObject(json,SkuDto.class);

        System.out.println(skuDto.getSkuStatus() == "1");
        System.out.println(skuDto.getSkuStatus().equals("1"));
        /**
         * 这里的结果将会是false  和true，原因仍然是老生常谈的，java的string缓存策略，对于"abc"这种写法的string，
         * 默认会写入string常量池进行缓存，再次使用时，string a="abc"这种写法会使得string指向相同常量池，
         * 在使用"=="号时，判断的是object的指针是否相等，如果不相等则直接返回false了，因此要用equal
         *
         */
        skuDto.setSkuStatus("1");
        System.out.println(skuDto.getSkuStatus() == "1");
        System.out.println(skuDto.getSkuStatus().equals("1"));
        /**
         * 这里的结果将会是true  和true，因为这时的skuStatus的两个指针都指向常量池中的同一个string串，这就导致
         * 看上去两则效果一样的假象，而实际上则是不一样的！
         *
         */
    }

    public static void ternary(String[] args) {
        //三元运算符的优先级最高，因此在每个子项目（元）中可以不加括号
        Integer max=100;
        Integer num=30;
        Integer i1= max-50>num?max-10:max-20-20;
        System.out.println(i1);
    }
}
