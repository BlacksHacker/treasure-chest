package com.shelton.treasure.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小¬
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static final String ALGORITHM = "RSA";

    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    public static PublicKey decodePublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64Check(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey decodePrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64Check(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 功能描述: RSA分段加密
     *
     * @param data
     * @param keyStr
     * @param isPublic
     * @return : byte[]
     * @author : xiaosheng1.li
     * @date : 2020/9/30 15:10
     **/
    public static String encryptBySection(String data, String keyStr, boolean isPublic) throws Exception {
        return Base64.encodeBase64String(executeBySection(decodeKey(keyStr, isPublic), Cipher.ENCRYPT_MODE, data.getBytes()));
    }

    /**
     * 功能描述: RSA分段解密
     *
     * @param data
     * @param keyStr
     * @param isPublic
     * @return : byte[]
     * @author : xiaosheng1.li
     * @date : 2020/9/30 15:10
     **/
    public static String decryptBySection(String data, String keyStr, boolean isPublic) throws Exception {
        return new String(executeBySection(decodeKey(keyStr, isPublic), Cipher.DECRYPT_MODE, Base64.decodeBase64(data)), "UTF-8");
    }

    /**
     * 功能描述: RSA加密
     *
     * @param data
     * @param keyStr
     * @param isPublic
     * @return : byte[]
     * @author : xiaosheng1.li
     * @date : 2020/9/30 15:11
     **/
    public static String encrypt(String data, String keyStr, boolean isPublic) throws Exception{
        return Base64.encodeBase64String(execute(decodeKey(keyStr, isPublic), Cipher.ENCRYPT_MODE, data.getBytes()));
    }

    /**
     * 功能描述: RSA解密
     *
     * @param data
     * @param keyStr
     * @param isPublic
     * @return : java.lang.String
     * @author : xiaosheng1.li
     * @date : 2020/9/30 16:54
     **/
    public static String decrypt(String data, String keyStr, boolean isPublic) throws Exception{
        return new String(execute(decodeKey(keyStr, isPublic), Cipher.DECRYPT_MODE, Base64.decodeBase64(data)), "UTF-8");
    }

    /**
     * 分段加解密核心逻辑
     */
    private static byte[] sectionDoFinal(byte[] bytes, Cipher cipher, int blockSize) throws IllegalBlockSizeException, BadPaddingException, IOException {

        int inputLen = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > blockSize) {
                cache = cipher.doFinal(bytes, offSet, blockSize);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * blockSize;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * base64检查
     */
    private static byte[] base64Check(String key){
        if (StringUtils.isBlank(key)){
            throw new IllegalArgumentException("empty key");
        }
        byte[] keyBytes = Base64.decodeBase64(key.getBytes());
        if (keyBytes == null || keyBytes.length == 0) {
            throw new IllegalArgumentException("key not encoded with base 64");
        }
        return keyBytes;
    }

    /**
     * 加解密处理
     */
    private static byte[] execute(Key key, int cipherMode, byte[] bytes) throws Exception{
        return initCipher(cipherMode, key).doFinal(bytes);
    }

    /**
     * 分段加解密处理
     */
    private static byte[] executeBySection(Key key, int cipherMode, byte[] bytes) throws Exception{
        if (Cipher.DECRYPT_MODE == cipherMode){
            return sectionDoFinal(bytes, initCipher(cipherMode, key), MAX_DECRYPT_BLOCK);
        }
        return sectionDoFinal(bytes, initCipher(cipherMode, key), MAX_ENCRYPT_BLOCK);
    }

    /**
     * 初始化
     */
    private static Cipher initCipher(int cipherMode, Key key) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM, DEFAULT_PROVIDER);
        cipher.init(cipherMode, key);
        return cipher;
    }

    /**
     * 公私钥Base64解码
     */
    private static Key decodeKey(String keyStr, boolean isPublic) throws Exception{
        Key key = null;
        if (isPublic){
            key = decodePublicKey(keyStr);
        }else {
            key = decodePrivateKey(keyStr);
        }
        return key;
    }
}

