package com.aak1247.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  aak1247 on 2017/7/7.
 */
public class Compiler {
    List<String> tokens = new ArrayList<>();
    String src;
    int curPos;
    public void next(){
        while (src.charAt(curPos)!=' '||src.charAt(curPos)!='\n'||src.charAt(curPos)!='='||src.charAt(curPos)!=';'){

        }
    }
}
