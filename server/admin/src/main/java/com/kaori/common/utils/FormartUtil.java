package com.kaori.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * @ClassName FormartUtil
 * @Description 数字格式化工具类 例如FormartUtil.formartNumber("000000000", 123) = 000000123
 * @author hele
 * @date 2019年08月14日
 */
public class FormartUtil {
	/**
	 * 数字前补0转成字符串
	 * @param place
	 * @param param
	 * @return
	 */
	public static String addPreZero(int place, String param) {
		// 格式化后的数据
		String formartData = "";

		String formart = "";

		for (int i = 0; i < place; i++) {
			formart = "0" + formart;
		}

		formartData = formartNumber(formart, Long.parseLong(param));

		return formartData;

	}

	/**
	 * 格式化数字
	 * @param formart
	 * @param param
	 * @return
	 */
	public static String formartNumber(String formart, long param) {
		// 格式化后的数据
		String formartData = "";
		try {
			DecimalFormat df = new DecimalFormat(formart);
			formartData = df.format(param);
		} catch (Exception e) {

		}

		return formartData;

	}

	/**
	 * 格式化数字
	 * @param formart
	 * @param param
	 * @return
	 */
	public static String formartDouble(String formart, double param) {
		// 格式化后的数据
		String formartData = "";
		try {
			DecimalFormat df = new DecimalFormat(formart);
			formartData = df.format(param);
		} catch (Exception e) {

		}

		return formartData;

	}



	/** 金额为分的格式 */
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

	/**
	 * 将分为单位的转换为元并返回金额格式的字符串 （除100）
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(Long amount) throws Exception {
		if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}

		int flag = 0;
		String amString = amount.toString();
		if (amString.charAt(0) == '-') {
			flag = 1;
			amString = amString.substring(1);
		}
		StringBuffer result = new StringBuffer();
		if (amString.length() == 1) {
			result.append("0.0").append(amString);
		} else if (amString.length() == 2) {
			result.append("0.").append(amString);
		} else {
			String intString = amString.substring(0, amString.length() - 2);
			for (int i = 1; i <= intString.length(); i++) {
				if ((i - 1) % 3 == 0 && i != 1) {
					result.append(",");
				}
				result.append(intString.substring(intString.length() - i,
						intString.length() - i + 1));
			}
			result.reverse().append(".")
					.append(amString.substring(amString.length() - 2));
		}
		if (flag == 1) {
			return "-" + result.toString();
		} else {
			return result.toString();
		}
	}

	/**
	 * 将分为单位的转换为元 （除100）
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(String amount) throws Exception {
		if (!amount.matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}
		return BigDecimal.valueOf(Long.valueOf(amount))
				.divide(new BigDecimal(100)).toString();
	}

	/**
	 * 将元为单位的转换为分 （乘100）
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(Long amount) {
		return BigDecimal.valueOf(amount).multiply(new BigDecimal(100))
				.toString();
	}

	/**
	 * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(String amount) {
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}

	public static double round(double amount) {
		//return Math.round(amount * 100) / 100.00d;
		return new  BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	
}
