package com.aak1247.compiler;

import com.aak1247.com.aak1247.lexer.Token;
import com.aak1247.com.aak1247.parser.AST;
import com.aak1247.com.aak1247.parser.Quadruple;

import java.util.List;

/**
 * @author  aak12 on 2017/7/13.
 * 将词法分析、语法分析、语义分析作为独立的三遍，便于输出，这样做的问题在于不利于解释执行，增加了额外的中间结果，会降低解释执行的效率，一般用于编译型语言。
 * 目标代码预计使用C语言。
 */
public class Compiler {
    private List<Token> tokenList;//词法分析的结果
    private AST ast;    //语法分析的结果
    private List<Quadruple> quadrupleList;  //语义分析和中间代码生成的结果
}
