.type	main, @function
.globl	main

main:
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	pushl %ebp
	movl %esp, %ebp
	subl $16, %esp
	movl $10, %eax 
	movl %eax, -4(%ebp)
	movl $5, %eax 
	movl %eax, -8(%ebp)
	movl -4(%ebp), %eax
	movl -8(%ebp), %edx
	addl %edx, %eax
	movl %eax, -12(%ebp)
	movl -12(%ebp), %eax 
	movl %eax, -16(%ebp)
	movl %eax, -4(%ebp)
	/*aca pusheo para llamar a la funcion externa*/
	pushl %eax	
	/*aca llamo al metodo externo (borra los comen antes de ejecutar por las dudas jajaj)*/
	call print_int
	addl  $8 , %esp
	nop
	leave
	ret