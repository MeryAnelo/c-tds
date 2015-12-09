	.file	"pruebaFloatParaS.c"
	.section	.rodata
.LC2:
	.string	"%f\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	subl	$32, %esp
	movl	.LC0, %eax
	movl	%eax, 20(%esp)
	movl	.LC1, %eax
	movl	%eax, 24(%esp)
	flds	20(%esp)
	fdivs	24(%esp)
	fstps	28(%esp)
	flds	28(%esp)
	fstpl	4(%esp)
	movl	$.LC2, (%esp)
	call	printf
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.section	.rodata
	.align 4
.LC0:
	.long	1087163597
	.align 4
.LC1:
	.long	1074580685
	.ident	"GCC: (Ubuntu 4.8.4-2ubuntu1~14.04) 4.8.4"
	.section	.note.GNU-stack,"",@progbits
