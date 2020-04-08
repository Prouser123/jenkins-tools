// vars/gitBranch.groovy

def call() {
  return sh(returnStdout: true, script: "git rev-parse --abbrev-ref HEAD").replaceAll('\r', '').replaceAll('\n', '')
}