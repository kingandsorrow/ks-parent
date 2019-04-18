package top.ks.common.util;

import io.swagger.annotations.ApiModelProperty;

public class RequestEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 2138671690435913059L;
	@ApiModelProperty(value="消息头，主要由手机端web填写",hidden=true)
	protected HeaderEntity header;

	public RequestEntity() {

		this.header = new HeaderEntity();
	}

	public HeaderEntity getHeader() {
		return header;
	}

	public void setHeader(HeaderEntity header) {
		this.header = header;
	}

}
