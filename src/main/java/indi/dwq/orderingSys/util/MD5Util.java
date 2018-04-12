package indi.dwq.orderingSys.util;


import java.security.MessageDigest;

/**
 * @author 董文强
 * @Time 2018/4/8 13:45
 */
public class MD5Util {


    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {

        String MD5_String;
        MD5_String = MD5("123");
        System.out.println(MD5_String);
        MD5_String = MD5("dongwenqiang");
        System.out.println(MD5_String);
        MD5_String = MD5("dong19951018");
        System.out.println(MD5_String);
    }

}
