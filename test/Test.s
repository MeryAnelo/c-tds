.type main, @function
.globl  main
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $10, %eax 
	movl %eax, -4(%ebp)
	movl $2, %eax 
	movl %eax, -8(%ebp)
	movl $0, %edx
	movl -4(%ebp), %eax
	movl -8(%ebp), %ecx
	cltd
	idivl %ecx
	movl  %eax, -12(%ebp)
	movl -12(%ebp), %eax 
	movl %eax, -16(%ebp)
	movl 0(%ebp), %eax
	movl %eax,-16(%ebp)
	pushl %eax
	call print_int
	movl %eax,-20(%ebp)
	addl $4, %esp
	nop
	leave
	ret
