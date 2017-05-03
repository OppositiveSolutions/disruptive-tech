package com.careerfocus.util;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class PasswordGenerator {

	public static String generatePassword() {
		String uuid = UUID.randomUUID().toString();
		// log.debug("uuid = " + uuid);
		uuid = uuid.replace("-", "");
		uuid = uuid.substring(0, 8);
		return uuid;
	}

	public static String generateNumberPassword() {
		return new BigInteger(UUID.randomUUID().toString().replaceAll("-", ""), 16).toString(36);
	}

	public static String generate32CharUniqueString() {
		String uuid = UUID.randomUUID().toString();
		// log.debug("uuid = " + uuid);
		uuid = uuid.replace("-", "");
		uuid = uuid.substring(0, 32);
		return uuid;
	}

	public static String generateSixDigitPassword() {
		Random random = new Random();
		int x = random.nextInt(900000) + 100000;
		return String.valueOf(x).replaceAll("0", String.valueOf(1 + random.nextInt(9)));
	}
	
	public static String generateLoginStringFromApp() {
		String uuid = UUID.randomUUID().toString();
		// log.debug("uuid = " + uuid);
		uuid = uuid.replace("-", "");
		uuid = uuid.substring(0, 20);
		return uuid;
	}

}