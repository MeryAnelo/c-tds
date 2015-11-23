Externprint_int:
	.globl	fib
	.type	fib, @function
fib:
	movl $0, %eax 
	movl %eax, -4(%ebp)
	movl $1, %eax 
	movl %eax, -8(%ebp)
	movl 0(%ebp), %eax
	pushl %eax
	call print_int
	movl %eax,-16(%ebp)
	addl $4, %esp
while1:
	movl $0, %eax
	cmpl -4(%ebp), %eax
jg isGtr0
	movl $0, -20(%ebp)
jmp endGtr0
isGtr0:
	movl $1, -20(%ebp)
endGtr0:
	movl -20(%ebp), %eax
	cmpl $0, %eax
	je end_while3
	movl -4(%ebp), %eax
	movl -8(%ebp), %edx
	addl %edx, %eax
	movl %eax, -24(%ebp)
	movl -24(%ebp), %eax 
	movl %eax, -28(%ebp)
	movl -8(%ebp), %eax 
	movl %eax, -4(%ebp)
	movl -28(%ebp), %eax 
	movl %eax, -8(%ebp)
	movl -32(%ebp), %eax
	movl $1, %edx
	subl %edx, %eax
	movl %ecx, %eax
	movl %eax, -32(%ebp)
	movl -32(%ebp), %eax
	pushl %eax
	call print_int
	movl %eax,-36(%ebp)
	addl $4, %esp
	jmp while1
end_while3:
	movl 0(%ebp), %eax
	leave
	ret
end_Methodfib:

	.size fib, .-fib
	.globl	main
	.type	main, @function
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $6, %eax
	pushl %eax
	call fib
	movl %eax,-44(%ebp)
	addl $4, %esp
	movl -44(%ebp), %eax 
	movl %eax, -48(%ebp)
	movl -48(%ebp), %eax
	pushl %eax
	call print_int
	movl %eax,-52(%ebp)
	addl $4, %esp
	nop
	leave
	ret
end_Methodmain:

	.size main, .-main
