// vars/ghClone.groovy

def call() {
    stage('Clone') {
         agent { label 'docker-cli', docker {image 'alpine:latest'}
         steps {
            git 'https://github.com/${env.REPO}.git'
			echo 'GIT COMMIT: $GIT_COMMIT'
			stash name 'scm', includes '*'
         }
      }
}