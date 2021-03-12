package com.lwf.common.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-03-04 11:06
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuDto {
    /**
     * 商品编号（POP_PID）不等于skuId
     */
    @JsonProperty("product_id")
    private String productId;
    /**
     * 店铺编号
     */
    @JsonProperty("shop_id")
    private String shopId;
    /**
     * 上柜时间
     */
    @JsonProperty("on_shelves_time")
    private String onShelvesTime;
    /**
     * 品牌名
     */
    @JsonProperty("product_name")
    private String productName;
    /**
     * 商家名称
     */
    @JsonProperty("vender_name")
    private String venderName;
    /**
     * 新品牌id字段
     */
    @JsonProperty("brand_id")
    private String brandId;
    /**
     * 上下架状态。1：上架(可搜索，可购买)
     */
    @JsonProperty("sku_status")
    private String skuStatus;
    /**
     * 一级类目
     */
    @JsonProperty("category_id1")
    private String categoryId1;
    /**
     * 三级类目
     */
    @JsonProperty("category_id")
    private String categoryId3;
    /**
     * 二级类目
     */
    @JsonProperty("category_id2")
    private String categoryId2;
    /**
     * 下柜时间
     */
    @JsonProperty("off_shelves_time")
    private String offShelvesTime;
    /**
     * 店铺名
     */
    @JsonProperty("shop_name")
    private String shopName;
    /**
     * 商品编号(自营主skuId-ERP_PID)
     */
    @JsonProperty("main_sku_id")
    private String mainSkuId;
    /**
     * 商品主图。相对地址
     */
    @JsonProperty("img_dfs_url")
    private String img;
    /**
     * 商品名称
     */
    @JsonProperty("sku_name")
    private String skuName;
    /**
     * 店内分类
     */
    @JsonProperty("shop_category")
    private String shopCategory;
    /**
     * 商品的第二分类
     */
    @JsonProperty("category_ext_id")
    private String categoryExtId;
    /**
     * 是否有效。（0：无效，1：有效 )
     */
    @JsonProperty("yn")
    private String validStatus;
    /**
     * 商家编号
     */
//    @JsonProperty("vender_id")
    private String venderId;
}
