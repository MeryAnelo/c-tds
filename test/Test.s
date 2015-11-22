.type main, @function
.globl  main
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $0, %eax 
	movl %eax, -4(%ebp)
while0:
less0:
	movl -4(%ebp), %eax
	cmpl $10, %eax
jl isLess0
	movl $0, -8(%ebp)
jmp endLess0
isLess0:
	movl $1, -8(%ebp)
endLess0:
	movl -8(%ebp), %eax
	cmpl $0, %eax
	je end_while2
	movl -4(%ebp), %eax
	pushl %eax
	call print_int
	movl %eax,-12(%ebp)
	addl $4, %esp
	movl -4(%ebp), %eax
	movl %eax, %xmm0
	movl $1, %eax
	movl %eax, %xmm1
	addss %xmm1, %xmm0
	movl %xmm0, -16(%ebp)
	movl -16(%ebp), %eax 
	movl %eax, -20(%ebp)
	jmp while0
end_while2::
	nop
	leave
	ret
