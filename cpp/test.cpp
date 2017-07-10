/**
    @
    @author aak1247
    @text encode UTF-8


*/




#include <iostream>
#include <cstdio>
#include <cstdlib>
// #include <memory.h>
#include <cstring>



namespace Tokens{
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
//        LET,
        VAR,


    //运算符
        ADD=201,   //add
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
    }; 

}
int token;            // current token
char input;
char *src, *old_src;  // pointer to source code string;
int poolsize;         // default size of text/data/stack
int line;             // line number
int *text,              //text segment
    *old_text,          //dump text segment
    *stack;             //stack
char *data;             //data stack
int *pc, *bp, *sp, ax, cycle;   //register


void back(){
    src--;
}

void advance(){
	src++;
}

void next() {
    input = *(src++);
    input = '>';
    std::cout<<"???"; 
	switch(input){
		case '>':
                //greater than or greater equal than
                if(*src=='='){
                	token = Tokens::GET;
                	advance();
				}else{
					token = Tokens::GT;
				}
                break;
        case '<':
                //less than or less equal than
                if(*src=='='){
                	token = Tokens::LET;
                	advance();
				}else{
					token = Tokens::LT;
				}
                break;
        case '=':
                //assign
                token = Tokens::ASSIGN;
                break;
        case ' ':
                //space
                token = Tokens::SPACE;
                break;
        case '\n':
                token = Tokens::SPACE;
                break;
        case '\t':
                token = Tokens::SPACE;
                break;
        case '(':
                token = Tokens::LRB;
                break;
        case ')':
                token = Tokens::RRB;
                break;


        default:
                break;
        
	}

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


int main(int argc, char **argv){
    argc--;
    argv++;
//	std::cin>>src;
	src = ">>>"; 
    next();
    std::cout<<token;
    std::cout<<Tokens::LT;
}
