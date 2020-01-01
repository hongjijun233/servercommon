package com.lianghongji.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA辅助类
 * 
 * @author deng.huaiyu
 *
 */
public class RSAUtils {
	

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

	private static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static byte[] encrypt(byte[] plaintext, String publicKey) throws Exception {
		PublicKey pubKey = getPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		
		int inputLen = plaintext.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(plaintext, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(plaintext, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
		//byte[] encrypted = cipher.doFinal(plaintext);
		return encryptedData;
	}
	
	/**
	 * RSA公共钥加密字符串
	 * 
	 * @param plaintext 普通字符串
	 * @param publicKey  base64 编码的公钥
	 * @return 加密字符串，经过base64加密
	 * @throws Exception
	 */
	public static String publicEncryptBase64(String plaintext, String publicKey) throws Exception {
		byte[] plaintextByte = plaintext.getBytes();
		return Base64.encodeBase64String(encrypt(plaintextByte, publicKey));
	}

	private static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	/**
	 * 
	 * @param encrypted
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] privateDecrypt(byte[] encrypted, String key) throws Exception {
		PrivateKey privateKey = getPrivateKey(key);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		int inputLen = encrypted.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encrypted, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encrypted, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
		//byte[] bt_original = cipher.doFinal(encrypted);
		return decryptedData;
	}
	
	/**
	 * 使用私钥解密加密内容
	 * 
	 * @param base64Encrpy 加密内容，使用base64编码
	 * @param privateKey 密钥,使用base64
	 * @return 普通文本
	 * @throws Exception
	 */
	public static String privateDecryptBase64(String base64Encrpy, String privateKey) throws Exception {
		byte[] dataBytes = Base64.decodeBase64(base64Encrpy);
		return new String(privateDecrypt(dataBytes, privateKey));
	}
	
	
}
