package com.aak1247.com.aak1247.parser;

import com.aak1247.com.aak1247.lexer.Identifier;
import com.aak1247.com.aak1247.lexer.IdentifierType;
import com.aak1247.com.aak1247.lexer.Lexer;
import com.aak1247.com.aak1247.lexer.Token;
import com.aak1247.com.aak1247.model.Error;
import com.aak1247.com.aak1247.model.Value;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aak12 on 2017/7/13.
 * 语法分析和语义分析
 * 使用词法分析得到的token流，应用递归向下分析的方法，匹配token（终结符）
 * 对文法的要求是：无公共左因子和左递归
 *
 * 每一次匹配完成后前进，cur_token总为新的。
 *
 *  错误类型：
 *      end 0 ("end", 0)
 *      variable undefined error 1  ("", 1, token)
 *      token unexpected error 2    (expected token, 2, token)  eg:("integer", 2, token)
 *      type error  3   (“type1 to type2”, 2, token)
 *
 */
public class Parser {
    private List<Identifier> identifierList;//变量名表
    private int index;
    private int line;
    private AST cur_ast;
    private List<Identifier> fStack;     //函数定义表
    private List<Identifier> data;   //数据段（常数表）
    private List<Identifier> arrStack;   //数组表
    private List<Quadruple> quadrupleList; //四元式表
    private List<Error> errorList;      //语法错误记录
    private boolean hasSucceded = true; //记录匹配是否成功
    private List<Identifier> assignList = new ArrayList<>();//表达式赋值列表
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
        if (index >= tokenList.size()-1){
            throw new Error("end",0);
        }
        return tokenList.get(++ index);
    }

    public void previous(){
        -- index;
    }

    //检查当前面临的token是否为所需的token
    public void match (Token token)throws Throwable{
        if (!token.equalsIgnoreContent(tokenList.get(index))&&!token.equalsInCharactor(tokenList.get(index))){
//            System.out.println("语法错误：在第" + tokenList.get(index).getLine() + "行，此处应为：" +  token.getCharactor() + "，但发现：" + tokenList.get(index).getCharactor());
            throw new Error("unexpected",2,token);
        }else if (index <= tokenList.size()){
            index ++;
        }else {
            throw new Error("end",0);
        }
    }

    public boolean hasNextToken(){
        return index<tokenList.size();
    }

    private Value expression() throws Throwable{
        //返回值为计算结果

        //asnHead
        Identifier local_identifier = new Identifier("temp");
        while (true){
            Token t1 = cur_token();
            Token t2 = nextToken();
            if (t1.equalsIgnoreContent(Lexer.ID_TOKEN)&&t2.equalsIgnoreContent(Lexer.ASSIGN_TOKEN)){
                if (identifierList.contains(new Identifier(t1.getCharactor()))){
                    assignList.add(identifierList.get(identifierList.indexOf(t1)));
                }else {
                    throw new Error("undefined",1,t1);
                }
                if (assignList.get(0) != null && !assignList.get(0).getType().equals(assignList.get(assignList.size()-1))){
                    throw new Error(assignList.get(assignList.size() - 1).getType() + " to " + assignList.get(0),3);
                }
//                assignList.add((Identifier) identifierList.stream().filter(identifier -> identifier.getName().equals(t1.getCharactor())).toArray()[0]);
            }else break;
            nextToken();
        }
        local_identifier.setType(assignList.get(0).getType());
        previous();
        if (cur_token().equalsIgnoreContent(Lexer.ID_TOKEN)) {

            nextToken();
        }else {

        }

            //生成赋值四元式，但不填



        //计算完成以后完成赋值


        return null ;
    }
    private Value factor() throws  Throwable {
        //factor
        if (cur_token().equalsIgnoreContent(Lexer.INC_TOKEN)){
        int time = 1;
        while (nextToken().equalsIgnoreContent(Lexer.INC_TOKEN)){++time;}
        if (cur_token().equalsIgnoreContent(Lexer.ID_TOKEN)){
            Token token = cur_token();
            if (hasDefined(cur_token())){
                if (!assignList.get(assignList.indexOf(cur_token())).getType().equals(IdentifierType.INT)){
                    throw new Error(assignList.get(assignList.indexOf(cur_token())).getType() +" to int",3,cur_token());
                }
                //语义动作

                //生成自增四元式
                while (time-- != 0) {
                    quadrupleList.add(new Quadruple(quadrupleList.size()-1, Instruction.INC,token.getCharactor(),"",""));
                }
            }else throw new Error("", 1, cur_token());
            nextToken();
        }else throw new Error("identifier",2,cur_token());
    }else if (cur_token().equalsIgnoreContent(Lexer.DEC_TOKEN)){
        int time = 1;
        while (nextToken().equalsIgnoreContent(Lexer.DEC_TOKEN)){++time;}
        if (cur_token().equalsIgnoreContent(Lexer.ID_TOKEN)){
            Token token = cur_token();
            if (hasDefined(cur_token())){
                //生成自增四元式
                while (time-- != 0) {
                    quadrupleList.add(new Quadruple(quadrupleList.size()-1, Instruction.DEC,token.getCharactor(),"",""));
                }
            }else throw new Error("", 1, cur_token());
            nextToken();
        }else throw new Error("identifier",2,cur_token());

    }else if (cur_token().equalsIgnoreContent(Lexer.LRB_TOKEN)){

    }
        return null;
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
                        throw throwable;
                    }
                }
            }
            if (cur_token().equalsIgnoreContent(Lexer.RCB_TOKEN)){
                hasSucceded = true;
                cur_ast = compSatement;
//                nextToken();
                return;
            }
        }


        //declStat
        if (cur_token() .equalsInCharactor(Lexer.INT_TOKEN)){
            nextToken();
            if (cur_token().equalsIgnoreContent(Lexer.ID_TOKEN)){
                Identifier identifier = new Identifier(cur_token().getCharactor());
                nextToken();
                if (cur_token().equalsIgnoreContent(Lexer.DELI_TOKEN)) {

                    nextToken();
                    return;
                }else {

                }
            }
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
//                throwable.printStackTrace();
//                System.out.println(throwable.getMessage());
                if (throwable.getMessage() != null&&throwable.getMessage().equals("nomatch")){
//                    System.out.println("语法错误：在" + cur_token.getLine() + "行");
//                    return;
                }else if (throwable.getMessage() != null && throwable.getMessage().equals("end")){
                    System.out.println("语法分析结束");
                }else if (throwable.getMessage() != null && throwable.getMessage().equals("finished")){
                    return;
                }else if (throwable.getMessage() != null && throwable.getMessage().equals("undefined")){

                }

                else {

                }
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
    private boolean hasDefined(Token token){
        if (token.equalsIgnoreContent(Lexer.ID_TOKEN) && identifierList.contains(new Identifier(token.getCharactor())))return true;
        return false;
    }
    public static void main(String args[]){
//        List<Token> tokenList = new ArrayList<>({new Token()})
//        Lexer lexer = new Lexer(":=:=:=∨∨∨∨hello; 111 12.3 TRUE NULL FALSE 'C' \"test\"/*好*/ TRUE NULL FALSE \n 'C' \"test\"//好");
        Lexer lexer = new Lexer("{ }");
        List<Token> tokenList = new ArrayList<>();
        while (lexer.hasNext()){
            tokenList.add(lexer.next());
//            System.out.println(tokenList.size());
//            System.out.println(tokenList.get(tokenList.size()-1));
        }
        Parser parser = new Parser(tokenList);
        parser.program();
    }
}
