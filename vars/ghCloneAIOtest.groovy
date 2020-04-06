// Scripted Pipelines only.

def call() {
  def String sha
  docker.image('alpine:latest').inside {
        stage('Clone') {
            sh 'apk add git'
			
			git "https://github.com/${REPO}.git"
			
			sha = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'")
			echo "sha = ${sha}"
			
			stash name: 'scm', includes: '*'
        }
    }
}