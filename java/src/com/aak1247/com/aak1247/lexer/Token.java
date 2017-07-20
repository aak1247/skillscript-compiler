package com.aak1247.com.aak1247.lexer;

import com.aak1247.com.aak1247.Interface.Printable;

/**
 * @author aak12 on 2017/7/13.
 *         词法分析生成的token标记对象
 *         对关键字、运算符、界符采用一符一种
 *         常量和标识符一类一种
 *         tokenType 为种别码
 *         tokenContent 为种内内容
 *         对于标识符为符号表地址
 *         对于常量为其值
 *         charactor 为其原始语句
 */
public class Token implements Printable {

    private char tokenType;
    private TokenContent tokenContent;
    private String charactor;
    private int line;

    public Token(char tokenType, String charactor, TokenContent tokenContent) {
        this.tokenType = tokenType;
        this.charactor = charactor;
        this.tokenContent = tokenContent;
    }

    public Token(char tokenType, String charactor) {
        this.tokenType = tokenType;
        this.charactor = charactor;
    }

    public Token(char tokenType) {
        this.tokenType = tokenType;
        this.tokenContent = null;
    }

    public Token(Token token, TokenContent tokenContent, int line) {
        this.tokenType = token.getTokenType();
        this.charactor = token.getCharactor();
        this.tokenContent = tokenContent;
        this.line = line;
    }

    public Token(Token token, int line) {
        this.tokenType = token.getTokenType();
        this.charactor = token.getCharactor();
        this.line = line;
    }

    public char getTokenType() {
        return tokenType;
    }

    public void setTokenType(char tokenType) {
        this.tokenType = tokenType;
    }

    public TokenContent getTokenContent() {
        return tokenContent;
    }

    public void setTokenContent(TokenContent tokenContent) {
        this.tokenContent = tokenContent;
    }

    public Object getContent() {
        return this.tokenContent.getContent();
    }

    public String getCharactor() {
        return charactor;
    }

    public void setCharactor(String charactor) {
        this.charactor = charactor;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("(");
        sb.append((int) tokenType);
        sb.append(",");
        if (charactor != null) {
            sb.append(charactor);
        } else {
            sb.append("--");
        }
        sb.append(",");
        if (tokenContent != null) {
            sb.append(tokenContent.toString());
        } else {
            sb.append("--");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        } else if ((((Token) o).getTokenType() == this.getTokenType()) && (((Token) o).getCharactor().equals(this.charactor)) && (((Token) o).getContent().equals(this.getContent()))) {
            return true;
        } else return false;
    }

    public boolean equalsIgnoreContent(Object o) {
        if ((o instanceof Token) && ((Token) o).getTokenType() == this.getTokenType()) {
            return true;
        } else if (o.getClass().isPrimitive() && ((char) o == this.getTokenType())) return true;
        return false;
    }

    public boolean equalsInCharactor(Object o) {
        if ((o instanceof Token) && (((Token) o).getCharactor().equals(this.getCharactor()))) {
            return true;
        }
        return false;
    }
}
