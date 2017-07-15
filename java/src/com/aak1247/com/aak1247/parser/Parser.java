package com.aak1247.com.aak1247.parser;

import com.aak1247.com.aak1247.lexer.Identifier;
import com.aak1247.com.aak1247.lexer.IdentifierType;
import com.aak1247.com.aak1247.lexer.Lexer;
import com.aak1247.com.aak1247.lexer.Token;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aak12 on 2017/7/13.
 * 语法分析和语义分析
 * 使用词法分析得到的token流，应用递归向下分析的方法，匹配token（终结符）
 * 对文法的要求是：无公共左因子和左递归
 *
 */
public class Parser {
    private List<Identifier> identifierList;
    private int index;
    private int line;
    private int result_int;
    private float result_float;
    private char result_char;
    private String result_string;
    private List<Object> stack;     //用于存放函数定义的栈区
    private List<Token> tokenList = new ArrayList<>();
    private Token cur_token;
    private AST program;



    public Parser(List<Token> tokenList) {
        this.tokenList = tokenList;
        index = 0;
        line = 1;
    }

    public Token nextToken(){
        cur_token = tokenList.get(index ++);
        return cur_token;
    }

    public void previous(){
        -- index;
    }

    //检查当前面临的token是否为所需的token
    public void match (Token token)throws Throwable{
        if (!token.equalsIgnoreContent(tokenList.get(index))){
            System.out.println("语法错误：在第" + tokenList.get(index).getLine() + "行，此处应为：" +  token.getCharactor() + "，但发现：" + tokenList.get(index).getCharactor());
            throw new Throwable("unexpected");
        }else if (index <= tokenList.size()){
            index ++;
        }else {
            throw new Throwable("finished");
        }
    }

    private String expression(){
        if (cur_token.equalsIgnoreContent(Lexer.ID_TOKEN)){

        }

        return "";
    }
    private void statement(){
        //compSata
        if (cur_token == Lexer.LCB_TOKEN){

        }

        //declStat
        if (cur_token .equalsInCharactor(Lexer.INT_TOKEN)){

        }
        if (cur_token .equalsInCharactor( Lexer.FLOAT_TOKEN)){

        }
        if (cur_token.equalsInCharactor(Lexer.CHAR_TOKEN)){

        }
        if (cur_token.equalsInCharactor(Lexer.STRING_TOKEN)){

        }
        if (cur_token.equalsInCharactor( Lexer.BOOL_TOKEN)){

        }


        //selectSata
        if (cur_token.equalsInCharactor(Lexer.IF_TOKEN)){

        }

        if (cur_token.equalsInCharactor(Lexer.SWITCH_TOKEN)){

        }

        //iteSata
        if (cur_token.equalsInCharactor(Lexer.WHILE_TOKEN)){

        }
        if (cur_token.equalsInCharactor(Lexer.DO_TOKEN)){

        }

        //exprStat
        if (cur_token.equalsIgnoreContent(Lexer.ID_TOKEN)){

        }else if (cur_token.equals(Lexer.INC_TOKEN)){

        }else if (cur_token.equals(Lexer.DEC_TOKEN)){

        }
        return;
    }


    private void program(){
        Element element = null;
        while (true){
            break;
        }
        return;
    }
    private void compStat()throws Throwable{
        if (cur_token.equals(Lexer.LCB_TOKEN)){
            this.nextToken();

            match(Lexer.RCB_TOKEN);
        }
    }
    private void declar(){

    }
    public static void main(String args[]){
//        List<Token> tokenList = new ArrayList<>({new Token()})
        Lexer lexer = new Lexer(":=:=:=∨∨∨∨hello; 111 12.3 TRUE NULL FALSE 'C' \"test\"/*好*/ TRUE NULL FALSE \n 'C' \"test\"//好");
        List<Token> tokenList = new ArrayList<>();
        while (lexer.hasNext()){
            tokenList.add(lexer.next());
        }
        Parser parser = new Parser(tokenList);
        parser.program();
    }
}
