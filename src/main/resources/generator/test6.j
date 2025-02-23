.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 5

    istore 0
    fstore 1
    astore 2
    astore 3
    ifeq L0
    goto L1
L0:
L1:
    ifeq L2
    goto L3
L2:
L3:
    L4:
    istore 0
    fmul
    fstore 1
    goto L4
    L5:
    return
.end method