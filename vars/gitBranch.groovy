// vars/gitBranch.groovy

def call() {
  String gitCli = sh(returnStdout: true, script: "git rev-parse --abbrev-ref HEAD").replaceAll('\r', '').replaceAll('\n', '')
  
  if (gitCli != "HEAD") {
    // command returned actual branch, return output.
    echo '[gitBranch] Found using CLI.'
	return gitCli

  } else {
    // command returned HEAD, so we must be using a multi-branch pipeline, falling back to $GIT_BRANCH instead.
    echo '[gitBranch] Found using ENV.'
	return GIT_BRANCH
  }
}