package com.crack;

import java.util.List;

public class SxltNoticeInfo {

	private String tradeType;//支付类型：1 : 支付 2 : 退款
	private String outTradeNo;//外部交易流水号    交易类型为1时为支付流水，2时为退款流水
	private String tradeStatus;//交易状态  2 : 支付成功/退款成功     3 : 支付交易关闭/退款失败     4：需二次退款
	private String secondRefundUrl;//二次退款url当tradeStatus 为4 （需二次退款）有效，业务系统打开此链接完成二次退款
	private String resultDesc;//交易结果描述
	private List<SxltPayInfo> payInfo;//交易信息
	
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getSecondRefundUrl() {
		return secondRefundUrl;
	}
	public void setSecondRefundUrl(String secondRefundUrl) {
		this.secondRefundUrl = secondRefundUrl;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public List<SxltPayInfo> getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(List<SxltPayInfo> payInfo) {
		this.payInfo = payInfo;
	}
}
