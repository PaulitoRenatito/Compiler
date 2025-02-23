.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 6

    L0:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "A"
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 0

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "B"
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 1

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "C"
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 2

    ifeq L2
    istore 3

    goto L3
L2:
    ifeq L4
    istore 3

    goto L5
L4:
    istore 3

L5:
L3:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Maior valor:"
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 3
    invokevirtual java/io/PrintStream/print(F)V

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Outro? "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

    new java/util/Scanner
    dup
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextFloat()F
    istore 4

    ldc 0
    goto L0
    L1:
    return
.end method