// vars/gitCommit.groovy

def call() {
  if (env.GIT_COMMIT) {
  
    echo "[gitCommit] Using env.GIT_COMMIT. (value: ${env.GIT_COMMIT})"
	return env.GIT_COMMIT
  } else {
  
    echo "[gitCommit] env.GIT_COMMIT not available, using git CLI instead..."
    return sh(returnStdout: true, script: "git rev-parse HEAD").replaceAll('\r', '').replaceAll('\n', '')
  }
}