def call() {
  stage('Clone') {
    agent { label 'docker-cli', docker {image 'alpine:latest'}
      steps {
        git 'https://github.com/${REPO}.git'
		echo 'GIT COMMIT: $GIT_COMMIT'
		stash name: 'scm', includes: '*'
      }
    }
  }
}