#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>

int token;            // current token
char *src, *old_src;  // pointer to source code string;
int poolsize;         // default size of text/data/stack
int line;             // line number
int *text,              //text segment
    *old_text,          //dump text segment
    *stack;             //stack
char *data;             //data stack
int *pc, *bp, *sp, ax, cycle;   //register

enum{
    ADD,    //add
    
}

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

    argc--;
    argv++;

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