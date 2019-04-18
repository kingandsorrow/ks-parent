/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved.
 *
 * @project asura
 * @file JsonEntityTransform
 * @package com.asura.framework.base.util
 *
 * @date 2015/3/12 16:48
 */
package top.ks.common.util;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * Json与Entity互相转化
 * </P>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author SZL
 * @version 1.0
 * @since 1.0
 */
public class JsonEntityTransform {

	public static <T> T parseObject(String jsonString, Class<T> clazz) {

		return JSON.parseObject(jsonString, clazz);
	}

	public static String toJsonString(Object obj) {

		return JSON.toJSONString(obj);
	}

}
