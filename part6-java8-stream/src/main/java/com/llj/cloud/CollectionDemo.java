package com.llj.cloud;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PackageName:   com.llj.cloud
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/14 22:48
 * Updater:     by luolinjie
 * Update_Date: 2019/9/14
 * Update_Description: luolinjie 补充
 **/
public class CollectionDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("过滤空字段后的列表: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
