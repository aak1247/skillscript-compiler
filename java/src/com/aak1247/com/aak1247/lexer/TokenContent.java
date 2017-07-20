package com.aak1247.com.aak1247.lexer;

/**
 * @author aak12 on 2017/7/14.
 */
public class TokenContent<T> {
    private T content;

    public TokenContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
