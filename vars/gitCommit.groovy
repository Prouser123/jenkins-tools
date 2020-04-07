// vars/gitCommit.groovy

def call() {
  return sh(returnStdout: true, script: "git rev-parse HEAD").replaceAll('\r', '').replaceAll('\n', '')
}