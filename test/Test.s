.Method Extern: printI
.Method: main
movl -4(%rbp), %eax
movl %eax, $5
movl -8(%rbp), %eax
movl %eax, $10
movl -12(%rbp), %eax
addl -16(%rbp), %eax
movl %eax, -28(%rbp)
movl -24(%rbp), %eax
movl %eax, -28(%rbp)
nop
leave
ret
.End-Method: main
