// vars/scmCloneStage.groovy

// Scripted Pipelines only.

def call() {
  useDockerImage('jcxldn/jenkins-containers:base') {
        stage('Clone') {
		
			def scmVars = checkout scm
			
			echo "[scmCloneStage] found GIT_BRANCH: ${scmVars.GIT_BRANCH}"
			
			env.GIT_BRANCH = scmVars.GIT_BRANCH
			
			echo "[scmCloneStage] set env.GIT_BRANCH: ${env.GIT_BRANCH}"
			
			echo "SHA = ${gitCommit()}"
			
			stash name: 'scm', includes: '**'
        }
    }
}