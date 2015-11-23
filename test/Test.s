.type main, @function
.globl  main
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $0, %eax 
	movl %eax, -4(%ebp)
while0:
//less
	movl -4(%ebp), %eax
	cmpl $10, %eax
	jl  trueLess0
	movl $0, -8(%ebp)
	jmp  endtrueLess0
trueLess0:
	movl $1, -8(%ebp)
endtrueLess0:

//false
	movl -8(%ebp), %eax
	cmpl $0, %eax
	je end_while2
//jfalse

//push
	movl -4(%ebp), %eax
	pushl %eax
//pushend
	call print_int
	movl %eax,-12(%ebp)
	addl $4, %esp
//suma
	movl -4(%ebp), %eax
	movl $1, %edx
	addl %edx, %eax
	movl %eax, -16(%ebp)

	movl -16(%ebp), %eax 
	movl %eax, -4(%ebp)
	jmp while0
end_while2:
	nop
	leave
	ret
