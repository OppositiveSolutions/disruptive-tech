package com.careerfocus.util;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class JPACryptoConverter implements AttributeConverter<String, String> {

	static Logger log = LoggerFactory.getLogger(JPACryptoConverter.class);

	String key = "MySuperSecretKey";

	@Override
	public String convertToDatabaseColumn(String entityValue) {
		try {
			byte[] keyBytes = Arrays.copyOf(key.getBytes("ASCII"), 16);

			SecretKey key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] cleartext = entityValue.getBytes("UTF-8");
			byte[] ciphertextBytes = cipher.doFinal(cleartext);

			return new String(Hex.encodeHex(ciphertextBytes));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbValue) {
		try {
			byte[] keyBytes = Arrays.copyOf(key.getBytes("ASCII"), 16);

			SecretKey key = new SecretKeySpec(keyBytes, "AES");
			Cipher decipher = Cipher.getInstance("AES");
			decipher.init(Cipher.DECRYPT_MODE, key);

			char[] cleartext = dbValue.toCharArray();
			byte[] decodeHex = Hex.decodeHex(cleartext);
			byte[] ciphertextBytes = decipher.doFinal(decodeHex);

			return new String(ciphertextBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}