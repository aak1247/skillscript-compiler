package com.aak1247.com.aak1247.model;

import com.aak1247.com.aak1247.lexer.Token;
import sun.net.TelnetOutputStream;

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
    public Error(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
}
