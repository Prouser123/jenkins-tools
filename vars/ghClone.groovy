// vars/ghClone.groovy

def call() {
  git "https://github.com/${REPO}.git"
  echo "gitCommit() | ${gitCommit()}"
  stash name: 'scm', includes: '*'
}