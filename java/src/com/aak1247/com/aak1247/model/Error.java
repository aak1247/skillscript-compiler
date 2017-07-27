package com.aak1247.com.aak1247.model;

import com.aak1247.com.aak1247.lexer.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author aak12 on 2017/7/16.
 */
public class Error extends Throwable {
    private int code;
    private Token token;

    public Error(String message, int code, Token token) {
        super(message);
        this.code = code;
        this.token = token;
    }

    public Error(String message, int code) {
        super(message);
        this.code = code;
    }

    public Error(String message) {
        super(message);
    }

    public Error(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void comsume(){
        switch (code){
            case 0:
                System.out.println("语法分析和语义分析完成");
                break;
            case 1:
                System.out.println("语义错误：在第"+ token.getLine() + "行，" +token.getCharactor() + " 未定义");
                break;
            case 2:
                System.out.println("语法错误：在第" + token.getLine() +"行，这里应当为"+ getMessage() +"但发现" + token.getCharactor());
                break;
            case 3:
                System.out.println("语义错误：在第" + token.getLine() +"行，不能将" + getMessage().split("to")[0] + "转为" + getMessage().split("to")[1] );
                break;
        }
    }
}
