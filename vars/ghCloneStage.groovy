// vars/ghCloneStage.groovy

// Scripted Pipelines only.

def call() {
  docker.image('jcxldn/jenkins-containers:base').inside {
        stage('Clone') {
		
			git "https://github.com/${REPO}.git"
			
			echo "SHA = ${gitCommit()}"
			
			stash name: 'scm', includes: '*'
        }
    }
}