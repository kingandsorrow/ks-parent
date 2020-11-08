package top.ks.common.util;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class FilteLocaleUtil {

    public static void main(String[] args) {
        FileReader fileReader = new FileReader("/Users/birongjun/Downloads/org_tenant_conf1.txt");
        FileWriter writer = new FileWriter("/Users/birongjun/Downloads/org_tenant_conf2.txt");
        List<String> list = fileReader.readLines();
        List<String> list2 = new ArrayList<>();
        for (String str : list) {
            String str2 = "'" + str + "',";
            list2.add(str2);
        }
        writer.writeLines(list2);

    }
}
