Externprint_int:
	.globl	main
	.type	main, @function
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $1, %eax 
	movl %eax, -4(%ebp)
	movl $1, %eax 
	movl %eax, -12(%ebp)
for0:
	movl -12(%ebp), %eax
	cmpl $6, %eax
	jl  trueLess0
	movl $0, -16(%ebp)
	jmp  endtrueLess0
trueLess0:
	movl $1, -16(%ebp)
endtrueLess0:
	movl -12(%ebp), %eax
	cmpl $6, %eax
	jl  trueLess1
	movl $0, -20(%ebp)
	jmp  endtrueLess1
trueLess1:
	movl $1, -20(%ebp)
endtrueLess1:
	movl -20(%ebp), %eax
	cmpl $0, %eax
	je end_for1
	movl -4(%ebp), %eax
	movl -12(%ebp), %edx
	imull %edx, %eax
	movl  %eax,  -24(%ebp)
	movl -24(%ebp), %eax 
	movl %eax, -4(%ebp)
	movl -12(%ebp), %eax
	movl $1, %edx
	addl %edx, %eax
	movl %eax, -12(%ebp)
	jmp for0
end_for1:
	movl -4(%ebp), %eax
	pushl %eax
	call print_int
	movl %eax,-28(%ebp)
	addl $4, %esp


	movl $0, %edx
	movl $3, %eax
	movl $2, %ecx
	cltd
	idivl %ecx
	movl  %edx, -32(%ebp)


	movl -32(%ebp), %eax 
	movl %eax, -4(%ebp)
	movl -4(%ebp), %eax
	pushl %eax
	call print_int
	movl %eax,-36(%ebp)
	addl $4, %esp
	nop
	leave
	ret
end_Methodmain:

	.size main, .-main
