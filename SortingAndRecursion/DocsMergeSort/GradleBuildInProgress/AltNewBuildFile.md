plugins {
    id 'java'
    id 'application'
    id 'org.asciidoctor.jvm.convert' version '3.3.2'
    id 'com.github.johnrengelman.shadow' version '7.1.2'
    id 'checkstyle'
    id 'com.github.spotbugs' version '5.0.13'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.commonmark:commonmark:0.18.1'
    implementation 'org.deeplearning4j:deeplearning4j-core:1.0.0-beta7'
    implementation 'org.nd4j:nd4j-native-platform:1.0.0-beta7'
    implementation 'com.google.guava:guava:31.1-jre'
    
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = 'com.example.MainClass'
}

test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = '8.45'
    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")
}

spotbugs {
    effort = 'max'
    reportLevel = 'high'
}

asciidoctor {
    sourceDir = file('src/docs')
    outputDir = file('build/docs')
}

task processMarkdown(type: JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.MarkdownProcessor'
}

task isolateCode(type: JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.CodeIsolator'
}

task runAIAnalysis(type: JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.AIAnalyzer'
}

shadowJar {
    manifest {
        attributes 'Main-Class': 'com.example.MainClass'
    }
}