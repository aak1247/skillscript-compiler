
> ABOUT：
> 这是一个编译器的简单实现。





## 简单介绍
关于语言的语法，会在后面介绍。仅就编译器来介绍，主要完成词法分析、语法分析及语义分析和中间代码生成，中间代码有两种生成模式，一种是帮助学习和研究的四元式模式，另一种是能够进行汇编的X86汇编语言。

如果时间充足，我将会优化每一步的输出，将词法分析的token流、语法分析产生的语法树或者中间可能用到的各种表格输出，但是目前不做输出。
## 关于不同语言的版本

出于娱乐和复习语言的目的，想要实现不同语言的版本，为了不落俗套，各语言版本的预计最终效果是不同的。
​    
### C语言

在C语言的版本中词法分析和语法分析将做为同一遍来完成，主要目标是实现生成汇编代码。

### C++

在C++版本中，将词法分析作为独立的一遍来处理。先进行词法分析生成对应的token流，然后根据token流进行递归下降分析。

### Python

对Python并不熟练，可能的实现是用更好的方式在一遍中进行词法分析和语法分析，语法分析可能采用预测分析程序进行，也就是可能会进行预测分析表的输出。

    当然，可能只是一个坑。

### Java版本
Java版本完全是为作业而生的，与本项目无关。
考虑到作业要求给出了孤立于函数和类的代码，将此语言的语法设计为类似于脚本的语言模式，最小的执行单位就是语句。
其文法的形式化描述如下：
```shell
<program>::=<statement>*
<statement>::=<exprStat>|<declarStat>|<selectStat>|<iterateStat>|<compStat>
<exprStat>::=<expr> ; |;
# 运算优先级：最低为赋值，其次三目和逻辑运算,关系再次，算术较高，最高为单目
<expr>::=<asgHead><expr>|<relaExpr> <exprTail> 
<asgHead>::=<id> = 
<relaExpr>::=<arithExpr> <relaExprTail>
<exprTail>::= | <logOp> <relaExpr>
<logOp>::= >|<|>=|<=|==
<relaExprTail>::= |<relaOp> <arithExpr>
<relaOp>::= '||' | && 
<arithExpr>::=<term> <arithExprTail>
<arithExprTail>::= | <addtiveOp> <term> <arithExpTail>
<addtiveOp>::= +| -
<term>::=<factor> <termTail>
<termTail>::= | <multOp> <term> <termTail>
<multOp>::=* | /
<factor>::=<selfOp> <lself>|<rself> <selfOp>+ | <num> | <call> | (<expr>)
<lself>::=<selfOp> <lself> | <var>
<call>::= <funName> (<expr>*)
<declarStat>::=<funDecl>|<varDecl>|<arrDecl>
<funDecl>::=<type> <funName> ( <paramDeclaList> ) { statment* <returnStat> }
<paramDeclaList>::=<paramDecla> \( , <paramDecla> \)*
<paramDecla>::=<type> <varName>
<returnStat>::=return ;|return <expr> ;
<varDecl>::=<type> <varName> <varIntia>
<varIntia>::= ;| = <expr>;
<arrDecl>::=<type> <arrName> <dem>+
<dem>::=[ <len> ]
<selectStat>::= if ( <expr> ) then <compStat> <if-then-tail> | switch(<expr>){<caseStat>+}
<caseSata>::= case <expr> : <statement>
<if-then-tail>::= | else <compStat>
<iterateStat>::=<do-while-sata>|<while-do-stat>
<do-while-stat>::=do <compStat> while ( <expr> )
<while-do-stat>::=while( <expr> ) <compStat>
<compStat>::={ <statement>* }
```



