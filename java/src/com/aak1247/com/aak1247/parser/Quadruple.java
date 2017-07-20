package com.aak1247.com.aak1247.parser;

/**
 * @author aak12 on 2017/7/13.
 */
public class Quadruple {
    private int index;
    private Instruction ope;
    private String arg1 = "--";
    private String arg2 = "--";
    private String result = "--";

    public Quadruple() {
    }

    public Quadruple(int index, Instruction ope, String arg1, String arg2, String result) {
        this.index = index;
        if (arg1 != null) this.arg1 = arg1;
        if (arg2 != null) this.arg2 = arg2;
        this.ope = ope;
        if (result != null) this.result = result;
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

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return index + "（" + ope + ", " + arg1 + ", " + arg2 + ", " + result + ")";
    }

}
