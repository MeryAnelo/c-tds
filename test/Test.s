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
.Equal0:
	movl -4(%ebp), %eax
	cmpl $5, %eax
je .equals0
	movl $0, -20(%ebp)
jmp .endEqual0
.equals0:
	movl $1, -20(%ebp)
.endEqual0:
	movl -20(%ebp), %eax
	cmpl $0, %eax
	je else_if2
	call print_int
	movl %eax,-24(%ebp)
	addl $0, %esp
	jmp end_if4
else_if2:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
end_if4:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	nop
	leave
	ret
End_Method: main
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
