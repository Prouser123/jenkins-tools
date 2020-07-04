// vars/ghSetStatus.groovy

// Can be one of error, failure, pending, or success.

def call(String message, String state, String context="ci") {

	String origin

	if (env.GIT_URL) {
		echo "[ghSetStatus] GIT_URL available, value: ${env.GIT_URL}"
		
		// We are in a multibranch pipeline where .git may not be available (https://ci.jcx.ovh/job/discord-xbl/job/master/20/console) so we will use the ENV variable instead.
		origin = env.GIT_URL
		
	} else {
		echo "[ghSetStatus] GIT_URL not available, using git CLI instead..."
		
		// The env variable is not available, falling back to CLI.
		origin = sh(returnStdout: true, script: "set +x && git config --get remote.origin.url").replaceAll('\r', '').replaceAll('\n', '')
	}
	String[] originArr = origin.split("/")
	String repo = (originArr[-2] + "/" + originArr[-1]).replace(".git", "")
	
	withCredentials([string(credentialsId: 'github-ci', variable: 'TOKEN')]) {
		sh """
	    set +x
            curl --silent --output /dev/null --show-error --fail \"https://api.github.com/repos/$repo/statuses/${gitCommit()}\" \
				-H \"Authorization: token ${TOKEN}\" \
                -H \"Content-Type: application/json\" \
                -X POST \
                -d \"{\\\"description\\\": \\\"$message\\\", \\\"state\\\": \\\"$state\\\", \\\"context\\\": \\\"$context\\\", \\\"target_url\\\": \\\"${BUILD_URL}\\\"}\"
        """
	}
}
