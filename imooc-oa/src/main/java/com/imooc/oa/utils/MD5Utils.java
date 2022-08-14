package com.imooc.oa.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public static String md5Digest(String source){
        return DigestUtils.md5Hex(source);
    }

    public static String md5Digest(String source, Integer salt) {
        char[] chars = source.toCharArray();
        for(int i = 0; i < chars.length; i++){
            chars[i] += salt;
        }
        String target = new String(chars);
        System.out.println(target);
        return md5Digest(target);
    }

    public static void main(String[] args) {

        System.out.println(md5Digest("test", 188));
    }
}
