.Method: sum
movl -12(%rbp), %eax
movl %eax, -8(%rbp)
movl -16(%rbp), %eax
addl -20(%rbp), %eax
movl %eax, -24(%rbp)
.End-Method: sum
.Method: inc
movl -28(%rbp), %eax
addl $1, %eax
movl %eax, -32(%rbp)
.End-Method: inc
.Method: main
movl -36(%rbp), %eax
movl %eax, $10
movl -40(%rbp), %eax
movl %eax, $analizador.MethodCallExpr@6d6f6e28
movl -44(%rbp), %eax
movl %eax, $analizador.MethodCallExpr@135fbaa4
.End-Method: main
