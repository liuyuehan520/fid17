package com.fdi17.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtilsNew {

	// --------------AES---------------
	private static final String KEY = "8f265befb798986f"; // 密匙，必须16位
	private static final String IV = "0000000000000000";
	private static final String ENCODING = "UTF-8"; // 编码
	private static final String ALGORITHM = "AES"; // 算法
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"; // 默认的加密算法，ECB模式

	/**
	 * AES加密
	 * 
	 * @param plainText
	 * @return String
	 */
	public static String AESEncrypt(String plainText) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
		IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		byte[] textByte = cipher.doFinal(plainText.getBytes(ENCODING));
		// 采用base64算法进行转码,避免出现中文乱码
		return Base64.getEncoder().encodeToString(textByte);
	}

	/**
	 * AES解密
	 * 
	 * @param cipherText
	 * @return String
	 */
	public static String AESDecrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
		IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		byte[] textByte = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		// 采用base64算法进行转码,避免出现中文乱码
		return new String(textByte, ENCODING);
	}
}
