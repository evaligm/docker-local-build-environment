pipeline {
	agent any
	tools {
		maven 'Maven Latest'
		ant 'Ant LATEST'
	} 
		environment {
        SCM_CREDENTIALS = credentials('Jenkins_GIT')
    }
   stages {
      stage('Preparation') { // for display purposes
         steps {
            // Get source from GIT repository.
            git  branch: 'master', credentialsId: 'Jenkins_GIT	', poll: true, changelog: true, url: 'https://github.com/lliborius/docker-local-build-environment'
         }
      }
      stage('Build') {
         steps {
            // get the application version from pom
            //pushPomVersionToEnv('helloworld/HelloWorld/pom.xml')
            // Run the maven build
            // ansiColor('xterm') {
            	sh "mvn clean install package -f helloworld/HelloWorld/pom.xml"
            }
	 }
      }	
   }
