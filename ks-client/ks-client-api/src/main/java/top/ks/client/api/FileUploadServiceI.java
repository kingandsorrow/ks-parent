package top.ks.client.api;

import top.ks.client.api.req.UploadImgReq;
import top.ks.client.api.resp.FilepathRes;

public interface FileUploadServiceI {

    FilepathRes imgStrUrlStream(UploadImgReq uploadImgReq) throws Exception;

}
