.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 8

    istore 1

    fstore 4

    fdiv
    fstore 3

    new java/util/Scanner
    dup
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 1

    new java/util/Scanner
    dup
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    fstore 3

    imul
    istore 2

    imul
    imul
    fstore 3

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Resultado: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 3
    invokevirtual java/io/PrintStream/print(F)V

    fstore 4

    fstore 5

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "E: "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 5
    invokevirtual java/io/PrintStream/print(F)V

    fdiv
    fstore 6

    return
.end method