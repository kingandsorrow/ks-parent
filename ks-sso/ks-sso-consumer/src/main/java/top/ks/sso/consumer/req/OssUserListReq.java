package top.ks.sso.consumer.req;

import lombok.Getter;
import lombok.Setter;
import top.ks.common.util.PageRequestEntity;

@Getter
@Setter
public class OssUserListReq extends PageRequestEntity {

    private String nickName;

    private String orgId;


}
