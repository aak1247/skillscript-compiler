package com.aak1247.com.aak1247.parser;

import com.aak1247.com.aak1247.lexer.Identifier;
import com.aak1247.com.aak1247.lexer.IdentifierType;
import com.aak1247.com.aak1247.lexer.Lexer;
import com.aak1247.com.aak1247.lexer.Token;
import com.aak1247.com.aak1247.model.Error;
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
    private List<Identifier> identifierList;//变量名表
    private int index;
    private int line;
    private int result_int;
    private float result_float;
    private char result_char;
    private String result_string;
    private AST cur_ast;
    private List<Identifier> fStack;     //函数定义表
    private List<Identifier> data;   //数据段（常数表）
    private List<Identifier> arrStack;   //数组表
    private List<Quadruple> quadrupleList; //四元式表
    private List<Error> errorList;      //语法错误记录
    private boolean hasSucceded = true; //记录匹配是否成功
    private List<Token> tokenList = new ArrayList<>();
    private AST program;



    public Parser(List<Token> tokenList) {
        program = new AST();
        this.tokenList = tokenList;
        index = 0;
        line = 1;
    }
    private Token cur_token(){
        return tokenList.get(index);
    }

    public Token nextToken()throws Throwable{
        if (index >= tokenList.size()){
            throw new Throwable("end");
        }
        return tokenList.get(index ++);
    }

    public void previous(){
        -- index;
    }

    //检查当前面临的token是否为所需的token
    public void match (Token token)throws Throwable{
        if (!token.equalsIgnoreContent(tokenList.get(index))&&!token.equalsInCharactor(tokenList.get(index))){
            System.out.println("语法错误：在第" + tokenList.get(index).getLine() + "行，此处应为：" +  token.getCharactor() + "，但发现：" + tokenList.get(index).getCharactor());
            throw new Throwable("unexpected");
        }else if (index <= tokenList.size()){
            index ++;
        }else {
            throw new Throwable("finished");
        }
    }

    public boolean hasNextToken(){
        return index<tokenList.size();
    }

    private String expression(){
        if (cur_token().equalsIgnoreContent(Lexer.ID_TOKEN)){
            try {
                match(Lexer.ID_TOKEN);
            }catch (Throwable throwable){

            }
        }


        /*
                            Identifier<Float> value_string = new Identifier<>("",CONST_FALSE_TOKEN,IdentifierType.FLOAT);
                    value_string.setValue(float_token_value);
                    if (!constantList.contains(value_string))constantList.add(value_string);
                                        Identifier<Integer> value_string = new Identifier<>("", CONST_INT_TOKEN,IdentifierType.INT);
                    value_string.setValue(token_value);
                    if (!constantList.contains(value_string))constantList.add(value_string);
         */
        return "";
    }
    private void statement() throws Throwable{
        System.out.println(cur_token());

        //compSata
        if (cur_token().equalsIgnoreContent(Lexer.LCB_TOKEN)){
            nextToken();
            AST compSatement = new AST();
            while (true) {
                try {
                    statement();
                    if (cur_ast !=null){
                        compSatement.addChildren(cur_ast);
                    }
                    if (!hasSucceded){
                        break;
                    }
                }catch (Throwable throwable){
                    if (throwable.getMessage().equals("finished")) {
                        return;
                    }
                }
            }
            if (cur_token().equalsIgnoreContent(Lexer.RCB_TOKEN)){
                hasSucceded = true;
                cur_ast = compSatement;
                return;
            }
        }


        //declStat
        if (cur_token() .equalsInCharactor(Lexer.INT_TOKEN)){
            
        }
        if (cur_token() .equalsInCharactor( Lexer.FLOAT_TOKEN)){

        }
        if (cur_token().equalsInCharactor(Lexer.CHAR_TOKEN)){

        }
        if (cur_token().equalsInCharactor(Lexer.STRING_TOKEN)){

        }
        if (cur_token().equalsInCharactor( Lexer.BOOL_TOKEN)){

        }


        //selectSata
        if (cur_token().equalsInCharactor(Lexer.IF_TOKEN)){
            //if else then
            if (nextToken().equals(Lexer.LRB_TOKEN)){
                try {
                    expression();
                }catch (Throwable throwable){
                    //未成功匹配expression

                }
                if (nextToken().equals(Lexer.RRB_TOKEN)){
                    //匹配）成功
                }else {
                    //匹配失败，应该为）
                    match(Lexer.RRB_TOKEN);
                }
                try {
                    compStat();
                }catch (Throwable throwable){

                }
                if (nextToken().equalsInCharactor(Lexer.ELSE_TOKEN)){

                }
                if (nextToken().equalsInCharactor(Lexer.THEN_TOKEN)){

                }else {
                    //没有匹配到then
                }
            }

        }

        if (cur_token().equalsInCharactor(Lexer.SWITCH_TOKEN)){

        }

        //iteSata
        if (cur_token().equalsInCharactor(Lexer.WHILE_TOKEN)){

        }
        if (cur_token().equalsInCharactor(Lexer.DO_TOKEN)){

        }

        //exprStat
        if (cur_token().equalsIgnoreContent(Lexer.ID_TOKEN)){

        }else if (cur_token().equals(Lexer.INC_TOKEN)){

        }else if (cur_token().equals(Lexer.DEC_TOKEN)){

        }
        //emptyStat
        if (cur_token().equalsIgnoreContent(Lexer.DELI_TOKEN)){
            this.hasSucceded = true;
            cur_ast = new AST(new Element("emptyStatement"));
        }
        hasSucceded = false;
    }


    private void program(){
        Element element = null;
        while (true){
            try {
                statement();
                if (cur_ast != null)program.addChildren(cur_ast);
                if (!hasSucceded){
                    break;
                }
            }catch (Throwable throwable){
                throwable.printStackTrace();
//                if (throwable.getMessage().equals("nomatch")){
//                    System.out.println("语法错误：在" + cur_token.getLine() + "行");
//                    return;
//                }else if (throwable.getMessage().equals("end")){
//                    System.out.println("语法错误：未匹配");
//                }else {
//
//                }
            }finally {

            }
        }
    }
    private void compStat()throws Throwable{
        if (cur_token().equals(Lexer.LCB_TOKEN)){
            this.nextToken();

            match(Lexer.RCB_TOKEN);
        }
    }
    private void declar(){

    }
    public static void main(String args[]){
//        List<Token> tokenList = new ArrayList<>({new Token()})
//        Lexer lexer = new Lexer(":=:=:=∨∨∨∨hello; 111 12.3 TRUE NULL FALSE 'C' \"test\"/*好*/ TRUE NULL FALSE \n 'C' \"test\"//好");
        Lexer lexer = new Lexer("{ }");
        List<Token> tokenList = new ArrayList<>();
        while (lexer.hasNext()){
            tokenList.add(lexer.next());
        }
        Parser parser = new Parser(tokenList);
        parser.program();
    }
}
