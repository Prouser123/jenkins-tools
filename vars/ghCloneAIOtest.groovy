// Scripted Pipelines only.

def call() {
  docker.image('alpine:latest').inside {
        stage('Clone') {
            sh 'apk add git'
			ghClone()
        }
    }
}