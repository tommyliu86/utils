package com.lwf.common.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-03-04 15:05
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class newSkuCommentInfo {

        private String averageScore;//平均分
        private String commentCount;//评论数
        private String generalCount;
        private String generalRate;
        private String generalRateShow;
        private String goodCount;//好货数，需求用到
        private String goodRate;//好货率，需求用到
        private String goodRateShow;
        private String mainSkuId;
        private String poorCount;
        private String poorRate;
        private String poorRateShow;
        private String score1;
        private String score2;
        private String score3;
        private String score4;
        private String score5;
        private String showCount;
        private String skuId;
}
