package com.aak1247.com.aak1247.model;

import com.aak1247.com.aak1247.lexer.IdentifierType;

/**
 * @author  alex on 2017/7/20.
 */
public class Value<T> {
    public T value;
    public IdentifierType type;

    public Value(T value, IdentifierType type) {
        this.value = value;
        this.type = type;
    }
}
