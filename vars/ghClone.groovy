// vars/ghClone.groovy

def call() {
  git "https://github.com/${env.REPO}.git"
  echo "GIT COMMIT ENV: ${env.GIT_COMMIT}"
  echo "GIT COMMIT LOC: ${GIT_COMMIT}"
  stash name: 'scm', includes: '*'
}