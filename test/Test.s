Externprint_int:
	.globl	main
	.type	main, @function
main:
	pushl %ebp
	movl %esp, %ebp
	subl $--4, %esp
	movl $100, %eax 
	movl %eax, -4(%ebp)
	movl $6, %eax 
	movl %eax, -8(%ebp)
	movl $0, %eax 
	movl %eax, -12(%ebp)
	movl $1, %eax 
	movl %eax, -16(%ebp)
while0:
	movl -8(%ebp), %eax
	cmpl $0, %eax
jg isGtr0
	movl $0, -20(%ebp)
jmp endGtr0
isGtr0:
	movl $1, -20(%ebp)
endGtr0:
	movl -20(%ebp), %eax
	cmpl $0, %eax
	je end_while2
	movl -12(%ebp), %eax
	movl -16(%ebp), %edx
	addl %edx, %eax
	movl %eax, -24(%ebp)
	movl -24(%ebp), %eax 
	movl %eax, -28(%ebp)
	movl -16(%ebp), %eax 
	movl %eax, -12(%ebp)
	movl -28(%ebp), %eax 
	movl %eax, -16(%ebp)
	movl -8(%ebp), %eax
	movl $1, %edx
	subl %edx, %eax
	movl %eax, -8(%ebp)
	jmp while0
end_while2:
	pushl -12(%ebp)
	call print_int
	movl %eax,-32(%ebp)
	addl $4, %esp
	nop
	leave
	ret
end_Methodmain:

	.size main, .-main
