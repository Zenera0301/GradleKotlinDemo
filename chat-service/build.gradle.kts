plugins {
    `java-library`   //java-library提供了传递依赖api函数
}
dependencies {
    api(project(":common"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}