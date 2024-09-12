package com.example.demo.util;

public class StringUtil {
    private StringUtil() { }   

    public static Boolean isNullOrEmpty(String s){
        if(s==null || s.isEmpty()){
            return true;
        }
        return false;
    }

    public static Boolean isNullOrWhiteSpace(String s){
        if(s==null || s.trim().isEmpty()){
            return true;
        }
        return false;
    }
}
