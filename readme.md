

Android字节码ASM插桩
https://blog.csdn.net/huangbin123/article/details/123322667

Android Gradle Plugins系列-01-自定义Gradle插件入门指南

https://blog.csdn.net/xiangang12202/article/details/121898175


插件打包的三种方式
Build Script
直接build script中编写插件的源代码。这样做的好处是插件会自动编译并包含在build script的classpath中，不用我们执行任何操作。但是，插件在build script之外是不可见的，所以不能在定义它的build script之外复用这个插件。

buildSrc project
将插件的源代码放在 rootProjectDir/buildSrc/src/main/java 目录（或 rootProjectDir/buildSrc/src/main/groovy 或 rootProjectDir/buildSrc/src/main/kotlin，具体取决于使用哪种语言）。 Gradle 负责编译和测试插件，并使插件在build script的classpath上可用。该插件对项目里的所有build script都是可见的。 但是对其它项目就不可见了，因此其它项目不能复用该插件。

有关 buildSrc 项目的更多详细信息，请阅读组织 Gradle 项目。

Standardalone project
为插件创建一个单独的项目（或单独的Module），最终编译并发布一个 JAR。通常来说，这个 JAR 可能包含一些插件，或者将几个相关的任务类捆绑到一个库中，或者两者的某种组合。

一般我们开发自定义插件，都是选择Standardalone project的方式居多，编译打包成JAR后，发布到Maven仓库（本地，远程，私有或公有都可以），以便提供给其它项目使用，这样的好处是方便复用，项目编译速度也会快一些。


