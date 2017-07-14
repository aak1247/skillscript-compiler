package com.aak1247.com.aak1247.lexer;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author aak12 on 2017/7/13.
 * 在词法分析前，先将关键字加入符号表
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

    //token 的标记，指定当前识别到的单词符号的种别
    public final static char ID = 0,//标识符
    //常量
            CONST_INT = 1,
            CONST_CHAR = 2 ,
            CONST_FLOAT = 3,
            CONST_STRING = 4,
            CONST_TRUE = 5,
            CONST_FALSE = 6,
            CONST_NULL = 7,

    //关键字
            MAIN = 8,
            RETURN = 9,
            IF = 10,
            ELSE = 11,
            THEN = 12,
            DO = 13,
            WHILE =14,
            BREAK = 15,
            INT = 16,
            FLOAT =17,
            CHAR = 19,
            STRING = 20,
            BOOL = 21,
    //运算符
            ASSIGN = 22,
            ASK = 23,
            OR = 24,
            AND = 25,
            GT = 26,
            GET = 27,
            LT = 28,
            LET = 29,
            EQ = 30,
            NEQ = 31,
            ADD = 32,
            SUB = 33,
            MULT = 34,
            DIV = 35,
            NOT = 36,
            INC = 37,
            DEC = 38,
    //界符
            LRB = 39,//(
            RRB = 40,//)
            LCB = 41,//{
            RCB = 42,//}
            LSB = 43,//[
            RSB = 44,//]
            DELI = 45,//;
            SQ = 47,//'
            DQ = 48,//"
            COL = 49;//:

    //运算符
    public final static Token
            ASSIGN_TOKEN = new Token(ASSIGN, ":="),
            ASK_TOKEN = new Token(ASK, "?"),
            OR_TOKEN = new Token(OR, "∨"),
            AND_TOKEN = new Token(AND,"∧"),
            GT_TOKEN = new Token(GT, ">"),
            GET_TOKEN = new Token(GET, "≥"),
            LT_TOKEN = new Token(LT,"<"),
            LET_TOKEN = new Token(LET,"≤"),
            EQ_TOKEN = new Token(EQ,"=="),
            NEQ_TOKEN = new Token(NEQ,"≠"),
            ADD_TOKEN = new Token(ADD,"+"),
            SUB_TOKEN = new Token(SUB,"-"),
            MULT_TOKEN = new Token(MULT,"×"),
            DIV_TOKEN = new Token(DIV,"/"),
            NOT_TOKEN = new Token(NOT,"┐"),
            INC_TOKEN = new Token(INC,"++"),
            DEC_TOKEN = new Token(DEC,"--");
    public final static Token
            LRB_TOKEN = new Token(LRB,"("),
            RRB_TOKEN = new Token(RRB,")"),
            LCB_TOKEN = new Token(LCB,"{"),
            RCB_TOKEN = new Token(RCB,"}"),
            LSB_TOKEN = new Token(LSB,"["),
            RSB_TOKEN = new Token(RSB,"]"),
            DELI_TOKEN = new Token(DELI,";"),
            SQ_TOKEN = new Token(SQ,"'"),
            DQ_TOKEN = new Token(DQ,"\""),
            COL_TOKEN = new Token(COL,":");

    //关键字
    public final static Token
        MAIN_TOKEN = new Token(MAIN,"main"),
        RETURN_TOKEN = new Token(RETURN,"return"),
        IF_TOKEN = new Token(IF,"if"),
        ELSE_TOKEN = new Token(ELSE,"else"),
        THEN_TOKEN = new Token(THEN,"then"),
        DO_TOKEN = new Token(DO, "do"),
        WHILE_TOKEN = new Token(WHILE,"while"),
        BREAK_TOKEN = new Token(BREAK,"break"),
        INT_TOKEN = new Token(INT,"int"),
        FLOAT_TOKEN = new Token(FLOAT, "float"),
        CHAR_TOKEN = new Token(CHAR,"char"),
        BOOL_TOKEN = new Token(BOOL,"bool"),
        STRING_TOKEN = new Token(STRING,"String");
    //常量
    public final static Token
            CONST_INT_TOKEN = new Token(CONST_INT),
            CONST_FLOAT_TOKEN = new Token(CONST_FLOAT),
            CONST_CHAR_TOKEN = new Token(CONST_CHAR),
            COSNT_STRING_TOKEN = new Token(CONST_STRING),
            COSNT_TRUE_TOKEN = new Token(CONST_TRUE),
            CONST_FALSE_TOKEN = new Token(CONST_FALSE),
            COSNT_NULL_TOKEN = new Token(CONST_NULL);

    public final static Token[] keywords = {
            MAIN_TOKEN,
            RETURN_TOKEN ,
            IF_TOKEN ,
            ELSE_TOKEN ,
            THEN_TOKEN ,
            DO_TOKEN ,
            WHILE_TOKEN ,
            BREAK_TOKEN ,
            INT_TOKEN ,
            FLOAT_TOKEN ,
            CHAR_TOKEN ,
            BOOL_TOKEN ,
            STRING_TOKEN
    };
    public final static Token[] operators = {
            ASSIGN_TOKEN,ASK_TOKEN,OR_TOKEN,
            AND_TOKEN,GT_TOKEN,GET_TOKEN,
            EQ_TOKEN,NEQ_TOKEN,LT_TOKEN,
            LET_TOKEN,ADD_TOKEN,SUB_TOKEN,
            MULT_TOKEN,DIV_TOKEN,NOT_TOKEN,
            INC_TOKEN,DEC_TOKEN
    };
    public final static Token[] delitimers = {
            LRB_TOKEN ,
            RRB_TOKEN ,
            LCB_TOKEN ,
            RCB_TOKEN ,
            LSB_TOKEN ,
            RSB_TOKEN ,
            DELI_TOKEN ,
            SQ_TOKEN ,
            DQ_TOKEN ,
            COL_TOKEN ,
    };

    public final static Token[] constants = {
            CONST_INT_TOKEN ,
            CONST_FLOAT_TOKEN ,
            CONST_CHAR_TOKEN ,
            COSNT_STRING_TOKEN ,
            COSNT_TRUE_TOKEN ,
            CONST_FALSE_TOKEN ,
            COSNT_NULL_TOKEN
    };
    //
    private void getchar(){
        if (cur_pos < pri_code.length()){
            peek = pri_code.charAt(cur_pos);
        } else {
            peek = 0;
        }
    }

    private void advance(){
        cur_pos++;
    }

    private void back(){
        cur_pos--;
    }

    private int get_line_num(){
        return this.line;
    }

    public Token next() {
        getchar();
        cur_pos++;
        switch (peek){
            case ':':
//                if ()
        }


        return null;
    }

    public String getCharactor(char tokenType){
        return "";
    }
}
