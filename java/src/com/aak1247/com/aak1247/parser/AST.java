package com.aak1247.com.aak1247.parser;

import com.aak1247.com.aak1247.Interface.Printable;

import java.util.List;

/**
 * @author  aak12 on 2017/7/13.
 */
public class AST implements Printable{
    private Element element;
    private List<AST> children;
    private int level;

    public AST() {
        int level = 0;
    }

    public AST(Element element) {
        this.element = element;
    }

    public AST(Element element, List<AST> children) {
        this.element = element;
        this.children = children;
    }

    public AST(Element element, int level) {
        this.element = element;
        this.level = level;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public List<AST> getChildren() {
        return children;
    }

    public void setChildren(List<AST> children) {
        this.children = children;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addChildren(AST child) {
        child.setLevel(this.getLevel()+1);
        this.children.add(child);
    }

    @Override
    public String toString() {
        String string = "";
        int level = this.level;
        while(--level!=0){
            string += " ";
        }
        string += element.toString();
        string += "\n";
        for (AST child: children){
            string += child.toString();
        }
        return string;
    }


}
