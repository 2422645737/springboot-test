package com.shiyueoe.demo_07_29;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {
    private static Predicate<Result> isA = r -> r.getType().equals("A");
    private static Predicate<Result> isB = r -> r.getType().equals("B");
    private static Predicate<Result> isC = r -> r.getType().equals("C");
    private static Predicate<Result> isD = r -> r.getType().equals("D");

    //假设来了一个新的需求，入参为d时，需要匹配isA或者isD
    private static Map<String,Predicate<Result>> filterMap = UMap.of(
            "a",isA,
                         "b",isB,
                         "c",isC,
                         "d",isA.or(isD));

    public static void main(String[] args) {
    }
    private static List<Result> filter(){

        //假设入参中的type是a,b,c
        Request request = new Request();

        //假设返回的结果中的值是A,B,C，和入参不一致
        List<Result> resultList = new ArrayList<>();

        //根据入参获取过滤器
        Predicate<Result> condition = filterMap.getOrDefault(request.getType(),r -> true);

        return resultList.stream().filter(condition).collect(Collectors.toList());
    }
}
