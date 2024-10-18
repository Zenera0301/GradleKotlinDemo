rootProject.name = "GradleJavaKotlinDemo"

include("common")
include("chat-service")
include("auth-service")
//include("buildSrc")//'buildSrc' cannot be used as a project name as it is a reserved name

pluginManagement {
    repositories{
        maven{
            setUrl("https://maven.aliyun.com/repository/public/")
        }
    }
}

gradle.settingsEvaluated{
    println("开始构建...")
}

gradle.buildFinished{
    println("结束构建！")
}

// 统计每个任务的耗时
//var time:Long = 0
//gradle.taskGraph.beforeTask{
//    time = System.currentTimeMillis()
//}
//gradle.taskGraph.afterTask{
//    var takeTime = System.currentTimeMillis() - time
//    println("任务：$name 执行耗时: $takeTime ms")
//}

