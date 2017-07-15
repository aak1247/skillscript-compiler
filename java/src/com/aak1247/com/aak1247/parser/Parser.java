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
 */
public class Parser {
    private Lexer lexer;
    private List<Identifier> identifierList;
    private List<Token> tokenList = new ArrayList<>();
    private AST ast;



    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }


}
