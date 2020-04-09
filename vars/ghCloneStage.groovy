// vars/ghCloneStage.groovy

// Scripted Pipelines only.

def call() {
  useDockerImage('jcxldn/jenkins-containers:base') {
        stage('Clone') {
		
			git "https://github.com/${REPO}.git"
			
			echo "SHA = ${gitCommit()}"
			
			stash name: 'scm', includes: '**'
        }
    }
}