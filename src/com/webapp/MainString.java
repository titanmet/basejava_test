package com.webapp;

public class MainString {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
        StringBuilder result = new StringBuilder();
        for (String str : strArray) {
            result.append(str).append(", ");
        }
        System.out.println(result);

        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1 == str2);
    }
}
