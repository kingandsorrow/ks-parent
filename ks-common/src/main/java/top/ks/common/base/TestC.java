package top.ks.common.base;

import cn.hutool.core.lang.Func;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestC {

    public static void main(String[] args) {
        List<List<String>> strs = new ArrayList<>();
        strs.add(new ArrayList<String>(Arrays.asList("1")));
        strs.add(new ArrayList<String>(Arrays.asList("2")));
        strs.add(new ArrayList<String>(Arrays.asList("3")));
        Stream<List<FuncOrg>> streams = strs.parallelStream().map(s -> {
            List<FuncOrg> funcOrgs = new ArrayList<FuncOrg>();
            funcOrgs.add(new FuncOrg("1" + s.get(0), "name" + s.get(0), "code" + s.get(0)));
            return funcOrgs;
        });
        List<Object> objects = streams.collect(Collectors.toList());
        List<FuncOrg> funcOrgs = new ArrayList<>();
        for (Object obj : objects) {
            List<FuncOrg> funcOrg = (List<FuncOrg>) obj;
            funcOrgs.addAll(funcOrg);
        }
        System.out.println(funcOrgs.size());
    }
}
