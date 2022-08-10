package com.example.traceplugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class TestClassVisitor(classVisitor: ClassVisitor) : ClassVisitor(Opcodes.ASM7, classVisitor) {
    private val TAG = "PluginAmsTag:"
    private var className: String? = null
    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        className = name
    }

    override fun visitMethod(
        methodAccess: Int,
        methodName: String?,
        methodDescriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {

        val methodVisitor =
            super.visitMethod(methodAccess, methodName, methodDescriptor, signature, exceptions)

        if (className == "com/example/testasmdemo/TraceTest" && methodName == "test") {
            println("$TAG 根据上面条件进行的代码植入。。。")
            println("$TAG method=$methodName")
            println("$TAG className=$className")
            return CustomizeMethodVisitor(
                api,
                methodVisitor,
                className,
                methodAccess,
                methodName,
                methodDescriptor
            )
        }

        return methodVisitor
    }
}
