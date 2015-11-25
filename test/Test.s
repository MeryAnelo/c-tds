Externprint_int:
	.globl	main
	.type	main, @function
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $6, %eax 
	movl %eax, -4(%ebp)
	movl $0, %eax 
	movl %eax, -8(%ebp)
	movl $1, %eax 
	movl %eax, -12(%ebp)
while0:
	movl $0, %eax
	cmpl -4(%ebp), %eax
jg isGtr0
	movl $0, -16(%ebp)
jmp endGtr0
isGtr0:
	movl $1, -16(%ebp)
endGtr0:
	movl -16(%ebp), %eax
	cmpl $0, %eax
	je end_while2
	movl -8(%ebp), %eax
	movl -12(%ebp), %edx
	addl %edx, %eax
	movl %eax, -20(%ebp)
	movl -20(%ebp), %eax 
	movl %eax, -24(%ebp)
	movl -12(%ebp), %eax 
	movl %eax, -8(%ebp)
	movl -24(%ebp), %eax 
	movl %eax, -12(%ebp)
	movl -4(%ebp), %eax
	movl $1, %edx
	subl %edx, %eax
	movl %ecx, %eax
	movl %eax, -4(%ebp)
	jmp while0
end_while2:
	movl $3, %eax 
	movl %eax, -8(%ebp)
	pushl -8(%ebp)
	call print_int
	movl %eax,-28(%ebp)
	addl $4, %esp
	nop
	leave
	ret
end_Methodmain:

	.size main, .-main
