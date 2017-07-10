#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>




enum token_list{
//标识符
    ID=0,

//常量
    CONST_INT=1,
    CONST_CHAR,
    CONST_FLOAT,
    CONST_STRING,
    CONST_TRUE,
    CONST_FALSE,
    CONST_NULL,


//界符
    LRB=20,    //左小括号
    RRB,    //右小括号
    LCB,   //左花括号
    FCB,   //右花括号 
    DELI,   //分号
    SPACE,  //空白
    SQ,     //单引号
    DQ,     //双引号
    LSB,   //左方括号
    RSB,   //右方括号
    COL,    //冒号

// keyword 关键字
    SKILL=101,
    SKILLSET,
    LEARN,
    FROM,
    TEACH,
    ATTACK,
    IF,
    ELSE,
    THEN,
    CASE,
    DEFAULT,
    BREAK,
    CONTINUE,
    WHILE,
    DO,
    EMIT,
    INT,
    FLOAT,
    CHAR,
    STRING,
    BOOL,
    LET,
    VAR,


//运算符
    ADD=201，   //add
    SUB,
    MULT,
    DIV,
    POW,
    MOD,
    AND,
    OR,
    NOT,
    SELF_ADD,
    SELF_SUB,
    BIT_OR,
    BIT_AND,
    ASSIGN,
    ADD_ASN,
    SUB_ASN,
    MULT_ASN,
    DIV_ASN,
    MOD_ASN,
    GT,
    LT,
    GET,
    LET,
    EQ,
    NEQ
}


int token;            // current token
char *src, *old_src;  // pointer to source code string;
int poolsize;         // default size of text/data/stack
int line;             // line number
int *text,              //text segment
    *old_text,          //dump text segment
    *stack;             //stack
char *data;             //data stack
int *pc, *bp, *sp, ax, cycle;   //register


void next() {
    token = *src++;


    return;
}

void expression(int level) {
    // do nothing
}

void program() {
    next();                  // get next token
    while (token > 0) {
        printf("token is: %c\n", token);
        next();
    }
}


int eval() { // do nothing yet
    return 0;
}

int main(int argc, char **argv)
{
    int i, fd;

    argc--; //实际参数数
    argv++; //第一个命令行参数

    poolsize = 256 * 1024; // arbitrary size
    line = 1;

    if ((fd = open(*argv, 0)) < 0) {
        printf("could not open(%s)\n", *argv);
        return -1;
    }

    if (!(src = old_src = malloc(poolsize))) {
        printf("could not malloc(%d) for source area\n", poolsize);
        return -1;
    }

    // read the source file
    if ((i = read(fd, src, poolsize-1)) <= 0) {
        printf("read() returned %d\n", i);
        return -1;
    }
    src[i] = 0; // add EOF character
    close(fd);

    //allocate memory for virture machine
    if (!(text = old_text = malloc(poolsize))){
        printf("could not malloc %d for text area ", poolsize);
        return -1;
    }

    if (!(data = malloc(poolsize))){
        printf("could not malloc %d for data area ", poolsize);
        return -1;
    }

    if (!(stack = malloc(poolsize))){
        printf("could not malloc %d for stack area ", poolsize);
        return -1;
    }

    memset(text, 0, poolsize);
    memset(data, 0, poolsize);
    memset(stack, 0, poolsize);

    bp = sp =(int*)((int)stack + poolsize);
    ax = 0;





    program();
    return eval();
}