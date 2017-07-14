package com.aak1247.com.aak1247.Interface;

/**
 * @author aak12 on 2017/7/13.
 */
public interface Printable {
    default String print(){
        System.out.print(this.toString());
        return this.toString();
    }
}
