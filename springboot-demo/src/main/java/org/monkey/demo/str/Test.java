package org.monkey.demo.str;

/**
 * Test
 *
 * @author cc
 * @since 2024/7/9 16:51
 */
public class Test {
    public static void main(String[] args) {
        String line1 = "1111111111111111111111111111111111111111111111111111111111";
        String line2 = "2222222222222222222222222222222222222222222222222222222222";
        String tmpStr = line1 + " " + line2;
        if (tmpStr.length() <= 30) {
            System.out.println(tmpStr);
        } else {
            System.out.println(tmpStr.substring(0, 30));
            System.out.println(tmpStr.substring(31));
        }

        line1 = "1111111111111111111111"; //111111111111111111111111111111
        line2 = "222";
        tmpStr = line1 + " " + line2;
        if (tmpStr.length() <= 30) {
            System.out.println(tmpStr);
        } else {
            System.out.println(tmpStr.substring(0, 30));
            System.out.println(tmpStr.substring(31));
        }
    }
}
