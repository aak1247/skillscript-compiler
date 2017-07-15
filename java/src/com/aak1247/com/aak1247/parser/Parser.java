package com.aak1247.com.aak1247.parser;

import com.aak1247.com.aak1247.lexer.Identifier;
import com.aak1247.com.aak1247.lexer.IdentifierType;
import com.aak1247.com.aak1247.lexer.Lexer;
import com.aak1247.com.aak1247.lexer.Token;

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
    private List<Token> tokenList = new ArrayList<>();
    private AST ast;



    public Parser(List<Token> tokenList) {
        this.tokenList = tokenList;
        index = 0;
        line = 1;
    }

    public Token nextToken(){
        return this.tokenList.get(index ++ );
    }

    public void previous(){
        -- index;
    }

    public boolean match (Token token)throws Throwable{
        if (token.equalsIgnoreContent(tokenList.get(index))){
            return true;
        }else {
            throw new Throwable("error");
        }
    }
    private boolean expression(){
        return true;
    }
}
