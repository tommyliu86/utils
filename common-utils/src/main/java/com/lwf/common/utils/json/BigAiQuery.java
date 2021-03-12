package com.lwf.common.utils.json;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-02-22 09:47
 */

public class BigAiQuery {
    private Map<String,String> categoryInfo;
    private List<String> cate;

    public Map<String, String> getCategoryInfo() {
        return categoryInfo;
    }

    public BigAiQuery setCategoryInfo(Map<String, String> categoryInfo) {
        this.categoryInfo = categoryInfo;
        return this;
    }

    public List<String> getCate() {
        return cate;
    }

    public BigAiQuery setCate(List<String> cate) {
        this.cate = cate;
        return this;
    }
}
