import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
	id("nu.studer.jooq") version "8.0"
	id("org.flywaydb.flyway") version "8.0.1"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.book.manager"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

extra["testcontainersVersion"] = "1.17.6"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")

	// jooQ
	implementation("org.jooq:jooq")
	implementation("org.jooq:jooq-meta")
	implementation("org.jooq:jooq-codegen")

	// flyway
	implementation("org.flywaydb:flyway-mysql")

	// kotest
	val kotest_version = "5.5.4"
	testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotest_version")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

	// jooq
	jooqGenerator("com.mysql:mysql-connector-j")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    url = "jdbc:mysql://localhost:3306/book?enabledTLSProtocols=TLSv1.2"
                    user = "root"
                    password = "root"
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "book"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "com.example.ktknowledgeTodo.infra.jooq"
                        directory = "$buildDir/generated/source/jooq/main"
                    }
                }
            }
        }
    }
 }

flyway {
    url = "jdbc:mysql://localhost:3306/book?enabledTLSProtocols=TLSv1.2"
    user = "root"
    password = "root"
 }