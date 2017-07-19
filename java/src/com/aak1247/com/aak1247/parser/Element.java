package com.aak1247.com.aak1247.parser;

import com.aak1247.com.aak1247.Interface.Printable;

/**
 * @author  aak12 on 2017/7/13.
 */
public class Element implements Printable{
    public String content;

    public Element(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return content;
    }
}
