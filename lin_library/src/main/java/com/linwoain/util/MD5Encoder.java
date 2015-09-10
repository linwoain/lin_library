package com.linwoain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 此类描述的是：   MD5加密的相关操作
 *
 * @author: linwoain
 * @version: 2014年5月19日 上午9:52:12
 */
public class MD5Encoder {

    /**
     * 此方法描述的是：md5加密一次
     *
     * @param str 要加密的内容
     * @return 加密后的内容
     */
    public static String encode(String str) {
        try {


            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte aByte : bytes) {
                String s = Integer.toHexString(0xff & aByte);
                if (s.length() == 1) {
                    sb.append("0" + s);
                } else {
                    sb.append(s);
                }
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("do not find md5 algorithm ??? surprised it");
        }
    }

    /**
     * 此方法描述的是：   md5二次加密
     *
     * @param str 要加密的内容
     * @return 加密后的内容
     */
    public static String encode2(String str) {
        return encode(encode(str));
    }
}
