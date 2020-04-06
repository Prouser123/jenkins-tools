// vars/ghClone.groovy

def call() {
  git "https://github.com/${env.REPO}.git"
  echo "GIT COMMIT HELPER: ${gitCommit()}"
  stash name: 'scm', includes: '*'
}