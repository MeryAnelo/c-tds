.Method Extern: printI
.Method: main
movl $5, %eax 
movl %eax, -4(%ebp)
movl $10, %eax 
movl %eax, -8(%ebp)
movl -12(%ebp), %eax
movl -16(%ebp), %edx
movl %edx, %ecx
subl %edx, %ecx
movl %ecx, %eax
movl %eax, -28(%ebp)
movl -28(%ebp), %eax 
movl %eax, -24(%ebp)
nop
leave
ret
.End-Method: main
