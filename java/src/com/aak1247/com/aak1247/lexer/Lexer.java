package com.aak1247.com.aak1247.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aak12 on 2017/7/13.
 * 在词法分析前，先将关键字加入符号表
 */
public class Lexer {
    private int line;
    private char peek;
    private int cur_pos;
    private String pri_code;
    private int buffer;
    private int doubleQuote = 0;
    private List<Identifier> identifierList = new ArrayList<>();//符号表
    public Lexer(String text) {
        this.pri_code = text;
        pri_code = pri_code.concat(" ");
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
            CONST_STRING_TOKEN = new Token(CONST_STRING),
            CONST_TRUE_TOKEN = new Token(CONST_TRUE, "TRUE"),
            CONST_FALSE_TOKEN = new Token(CONST_FALSE,"FALSE"),
            CONST_NULL_TOKEN = new Token(CONST_NULL,"NULL");
    public final static Token ID_TOKEN = new Token(ID);
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
            CONST_STRING_TOKEN ,
            CONST_TRUE_TOKEN ,
            CONST_FALSE_TOKEN ,
            CONST_NULL_TOKEN
    };
    //
    private char getchar()throws Throwable{
        if (cur_pos < pri_code.length()){
            peek = pri_code.charAt(cur_pos++);
        } else {
            peek = 0;
            throw new Throwable("finished");
        }
        return peek;
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
        try{
//            getchar();
            for(getchar();;getchar()){
                if( peek == ' ' || peek == '\t' );
                else if( peek == '\n' ) line++;
//                else if( peek == '/'){	//过滤注释
//                    advance();
//                    getchar();
//                    if (peek!='/'){
//
//                    }
//                    do{
//                        getchar();
//                    }while(peek !='}');
//                }
                else break;
            }
            switch (peek){
                case ':':
                    getchar();
                    if (peek == '='){
                        return new Token(ASSIGN_TOKEN, line);
                    }else if (peek == '=') {
                        return new Token(EQ_TOKEN, line);
                    }else {
                        back();
                        return new Token(COL_TOKEN, line);
                    }
                case '?':
                    return new Token(ASK_TOKEN, line);
                case '∨':
                    return new Token(OR_TOKEN, line);
                case '∧':
                    return new Token(AND_TOKEN, line);
                case '>':
                    return new Token(GT_TOKEN, line);
                case '≥':
                    return new Token(GET_TOKEN, line);
                case '<':
                    return new Token(LT_TOKEN, line);
                case '≤':
                    return new Token(LET_TOKEN, line);
                case '≠':
                    return new Token(NEQ_TOKEN, line);
                case '+':
                    getchar();
                    if(peek == '+'){
                        return new Token(INC_TOKEN, line);
                    }else {
                        back();
                        return new Token(ADD_TOKEN, line);
                    }
                case '-':
                    getchar();
                    if (peek == '-'){
                        return new Token(DEC_TOKEN, line);
                    }else {
                        back();
                        return new Token(SUB_TOKEN, line);
                    }
                case '×':
                    return new Token(MULT_TOKEN, line);
                case '/':
                    return new Token(DIV_TOKEN, line);
                case '┐':
                    return new Token(NOT_TOKEN, line);
                case '(':
                    return new Token(LRB_TOKEN, line);
                case ')':
                    return new Token(RRB_TOKEN, line);
                case '{':
                    return new Token(LCB_TOKEN, line);
                case '}':
                    return new Token(RCB_TOKEN, line);
                case '[':
                    return new Token(LSB_TOKEN, line);
                case ']':
                    return new Token(RSB_TOKEN, line);
                case ';':
                    return new Token(DELI_TOKEN, line);
//                case '\'':
//                    return SQ_TOKEN;
//                case '\"':
//                    return DQ_TOKEN;
            }
            if (peek<='9'&&peek>='0'){
                int token_value = peek - '0';
                while(((peek = getchar())<='9')&&(peek>='0')){
                    token_value = token_value * 10 + (peek - '0');
                }
                if (peek == '.'){
                    float float_token_value = token_value;
                    int bit_count = 0;
                    token_value = 0;
                    while(((peek = getchar())<='9')&&(peek>='0')){
                        token_value = token_value * 10 + (peek - '0');
                        bit_count ++ ;
                    }
                    back();
                    float_token_value += token_value/Math.pow(10,bit_count);
                    return new Token(CONST_FLOAT_TOKEN,new TokenContent<>(float_token_value), line);
                }else {
                    back();
                    return new Token(CONST_INT_TOKEN, new TokenContent<>(token_value), line);
                }
            }
            if ((peek<='z'&&peek >= 'a')||(peek <= 'Z'&& peek >= 'A')||peek == '_'){
                StringBuffer sb = new StringBuffer();
                sb.append(peek);
                while ((peek = getchar()) == '_'||(peek <= 'z'&&peek >= 'a')||(peek <= 'Z'&& peek >= 'A')||(peek <= '9'&&peek >= '0')){
                    sb.append(peek);
                }
                back();
                String result = sb.toString();
                if(result.equals("TRUE")){
                    return CONST_TRUE_TOKEN;
                }else if(result.equals("FALSE")){
                    return CONST_FALSE_TOKEN;
                }else if(result.equals("NULL")){
                    return CONST_NULL_TOKEN;
                }else return new Token(ID, result);
            }
            if (peek == '\''){
                //char
                char token_value = getchar();
                if ((peek = getchar()) == '\''){
                    return new Token(CONST_CHAR_TOKEN, new TokenContent(token_value), line);
                }else {
                    throw new Throwable("SingleQuote"+Integer.toString(line));
                }
            }
            if (peek == '"'){
                //string
                doubleQuote ++ ;
                StringBuffer sb = new StringBuffer();
                while ((peek = getchar())!= '"'){
                    buffer = line;
                    sb.append(peek);
                }
                if (peek == '"') doubleQuote++;
                return new Token(CONST_STRING_TOKEN,new TokenContent(sb.toString()), line);
            }
            throw new Throwable("UnknownCharactor");

        }catch (Throwable throwable){
            String message = throwable.getMessage();
            if (message.equals("finished")&&doubleQuote%2==0){
                System.out.println("词法分析完成");
                return null;
            }else if (message.matches("SingleQuote\\d")){
                System.out.println("语法错误：在第" + line + "行,单引号用来定义char型");
            }else if ( doubleQuote%2!=0){
                System.out.println("语法错误：在第" + line + "行,双引号未闭合");
            }else if (message.equals("UnknownCharactor")){
                System.out.println("未知字符：在第" + line + "行" );
            } else{

            }
        }



        return null;
    }

    public String getCharactor(char tokenType){
        return "";
    }

    public boolean hasNext (){
        if (cur_pos<pri_code.length()){
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        Lexer lexer = new Lexer(":=:=:=∨∨∨∨hello; 111 12.3 TRUE NULL FALSE 'C' \"test\"好");
        while (lexer.hasNext()) {
            Token tToken;
            if((tToken = lexer.next())!=null)System.out.println(tToken);
        }
    }
}
