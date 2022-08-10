package com.example.traceplugin

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

class CustomizeMethodVisitor(
    api: Int,
    methodVisitor: MethodVisitor,
    className: String?,
    access: Int,
    name: String?,
    descriptor: String?
) : AdviceAdapter(api, methodVisitor, access, name, descriptor) {
    private val TAG = "${this.javaClass.simpleName}"
    override fun onMethodEnter() {
        println("$TAG onMethodEnter")
        super.onMethodEnter()
        mv.visitLdcInsn("TraceTest.class")
        mv.visitLdcInsn("aaa start")
        mv.visitMethodInsn(
            INVOKESTATIC, "android/util/Log", "d",
            "(Ljava/lang/String;Ljava/lang/String;)I", false
        )

        mv.visitInsn(POP)
    }

    override fun onMethodExit(opcode: Int) {
        mv.visitLdcInsn("TraceTest.class")
        mv.visitLdcInsn("aaa end")
        mv.visitMethodInsn(
            INVOKESTATIC, "android/util/Log", "d",
            "(Ljava/lang/String;Ljava/lang/String;)I", false
        )
        mv.visitInsn(POP)
        println("$TAG onMethodExit")
        super.onMethodExit(opcode)
    }

}
