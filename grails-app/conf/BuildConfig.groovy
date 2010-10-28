/**
 * This file is part of the DuckMaps software for making building plans
 * available on the Internet.
 * 
 * Copyright (C) 2010  Jan Ehrhardt
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
  // inherit Grails' default dependencies
  inherits("global") {
    // uncomment to disable ehcache
    // excludes 'ehcache'
  }
  log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  repositories {
    grailsPlugins()
    grailsHome()
    grailsCentral()
    
    // uncomment the below to enable remote dependency resolution
    // from public Maven repositories
    //mavenLocal()
    //mavenCentral()
    //mavenRepo "http://snapshots.repository.codehaus.org"
    //mavenRepo "http://repository.codehaus.org"
    //mavenRepo "http://download.java.net/maven/2/"
    //mavenRepo "http://repository.jboss.com/maven2/"
  }
  dependencies {
    // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
    
    // These dependencies are required as a workaround for STS.
    // Currently STS does not recognize dependencies declared by plugins.
    compile 'org.apache.ant:ant-junit:1.7.1'
    compile 'org.spockframework:spock-core:0.4-groovy-1.7'
    test('net.sourceforge.htmlunit:htmlunit:2.7') {
      excludes 'xalan' // IVY-1006 - use xalan 2.7.0 to avoid (see below)
      excludes 'xml-apis' // GROOVY-3356
    }
    runtime('org.springframework.security:org.springframework.security.core:3.0.3.RELEASE') {
      excludes 'com.springsource.org.aopalliance',
          'com.springsource.org.apache.commons.logging',
          'org.springframework.beans',
          'org.springframework.context',
          'org.springframework.core'
    }
    runtime('org.springframework.security:org.springframework.security.web:3.0.3.RELEASE') {
      excludes 'com.springsource.javax.servlet',
          'com.springsource.org.aopalliance',
          'com.springsource.org.apache.commons.logging',
          'org.springframework.aop',
          'org.springframework.beans',
          'org.springframework.context',
          'org.springframework.core',
          'org.springframework.web'
    }
    
    test('xalan:xalan:2.7.0') { excludes 'xml-apis' // GROOVY-3356
    }
  }
}
