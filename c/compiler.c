#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>

/**

@author aak1247
@date 2017/07/10
@encode utf-8

说明
暂未对for循环进行支持
暂无对float的支持
无类型转换
无面向对象
广义的函数式语言（变量可以为函数类型）
*/

// instructions
// 四元式可能的语义动作
enum { 
    LEA ,
    IMM ,
    JMP ,
    CALL,
    JZ ,
    JNZ ,
    ENT ,
    ADJ ,
    LEV ,
    LI  ,
    LC  ,
    SI  ,
    SC  ,
    PUSH,
    OR  ,
    XOR ,
    AND ,
    EQ  ,
    NE  ,
    LT  ,
    GT  ,
    LE  ,
    GE  ,
    SHL ,
    SHR ,
    ADD ,
    SUB ,
    MUL ,
    DIV ,
    MOD ,
    OPEN,
    READ,
    CLOS,
    PRTF,
    MALC,
    MSET,
    MCMP,
    EXIT 
};


//all possible tokens
enum token_list
{
    //标识符
    ID = 0,

    //常量
    CONST_INT = 1,
    CONST_CHAR,
    CONST_FLOAT,
    CONST_STRING,
    CONST_TRUE,
    CONST_FALSE,
    CONST_NULL,



    // keyword 关键字
    SKILL = 10,
    SKILLSET,
    LEARN,
    FROM,
    TEACH,
    ATTACK,
    EMIT,   //优先级较低
    IF,
    ELSE,
    THEN,
    CASE,
    DEFAULT,
    BREAK,
    CONTINUE,
    WHILE,
    DO,
    INT,
    FLOAT,
    CHAR,
    STRING,
    BOOL,
    LET,
    VAR,

    //运算符
//最低为赋值
    ASSIGN = 31,
    ADD_ASN,
    SUB_ASN,
    MULT_ASN,
    DIV_ASN,
    MOD_ASN,
//其次为条件和逻辑
    ASK,
    AND,
    OR,
    BIT_OR,
    BIT_AND,
    XOR,
//其次为关系
    GT,
    LT,
    GET,
    LET,
    EQ,
    NEQ，
//其他双目及单目
    SHL,
    SHR,
    ADD， //add
    SUB,
    MULT,
    DIV,
    MOD,
    POW,
    NOT,
    SELF_ADD,
    SELF_SUB,


//界符
    LRB = 70, //左小括号
    RRB,      //右小括号,并不用到
    LCB,      //左花括号
    RCB,      //右花括号，并不用的
    DELI,     //分号
    SPACE,    //空白
    SQ,       //单引号
    DQ,       //双引号
    LSB,      //左方括号
    RSB,      //右方括号，并不用到
    COL,      //冒号


}

struct identifier
{
    int token;
    int hash;
    char *name;
    int class;
    int type;
    int value;
    int Bclass;
    int Btype;
    int Bvalue;
}

int token;                    // current token
char *src, *old_src;          // pointer to source code string;
int poolsize;                 // default size of text/data/stack
int line;                     // line number
int *text,                    //text segment
    *old_text,                //dump text segment
    *stack;                   //stack
char *data;                   //data stack
int *pc, *bp, *sp, ax, cycle; //register
int counter;    //iterable counter

int token_val;   // value of current token (mainly for number)
float float_val;    //value of float token
int *current_id, // current parsed ID
    *symbols;    // symbol table
int *idmain;    //main skill

// fields of identifier
enum
{
    Token,
    Hash,
    Name,
    Type,
    Class,
    Value,
    BType,
    BClass,
    BValue,
    IdSize
};


//Types of identifier
enum{
    CHAR,
    INT,
    FLOAT,
    BOOL,
    STRING,
    PTR,
    REF,
    FUNC,
}

//the middle code 
struct middle_code{
    int index;      //当前四元式序号
    int operation;  //操作
    int argu1;      //第一操作数
    int argu2;      //第二操作数
    int result;     //第二操作数
}


void next()
{
    char *last_pos;
    int hash;

    while (token = *src)
    {
        ++src;
        // parse token here
        if (token == '\n'){
            ++line;
        }
        else if (token == '#'){
            // skip macro, because we will not support it
            while (*src != 0 && *src != '\n')
            {
                src++;
            }
        }
        else if ((token >= 'a' && token <= 'z') || (token >= 'A' && token <= 'Z') || (token == '_')){

            // parse identifier
            last_pos = src - 1;
            hash = token;

            while ((*src >= 'a' && *src <= 'z') || (*src >= 'A' && *src <= 'Z') || (*src >= '0' && *src <= '9') || (*src == '_'))
            {
                hash = hash * 147 + *src;
                src++;
            }

            // look for existing identifier, linear search
            current_id = symbols;
            while (current_id[Token])
            {
                if (current_id[Hash] == hash && !memcmp((char *)current_id[Name], last_pos, src - last_pos))
                {
                    //found one, return
                    token = current_id[Token];
                    return;
                }
                current_id = current_id + IdSize;
            }

            // store new ID
            current_id[Name] = (int)last_pos;
            current_id[Hash] = hash;
            token = current_id[Token] = ID;
            return;
        }

        else if (token >= '0' && token <= '9') {
            // parse number, three kinds of int: dec(123) hex(0x123) oct(017)
            //also float
            token_val = token - '0';
            token = CONST_INT;
            if (token_val > 0) {
                // dec, starts with [1-9]
                while (*src >= '0' && *src <= '9') {
                    token_val = token_val*10 + *src++ - '0';
                }
                if (*src == '.'){
                    //float
                    float_val = token_val;
                    token_val = 0;
                    counter = 0;
                    while (*src >= '0' && *src <= '9') {
                        token_val = token_val*10 + *src++ - '0';
                        counter ++;
                    }
                    float_val += (float)token_val/pow(10, counter);
                    token = CONST_FLOAT;
                }
            } else {
                // starts with number 0
                if (*src == 'x' || *src == 'X') {
                    //hex
                    token = *++src;
                    while ((token >= '0' && token <= '9') || (token >= 'a' && token <= 'f') || (token >= 'A' && token <= 'F')) {
                        token_val = token_val * 16 + (token & 15) + (token >= 'A' ? 9 : 0);
                        token = *++src;
                    }
                } else if(*src != '.') {
                    // oct
                    while (*src >= '0' && *src <= '7') {
                        token_val = token_val*8 + *src++ - '0';
                    }
                } else {
                    //float
                    float_val = 0;
                    token_val = 0;
                    counter = 0;
                    while (*src >= '0' && *src <= '9') {
                        token_val = token_val*10 + *src++ - '0';
                        counter ++;
                    }
                    float_val += (float)token_val/pow(10, counter);
                    token = CONST_FLOAT;

                }
            } 
            return;
        }
        else if (token == '"' || token == '\'') {
            // parse string literal, currently, the only supported escape
            // character is '\n', store the string literal into data.
            last_pos = data;

            //////////////////////////////
            token = CONST_STRING;
            //////////////////////
            while (*src != 0 && *src != token) {
                token_val = *src++;
                if (token_val == '\\') {
                    // escape character
                    token_val = *src++;
                    if (token_val == 'n') {
                        token_val = '\n';
                    }
                }

                if (token == '"') {
                    *data++ = token_val;
                }
            }

            src++;
            // if it is a single character, return CONST_CHAR token
            if (token == '"') {
                token_val = (int)last_pos;
            } else {
                token = CONST_CHAR;
            }

            return;
        }


        else if (token == '/') {
            if (*src == '/') {
                // skip comments
                while (*src != 0 && *src != '\n') {
                    ++src;
                }
            } else if(*src == '='){
                token = DIV_ASN;
                src ++;
            } else{
                // divide operator
                token = DIV;
                return;
            }
        }


        else if (token == '=') {
            // parse '==' and '='
            if (*src == '=') {
                src ++;
                token = EQ;
            } else {
                token = ASSIGN;
            }
            return;
        }
        else if (token == '+') {
            // parse '+' and '++'
            if (*src == '+') {
                src ++;
                token = SELF_ADD;
            } else if(*src == '='){
                src ++;
                token = ADD_ASN;
            } else {
                token = ADD;
            }
            return;
        }
        else if (token == '-') {
            // parse '-' and '--'
            if (*src == '-') {
                src ++;
                token = SELF_SUB;
            } else if(*src == '='){
                src ++;
                token = SUB_ASN;
            } else {
                token = SUB;
            }
            return;
        }
        else if (token == '!') {
            // parse '!='
            if (*src == '=') {
                src++;
                token = NEQ;
            }else {
                token = NOT;
            }
            return;
        }
        else if (token == '<') {
            // parse '<=', '<<' or '<'
            if (*src == '=') {
                src ++;
                token = LET;
            } else if (*src == '<') {
                src ++;
                token = SHL;
            } else {
                token = LT;
            }
            return;
        }
        else if (token == '>') {
            // parse '>=', '>>' or '>'
            if (*src == '=') {
                src ++;
                token = GET;
            } else if (*src == '>') {
                src ++;
                token = SHR;
            } else {
                token = GT;
            }
            return;
        }
        else if (token == '|') {
            // parse '|' or '||'
            if (*src == '|') {
                src ++;
                token = OR;
            } else {
                token = BIT_OR;
            }
            return;
        }
        else if (token == '&') {
            // parse '&' and '&&'
            if (*src == '&') {
                src ++;
                token = AND;
            } else {
                token = BIT_AND;
            }
            return;
        }
        else if (token == '^') {
            token = XOR;
            return;
        }
        else if (token == '%') {
            if(*src == '='){
                src ++;
                token = MOD_ASN;
            } else {
                token = MOD;
            }
            return;
        }
        else if (token == '*') {
            if (*src == '='){
                token = MULT_ASN;
                src ++;
            } else if(*src == '*'){
                token = POW;
                src ++;
            } else {
                token = MULT;
            }
            return;
        }
        else if (token == '[') {
            token = LSB;
            return;
        }
        else if (token == '?') {
            token = ASK;
            return;
        }
        else if (token == 50071) {
            //char ×
            token = MULT;
            return;
        }
        else if (token == 14846372){
            //char ≤
            token = LET;
            return;
        }
        else if (token == 14846368){
            //char ≠
            token = NEQ;
            return;
        }
        else if (token == 14846373){
            //char ≥
            token = GET;
            return;
        }
        else if (token == 14846119){
            //char ∧
            token = AND;
            return;
        }
        else if (token == 14846120){
            //char ∨
            token = OR;
            return;
        }
        else if (token == 14849168){
            //char ┐
            token = NOT;
            return;
        }
        else if (token == '~' || token == ';' || token == '{' || token == '}' || token == '(' || token == ')' || token == ']' || token == ',' || token == ':') {
            // directly return the character as token;
            return;
        }
        else{
            printf("charactor not recognised in line %d",line);
        }
    }
    return;
}

void match(int tk) {
    if (token == tk) {
        next();
    } else {
        printf("%d: expected token: %d\n", line, tk);
        exit(-1);
    }
}

//syntax analysis









void skillscript(){

}

void skill(){


}

void iterationSkill(){

}

void selectionSill(){

}

void compoundSkill(){

}

void declarationSkill(){

}

void expressionSkill(){

}

void expressionSuffix(){

}

void declarationSuffix(){

}

void emitStatement(){

}









void expression(int level)
{
    // do nothing
}



void program()
{
    next(); // get next token
    while (token > 0)
    {
        printf("token is: %c\n", token);
        next();
    }
}

int eval()
{ // do nothing yet
    return 0;
}

int main(int argc, char **argv)
{
    int i, fd;

    argc--; //实际参数数
    argv++; //第一个命令行参数

    poolsize = 256 * 1024; // arbitrary size
    line = 1;

    if ((fd = open(*argv, 0)) < 0)
    {
        printf("could not open(%s)\n", *argv);
        return -1;
    }

    if (!(src = old_src = malloc(poolsize)))
    {
        printf("could not malloc(%d) for source area\n", poolsize);
        return -1;
    }

    // read the source file
    if ((i = read(fd, src, poolsize - 1)) <= 0)
    {
        printf("read() returned %d\n", i);
        return -1;
    }
    src[i] = 0; // add EOF character
    close(fd);

    //allocate memory for virture machine
    if (!(text = old_text = malloc(poolsize)))
    {
        printf("could not malloc %d for text area ", poolsize);
        return -1;
    }

    if (!(data = malloc(poolsize)))
    {
        printf("could not malloc %d for data area ", poolsize);
        return -1;
    }

    if (!(stack = malloc(poolsize)))
    {
        printf("could not malloc %d for stack area ", poolsize);
        return -1;
    }

    memset(text, 0, poolsize);
    memset(data, 0, poolsize);
    memset(stack, 0, poolsize);

    bp = sp = (int *)((int)stack + poolsize);
    ax = 0;



    src = "skill skillset learn from teach if else then case default break continue while do emit int char string bool var class enum void"
          "open read close say malloc memset memcmp exit attack";

     // add keywords to symbol table
    i = CHAR;
    while (i <= While) {
        next();
        current_id[Token] = i++;
    }

    // add library to symbol table
    i = ;
    while (i <= EXIT) {
        next();
        current_id[Class] = Sys;
        current_id[Type] = INT;
        current_id[Value] = i++;
    }

    next(); current_id[Token] = Char; // handle void type
    next(); idmain = current_id; // keep track of main
    program();
    return eval();
}