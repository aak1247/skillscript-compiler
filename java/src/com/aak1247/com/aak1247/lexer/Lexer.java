package com.aak1247.com.aak1247.lexer;

/**
 * @author aak12 on 2017/7/13.
 */
public class Lexer {
    private int line;
    private char peek;
    private int cur_pos;
    private String pri_code;
    public Lexer(String text) {
        this.pri_code = text;
        line = 1;
        cur_pos = 0;
    }

    private void getchar(){
        if (cur_pos < pri_code.length()){
            peek = pri_code.charAt(cur_pos);
        } else {
            peek = 0;
        }
    }

    private int get_line_num(){
        return this.line;
    }

    public Token next() {



        return null;
    }
}
