package top.ks.file.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.file.consumer.FileUploadServiceI;
import top.ks.file.consumer.req.FileUploadReq;
import top.ks.file.consumer.resp.FileUploadRes;
import top.ks.file.provider.bootstrap.KsFileProvider;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KsFileProvider.class)
@ContextConfiguration
public class FileServiceITest {
    @Resource
    private FileUploadServiceI fileUploadServiceI;

    @Test
    public void testOrgList() throws Exception {
        FileUploadReq fileUploadReq = new FileUploadReq();
        fileUploadReq.setFileName("ks");
        fileUploadReq.setFileType(".jar");
        InputStream inputStream = new FileInputStream(new File("/Users/birongjun/Downloads/ks-base-provider-1.0.0-SNAPSHOT.jar"));
        FileUploadRes fileUploadRes = fileUploadServiceI.fileUploadStream(fileUploadReq, inputStream);
        System.out.println(JSON.toJSON(fileUploadRes));

    }
}
