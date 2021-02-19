package com.lwf.common.utils.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-01-21 17:15
 */
public class PatternTest {
    private final static Pattern PATTERN = Pattern.compile("^(.+)\\#\\{([0-9]*)\\}$");

    public static void main(String[] args) {
        List<String> stringList = resolveName("content_cms_msa#{600}");
        System.out.println(stringList);

    }
    private static List<String> resolveName(String name) {
        List<String> names = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(name);
        System.out.println(matcher);
        System.out.println(matcher.groupCount());
        if (matcher.matches()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                names.add(matcher.group(i));
            }
        } else {
            names.add(name);
        }
        return names;
    }
}
