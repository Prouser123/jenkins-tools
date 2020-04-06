// vars/gitCommit.groovy

def call() {
  return sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'")
}