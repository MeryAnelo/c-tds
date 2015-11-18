.Method Extern: printI
.Method: main
movl $5, %eax 
movl %eax, -4(%rbp)
movl $10, %eax 
movl %eax, -8(%rbp)
movl -12(%rbp), %eax
movl -16(%rbp), %edx
addl %edx, %eax
movl %eax, -28(%rbp)
movl -28(%rbp), %eax 
movl %eax, -24(%rbp)
nop
leave
ret
.End-Method: main
