package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class MSG implements Serializable {
    @JSONField(
            name = "CHANNEL_ID"
    )
    private String channel_id;
    @JSONField(
            name = "CHANNEL_TYPE"
    )
    private String channel_type;
    @JSONField(
            name = "OPERATOR_ID"
    )
    private String operator_id;
    @JSONField(
            name = "OP_CITY"
    )
    private String op_city;
    @JSONField(
            name = "OP_DISTRICT"
    )
    private String op_district;
    @JSONField(
            name = "OP_PROVINCE"
    )
    private String op_province;
    @JSONField(
            name = "SERIAL_NUMBER"
    )
    private String serial_number;
    @JSONField(
            name = "PROVINCE"
    )
    private String province;
    @JSONField(
            name = "CITY"
    )
    private String city;
    @JSONField(
            name = "NUM_TYPE"
    )
    private String num_type;
    @JSONField(
            name = "AREA_CODE"
    )
    private String area_code;
    @JSONField(
            name = "PARA"
    )
    private para para;

    public MSG() {
    }

    public String getChannel_id() {
        return this.channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_type() {
        return this.channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public String getOperator_id() {
        return this.operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getOp_city() {
        return this.op_city;
    }

    public void setOp_city(String op_city) {
        this.op_city = op_city;
    }

    public String getOp_district() {
        return this.op_district;
    }

    public void setOp_district(String op_district) {
        this.op_district = op_district;
    }

    public String getOp_province() {
        return this.op_province;
    }

    public void setOp_province(String op_province) {
        this.op_province = op_province;
    }

    public String getSerial_number() {
        return this.serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNum_type() {
        return this.num_type;
    }

    public void setNum_type(String num_type) {
        this.num_type = num_type;
    }

    public String getArea_code() {
        return this.area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public para getPara() {
        return this.para;
    }

    public void setPara(para para) {
        this.para = para;
    }
}

