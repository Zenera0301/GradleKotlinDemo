plugins {
    `kotlin-dsl`
}

repositories {
    maven{
        setUrl("https://maven.aliyun.com/repository/public/")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

gradlePlugin {
    plugins {
        create("my-custom-plugin") {   //创建一个新的Plugin
            id = "my-plugin"  //插件的ID
            implementationClass = "MyPlugin"   //插件的实现类
        }
    }
}

tasks.test {
    useJUnitPlatform()
}