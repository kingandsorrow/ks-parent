package top.ks.common.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterTest {

    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        String csv1 = "/Users/birongjun/Downloads/iuap_cloud_basedoc_org_admin.csv";
        String csv2 = "/Users/birongjun/Downloads/tenant.csv";
        handleGetCsv(stringSet, csv1);
        handleGetCsv(stringSet, csv2);
        //指定路径和编码
        CsvWriter writer = CsvUtil.getWriter("/Users/birongjun/Downloads/tenantAll.csv", CharsetUtil.CHARSET_UTF_8);
        for (String s : stringSet) {
            writer.write(new String[]{s});
        }


    }

    private static void handleGetCsv(Set<String> stringSet, String csvPath) {
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(FileUtil.file(csvPath));
        List<CsvRow> rows = data.getRows();
        for (CsvRow csvRow : rows) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
            stringSet.add(csvRow.get(0).trim());
        }
    }
}
