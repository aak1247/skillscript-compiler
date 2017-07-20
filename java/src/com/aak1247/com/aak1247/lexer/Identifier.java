package com.aak1247.com.aak1247.lexer;

import com.aak1247.com.aak1247.Interface.Printable;

/**
 * @author aak12 on 2017/7/13.
 * 标识符用于识别变量名、函数名和关键字。
 * 对关键字的处理有两种方式：
 *      1. 在词法分析前先加入符号表，这也就意味着要先建立一个符号表；
 *      2. 在语法分析阶段再处理关键字，这也就意味着在词法分析阶段不需要维护符号表。
 *      采取方法2，这样关于定义和类型的报错就只能放在语法分析阶段进行。
 */
public class Identifier<T> implements Printable{
    private String name;        //名称
    private Token token;        //对应token，标识符为ID_TOKEN
    private IdentifierType type;//变量的type
    private T value;            //变量的值

    public Identifier(){
    }
    public Identifier(String name) {
        this.name = name;
    }
    public Identifier(String name, Token token){
        this.name = name;
        this.token = token;
    }
    public Identifier(String name, Token token, IdentifierType type) {
        this.name = name;
        this.token = token;
        this.type = type;
        Object value = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public void setType(IdentifierType type) {
        this.type = type;
    }

    public IdentifierType getType() {
        return type;
    }

    public Token getToken() {
        return token;
    }



    @Override
    public String toString(){
        return "";
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Identifier ){
            if (((Identifier) o).getName().equals(this.name)){
                return true;
            }
        }
        return false;
    }
}
