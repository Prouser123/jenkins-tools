def call() {
  stage('Clone') {
    agent { label 'docker-cli', docker {image 'alpine:latest'}
      steps {
	    sh 'apk add git'
        ghClone()
      }
    }
  }
}