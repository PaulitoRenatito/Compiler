.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 7

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 4
    invokevirtual java/io/PrintStream/print(F)V

    new java/util/Scanner
    dup
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    astore 4

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Sobrenome: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    astore 5

    astore 0

    astore 0

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 0
    invokevirtual java/io/PrintStream/print(F)V

    new java/util/Scanner
    dup
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 1

    new java/util/Scanner
    dup
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 2

    ifeq L0
    istore 3

    istore 2

    istore 1

    goto L1
L0:
L1:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Apos a troca: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 1
    invokevirtual java/io/PrintStream/print(F)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 2
    invokevirtual java/io/PrintStream/print(F)V

    return
.end method