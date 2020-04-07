// vars/scmCloneStage.groovy

// Scripted Pipelines only.

def call() {
  docker.image('jcxldn/jenkins-containers:base').inside {
        stage('Clone') {
		
			checkout scm
			
			echo "SHA = ${gitCommit()}"
			
			stash name: 'scm', includes: '*'
        }
    }
}