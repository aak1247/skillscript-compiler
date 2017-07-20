package com.aak1247.com.aak1247.compiler;

import com.aak1247.com.aak1247.lexer.Identifier;
import com.aak1247.com.aak1247.lexer.Lexer;
import com.aak1247.com.aak1247.lexer.Token;
import com.aak1247.com.aak1247.parser.AST;
import com.aak1247.com.aak1247.parser.Quadruple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author aak12 on 2017/7/13.
 *         将词法分析、语法分析、语义分析作为独立的三遍，便于输出，这样做的问题在于不利于解释执行，增加了额外的中间结果，会降低解释执行的效率，一般用于编译型语言。
 *         目标代码预计使用C语言。
 */
public class Compiler {
    private List<Token> tokenList;//词法分析的结果
    private List<Identifier> identifierList; //符号表
    private AST ast;    //语法分析的结果
    private List<Quadruple> quadrupleList;  //语义分析和中间代码生成的结果

    public static void main(String argss[]) {
        Compiler compiler = new Compiler();

        //后缀检查

        //读取文件为字符串
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input.bb"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Tool.IOException :" + ex.toString());
        }
        //词法分析，生成token流
        Lexer lexer = new Lexer(sb.toString());
        while (lexer.hasNext()) {
            Token token = lexer.next();
            if (token != null) {
                compiler.tokenList.add(token);
                System.out.println(token);
            }
        }
        //语法分析， 生成AST

        //语义分析生成中间代码
    }
}
