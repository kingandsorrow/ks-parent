package top.ks.yonyou.db.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.db.bootstrap.KsYonyouDb;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouDb.class)
public class TestFile {
    @Test
    public void testFile1() {
        try {


            FileReader fileReader = new FileReader("/Users/birongjun/work/yon-work/dev-script/change_all.txt");
            List<String> stringList = fileReader.readLines();
            for (String line : stringList) {
                FileUtil.copy(line, "/Users/birongjun/Downloads/nanhang/", true);
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }
}
