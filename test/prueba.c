#include <stdio.h> 
    
    // Calculo fibonacci iterativo
    int fib(int n){
        int first,second,tmp;
        first = 0;
        second = 1;
        while (n>0) {
            tmp = first+second;
            first = second;
            second = tmp;
            n -= 1;
             printf("%d\n",n);
        }
        return first;
    }
    
    void main(){
        int i,f;
        f=6;
        i=fib(f);
        printf("%d\n",i);
        return;

    }