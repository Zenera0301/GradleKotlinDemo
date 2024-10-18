import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        //println("MyPlugin被加载了！")
        target.tasks.register("a") {
            doLast { println("完成a任务") }
        }

        target.tasks.register("b") {
            doLast { println("完成b任务") }
            dependsOn(target.tasks.named("a"))
        }
    }
}