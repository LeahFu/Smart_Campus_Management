package ca.access.utils;

/**
 * @author: Lei Fu
 * @date: 2023/07/05
 * @description:  ca.access.util
 */
public class AccessUtil {

    /**
     * Randomly generate six digits
     * @return
     */
    public  static int randomSixNums(){
        int code = (int) ((Math.random()*9+1)*100000);
        return code;
    }

    public static void main(String[] args) {
        System.out.println(AccessUtil.randomSixNums());
    }
}
