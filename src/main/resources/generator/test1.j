.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 8

    ldc 2.0
    fstore 1

    ldc 1
    istore 5

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    fstore 2

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    fstore 6

    fmul
    ldc 1
    fdiv
    fstore 0

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Resultado: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 0
    invokevirtual java/io/PrintStream/print(F)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Total: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    fdiv
    fstore 3

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Total: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 3
    invokevirtual java/io/PrintStream/print(F)V

    return
.end method