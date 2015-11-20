.globl  main
.type main, @function
main:
pushl %ebp
movl %esp, %ebp
subl $16, %esp
movl $5, %eax 
movl %eax, -4(%ebp)
movl $10, %eax 
movl %eax, -8(%ebp)
movl -4(%ebp), %eax
movl -8(%ebp), %edx
addl %edx, %eax
movl %eax, -12(%ebp)
movl -12(%ebp), %eax 
movl %eax, -16(%ebp)
nop
leave
ret
