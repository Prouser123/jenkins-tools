// vars/ghClone.groovy

def call() {
  git 'https://github.com/$REPO.git'
  echo 'GIT COMMIT: $GIT_COMMIT'
  stash name: 'scm', includes: '*'
}