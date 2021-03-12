package com.lwf.common.utils.json;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-02-08 13:46
 */
@Data
@Accessors(chain = true)
public class BigAiSkuDto {
    /**
     * skuId
     */
    private String skuId;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 商品好评率
     */
    private String praiseRate;
    /**
     * 商品好评数
     */
    private String praiseCount;
    /**
     * 店铺dsr评分
     */
    private String shopScore;
    /**
     * title	场景标签

     */
    private String title;
    /**
     *  tags	标签（旧标签）
     */
    private String tags;
    /**
     * brandId  品牌id
     */
    private String brandId;
    /**
     * brandName	品牌名称
     */
    private String brandName;
    /**
     * transDisputeTyRate	 30天交易纠纷因子排名率
     */
    private String transDisputeTyRate;
    /**
     * benchMarkRate
     */
    private String benchMarkRate;
    /**
     * wlCommAmount
     */
    private String wlCommAmount;
    /**
     * wlCommShare
     */
    private String wlCommShare;
    /**
     *
     *
     */
    private String jdPrc;
    /**
     * skuTag	 sku标签，多个以逗号隔开（包含通用标签，女装商品属性标签，女装场景标签）
     */
    private String skuTag;
}
