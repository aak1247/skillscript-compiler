package com.aak1247.com.aak1247.model;

import com.aak1247.com.aak1247.Interface.Printable;
import com.aak1247.com.aak1247.lexer.Identifier;
import com.aak1247.com.aak1247.lexer.IdentifierType;

import java.util.List;

/**
 * @author  alex on 2017/7/21.
 */
public class Function implements Printable {
    private String name;
    private List<Identifier> paraList;
    private int entry;
    private int result;

    public Function(String name, List<Identifier> paraList, int entry, int result) {
        this.name = name;
        this.paraList = paraList;
        this.entry = entry;
        this.result = result;
    }
}
