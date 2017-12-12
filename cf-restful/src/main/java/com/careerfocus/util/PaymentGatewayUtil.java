package com.careerfocus.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PaymentGatewayUtil {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static void main(String args[]) {
		System.out.println("TransactionID = " + randomAlphaNumeric(27));
		String value = "key|txnid|amount|productinfo|firstname|email||||||salt";
		try {
			System.out.println("sha = " + generateHash(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String generateHash(String input) throws Exception {
		MessageDigest objSHA = MessageDigest.getInstance("SHA-512");
		byte[] bytSHA = objSHA.digest(input.getBytes());
		BigInteger intNumber = new BigInteger(1, bytSHA);
		String strHashCode = intNumber.toString(16);

		// pad with 0 if the hexa digits are less then 128.
		while (strHashCode.length() < 128) {
			strHashCode = "0" + strHashCode;
		}
		return strHashCode;
	}
}
