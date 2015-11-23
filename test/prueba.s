	.file	"prueba.c"
	.section	.rodata
.LC0:
	.string	"%d\n"
	.text
	.globl	fib
	.type	fib, @function
fib:
.LFB0:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$40, %esp
	movl	$0, -20(%ebp)
	movl	$1, -16(%ebp)
	jmp	.L2
.L3:
	movl	-16(%ebp), %eax
	movl	-20(%ebp), %edx
	addl	%edx, %eax
	movl	%eax, -12(%ebp)
	movl	-16(%ebp), %eax
	movl	%eax, -20(%ebp)
	movl	-12(%ebp), %eax
	movl	%eax, -16(%ebp)
	subl	$1, 8(%ebp)
	movl	8(%ebp), %eax
	movl	%eax, 4(%esp)
	movl	$.LC0, (%esp)
	call	printf
.L2:
	cmpl	$0, 8(%ebp)
	movl	-12(%ebp), %eax
	leave
	ret
.LFE0:
	.size	fib, .-fib
	.globl	main
	.type	main, @function
main:
.LFB1:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	subl	$32, %esp
	movl	$6, 24(%esp)
	movl	24(%esp), %eax
	movl	%eax, (%esp)
	call	fib
	movl	%eax, 28(%esp)
	movl	28(%esp), %eax
	movl	%eax, 4(%esp)
	movl	$.LC0, (%esp)
	call	printf
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE1:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.8.4-2ubuntu1~14.04) 4.8.4"
	.section	.note.GNU-stack,"",@progbits
