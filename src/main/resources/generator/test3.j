.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 5

    astore 2

    ldc 50
    istore 1

    ldc 100
    istore 3

    L0:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Pontuacao Candidato: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 0

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Disponibilidade Candidato: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    astore 2

    ifeq L2
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Candidato aprovado"
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    goto L3
L2:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Candidato reprovado"
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

L3:
    ldc 0
    goto L0
    L1:
    return
.end method