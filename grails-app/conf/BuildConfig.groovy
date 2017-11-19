grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.8
grails.project.source.level = 1.8
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://repo.grails.org/grails/core"
        mavenRepo "http://repo.grails.org/grails/plugins"
        mavenRepo "http://repo.grails.org/grails/libs-releases/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        compile 'org.springframework:spring-core:jar:4.3.10.RELEASE'
        compile 'org.springframework:spring-context:jar:4.3.10.RELEASE'
        compile 'org.springframework:spring-context-support:jar:4.3.10.RELEASE'
        compile 'org.springframework:spring-beans:jar:4.3.10.RELEASE'
        compile 'org.springframework:spring-aop:jar:4.3.10.RELEASE'
        compile 'org.springframework.data:spring-data-commons:jar:1.13.6.RELEASE'

        compile 'org.springframework.data:spring-data-mongodb:1.10.6.RELEASE'
        compile 'org.mongodb:mongo-java-driver:jar:3.5.0'

        runtime 'mysql:mysql-connector-java:6.0.6'

        compile 'org.springframework.data:spring-data-redis:jar:1.8.6.RELEASE'
        compile 'redis.clients:jedis:jar:2.9.0'

        test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"
        compile "com.xlson.groovycsv:groovycsv:1.0"
        compile 'com.fasterxml.jackson.core:jackson-databind:jar:2.8.6'
        compile 'org.antlr:antlr4-maven-plugin:4.7'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55.3" // or ":tomcat:8.0.22"

        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.8'
        // asset-pipeline 2.0+ requires Java 7, use version 1.9.x with Java 6
        compile ":asset-pipeline:2.5.7"

        // plugins needed at runtime but not for compilation
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"
        runtime ":twitter-bootstrap:3.3.5"

        test ":funky-spock:0.2.2"

        compile ":csv:0.3.1"

        compile "org.grails.plugins:hibernate4:4.3.10"

        compile "org.grails.plugins:spring-security-core:2.0.0"
        compile "org.grails.plugins:spring-security-acl:2.0.1"

        // Uncomment these to enable additional asset-pipeline capabilities
        //compile ":sass-asset-pipeline:1.9.0"
        //compile ":less-asset-pipeline:1.10.0"
        //compile ":coffee-asset-pipeline:1.8.0"
        //compile ":handlebars-asset-pipeline:1.3.0.3"
    }
}
