package ca.access.utils;

import java.security.MessageDigest;

/**
 * @author: Lei Fu
 * @date: 2023/05/19
 * @description: System user business implementation class
 */
public class Md5Util {
    public final static String MD5(String s){
        // Convert bytes to characters represented in hexadecimal
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F'};
        try{
            byte[] btInput = s.getBytes();
            // Get the object of MD5 digest algorithm
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // Update the digest with the specified bytes
            mdInst.update(btInput);
            // Complete the hash calculation and generate a 128-bit long integer
            byte[] md = mdInst.digest();
            // Convert ciphertext to hexadecimal string form
            int j = md.length;
            // For each byte in hexadecimal, use two characters
            char str[] = new char[j*2];
            // Indicates the corresponding character position in the conversion result
            int k = 0;
            // Starting from the first byte, convert each byte into a hexadecimal character conversion
            for(int i = 0;i<j;i++){
                // Get the first byte
                byte byte0 = md[i];
                // Take the upper 4 bits of the byte and convert it. logical shift right
                str[k++] = hexDigits[byte0>>>4 & 0xf];
                // Take the lower 4 digits of the byte to convert.
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){
        System.out.printf(Md5Util.MD5("123456"));
    }
}
