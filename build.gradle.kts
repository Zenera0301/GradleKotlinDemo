plugins {
    //id("java")
    id("application")
    id("maven-publish")
    kotlin("jvm") version "1.9.21"

    id("my-plugin")   //直接通过id使用buildSrc中定义的插件
}
application{
    mainClass="Main"
}
group = "org.example"
version = "1.0-SNAPSHOT"

configure<JavaPluginExtension>{
    sourceCompatibility = Version.sourceVersion//来自buildSrc
    targetCompatibility = Version.targetVersion
}

// 配置完后，点publishToMavenLocal 会保存到C盘中的.m2文件夹，而非设置的Maven仓库
publishing{
    publications{
        create<MavenPublication>("library"){//随便起仓库名
            groupId="org.example"
            artifactId="hello"
            version="0.0.1"
            from(components["java"])//发布为jar包形式
        }
    }
}

subprojects {   //subprojects表示对所有的子项目生效
    apply(plugin = "java")   //定义插件需要使用apply来完成，plugin{}在这里不行

    group = "org.example"   //定义组
    version = "unspecified"  //定义版本

    repositories {   //定义自定义仓库地址
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
    }
}

repositories {
    mavenAlibaba()//找到buildSrc中定义的函数
}

dependencies {
//    implementation("org.springframework:spring-context:6.1.13"){
//        exclude("org.springframework", "spring-aop")
//    }

//    implementation("org.springframework:spring-context:6.1.13")//包含aop6.1.3
//    implementation("org.springframework:spring-aop:6.1.1")//不起作用也不冲突，系统默认使用更新一点的版本aop6.1.3

//    implementation("org.springframework:spring-context:6.1.13")//虽然包含aop6.1.3
//    implementation("org.springframework:spring-aop:6.1.1!!")//但是此处：强制使用aop6.1.1


    implementation("org.springframework:spring-context:${Version.springVersion}") //直接从Version中获取版本
    implementation("org.springframework:spring-beans:${Version.springVersion}")
    implementation("org.springframework:spring-aop:${Version.springVersion}")
    implementation("org.springframework:spring-web:${Version.springVersion}")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

//// 自定义任务：HelloTask
//defaultTasks("HelloTask") // ./gredlew 直接执行该任务
//tasks.register("HelloTask"){
//    group = "build" // 默认是在other中的，这样可以设置到build组中，也可以自定义组名
//    description = "这是一个伟大的任务！"
//
//    dependsOn("jar") // 前置任务，先执行jar任务，再继续执行本任务
//
//    doFirst{
//        println("获取自定义参数：${project.properties["testPara"]}") // ./gradlew HelloTask -P testPara=666
//        println("自定义任务开始...")
//    }
//
//    doLast{
//        println("自定义任务结束！")
//    }
//}
//
//tasks.named("build"){
//    dependsOn("HelloTask")//让HelloTask成为build的前置操作
//}
////或：
////tasks.build{
////    dependsOn("HelloTask")//HelloTask是build的前置操作
////}