package com.aak1247.com.aak1247.parser;

import java.util.List;

/**
 * @author  aak12 on 2017/7/13.
 */
public class AST {
    private Element element;
    private List<Element> children;

    public AST() {
    }

    public AST(Element element) {
        this.element = element;
    }

    public AST(Element element, List<Element> children) {
        this.element = element;
        this.children = children;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public void addChildren(Element child) {
        this.children.add(child);
    }
}
