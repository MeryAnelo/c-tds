.type main, @function
.globl  main
main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $0, %eax 
	movl %eax, -4(%ebp)
	movl $0, %eax 
	movl %eax, -8(%ebp)
for0:
//less
	movl -4(%ebp), %eax
	cmpl $15, %eax
	jl  trueLess0
	movl $0, -12(%ebp)
	jmp  endtrueLess0
trueLess0:
	movl $1, -12(%ebp)
endtrueLess0:

//false
	movl -12(%ebp), %eax
	cmpl $0, %eax
	je end_for1
//jfalse
//suma
	movl -4(%ebp), %eax
	movl $1, %edx
	addl %edx, %eax
	movl %eax, -4(%ebp)


//push
	movl -8(%ebp), %eax
	pushl %eax
//pushend
	call print_int
	movl %eax,-16(%ebp)
	addl $4, %esp
//suma
	movl -8(%ebp), %eax
	movl $1, %edx
	addl %edx, %eax
	movl %eax, -20(%ebp)

	movl -20(%ebp), %eax 
	movl %eax, -8(%ebp)
	jmp for0
end_for1:
	nop
	leave
	ret
