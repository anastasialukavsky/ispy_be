import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.*
import org.jooq.meta.jaxb.Configuration
import org.jooq.meta.jaxb.Target

plugins {
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.jooq.jooq-codegen-gradle") version "3.19.13"
	id("application")
}

group = "com.ispy"
version = "0.0.1-SNAPSHOT"

application {
	mainClass.set("com.ispy.ispy.IspyApplicationKt")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}


repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jooq:jooq:3.19.13")
	implementation("org.jooq:jooq-meta:3.19.13")
	implementation("org.jooq:jooq-codegen:3.19.13")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("com.graphql-java:graphql-java-extended-scalars:21.0")
	implementation("com.graphql-java-kickstart:graphql-java-tools:13.1.1") {
		exclude(group = "com.graphql-java", module = "graphql-java-extended-scalars")
	}
	implementation("com.graphql-java:graphql-java-extended-scalars:21.0") {
	}
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}



tasks.register<JavaExec>("generateJooq") {
	group = "jooq"
	description = "Generates jOOQ classes from the database schema."

	mainClass.set("org.jooq.codegen.GenerationTool")
	classpath = sourceSets["main"].runtimeClasspath
	args = listOf(file("src/main/resources/jooq-config.xml").absolutePath)
}



tasks.register("jooqGenerate") {
	doLast {
		println("Generating jOOQ code...")
		val jdbcUrl = "jdbc:postgresql://localhost:5432/ispy_db"
		val username = "anastasialukavsky"
		val password = "postrespass"
		val packageName = "com.isy.jooq"
		val outputDirectory = "src/main/kotlin/com/ispy/jooq"


		val jooqConfiguration = Configuration()
			.withJdbc(
				Jdbc()
					.withDriver("org.postgresql.Driver")
					.withUrl(jdbcUrl)
					.withUser(username)
					.withPassword(password)
			)
			.withGenerator(
				Generator()
					.withName("org.jooq.codegen.KotlinGenerator")
					.withDatabase(
						Database()
							.withIncludePrimaryKeys(true)
							.withName("org.jooq.meta.postgres.PostgresDatabase")
							.withInputSchema("public")
							.withIncludes(".*")
							.withExcludes("databasechangelog|databasechangeloglock")

					)
					.withTarget(
						Target()
							.withPackageName(packageName)
							.withDirectory(outputDirectory)
					)

			)


		GenerationTool.generate(jooqConfiguration)
		println("jOOQ generation completed.")
	}
}