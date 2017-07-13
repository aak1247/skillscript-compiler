package com.aak1247.com.aak1247.parser;

/**
 * @author aak12 on 2017/7/13.
 */
public class Quadruple {
    private int index;
    private Instruction ope;
    private Object arg1;
    private Object arg2;
    private Object result;
    public Quadruple(){}
    public Quadruple(int index,Instruction ope, Object arg1, Object arg2, Object result){
        this.index = index;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.ope = ope;
        this.result = result;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Instruction getOpe() {
        return ope;
    }

    public void setOpe(Instruction ope) {
        this.ope = ope;
    }

    public Object getArg1() {
        return arg1;
    }

    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }

    public Object getArg2() {
        return arg2;
    }

    public void setArg2(Object arg2) {
        this.arg2 = arg2;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
