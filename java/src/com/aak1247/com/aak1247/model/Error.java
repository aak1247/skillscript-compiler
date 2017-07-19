package com.aak1247.com.aak1247.model;

/**
 * @author aak12 on 2017/7/16.
 */
public class Error {
    private String message;
    private int line;
    private String expected = null;
    private String got = null;
    public Error(String message, int line, String expected, String got) {
        this.message = message;
        this.line = line;
        this.expected = expected;
        this.got = got;
    }
    public Error(String message, int line) {
        this.message = message;
        this.line = line;
    }
    public void consume(){
        System.out.println("语法错误：在第" + line + "行");
        if (expected == null && got == null){
            return;
        }
        System.out.println(",应为" + expected +"但发现"+ got);
    }
}
