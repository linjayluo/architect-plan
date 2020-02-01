package com.llj.cloud.lambda123;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PackageName:   com.llj.cloud.lambda123
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/11/7 11:15
 * Updater:     by luolinjie
 * Update_Date: 2019/11/7
 * Update_Description: luolinjie 补充
 **/
public class LambdaDemo1 {
    public static void main(String[] args) {
        List<OnlineCntDO> onlineCntDOS = new ArrayList<>();
        onlineCntDOS.add(new OnlineCntDO("AC", 2, 1));
        onlineCntDOS.add(new OnlineCntDO("TV", 2, 32));
        onlineCntDOS.add(new OnlineCntDO("AC", 2, 23));
        onlineCntDOS.add(new OnlineCntDO("TV", 2, 4));
        onlineCntDOS.add(new OnlineCntDO("AC", 2, 5));
        onlineCntDOS.add(new OnlineCntDO("SLK", 2, 42));
        onlineCntDOS.add(new OnlineCntDO("AC", 2, 2));
        onlineCntDOS.add(new OnlineCntDO("SLK", 2, 35));
        onlineCntDOS.add(new OnlineCntDO("AC", 2, 5));
        onlineCntDOS.add(new OnlineCntDO("SLK", 2, 22));
        onlineCntDOS.add(new OnlineCntDO("AC", 2, 77));

        onlineCntDOS.add(new OnlineCntDO("AC", 3, 1));
        onlineCntDOS.add(new OnlineCntDO("TV", 3, 33));
        onlineCntDOS.add(new OnlineCntDO("AC", 3, 23));
        onlineCntDOS.add(new OnlineCntDO("TV", 3, 4));
        onlineCntDOS.add(new OnlineCntDO("AC", 3, 5));
        onlineCntDOS.add(new OnlineCntDO("SLK", 3, 43));
        onlineCntDOS.add(new OnlineCntDO("AC", 3, 2));
        onlineCntDOS.add(new OnlineCntDO("SLK", 3, 35));
        onlineCntDOS.add(new OnlineCntDO("AC", 3, 5));
        onlineCntDOS.add(new OnlineCntDO("SLK", 3, 22));
        onlineCntDOS.add(new OnlineCntDO("AC", 3, 78));

        Map<String, Map<Integer, List<OnlineCntDO>>> collect = onlineCntDOS.stream()
                .collect(Collectors.groupingBy(OnlineCntDO::getCategory, Collectors.groupingBy(OnlineCntDO::getPlatform)));
        collect.forEach((k, v) -> v.forEach((k1, v1) -> System.out.println(k + "-" + k1 + "--->" + v1.stream().mapToInt(OnlineCntDO::getValue).max().getAsInt())));
    }
}
