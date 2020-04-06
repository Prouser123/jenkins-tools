def call() {
  stage('Clone') {
    agent { label 'docker-cli', docker {image 'alpine:latest'}
      steps {
        ghClone()
      }
    }
  }
}