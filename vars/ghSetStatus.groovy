// vars/ghSetStatus.groovy

def call(String message, String state, String context="ci") {
	withCredentials([string(credentialsId: 'github-ci', variable: 'TOKEN')]) {
		sh """
            curl --silent --output /dev/null --show-error --fail \"https://api.github.com/repos/${REPO}/statuses/${gitCommit()}\" \
				-H \"Authorization: ${TOKEN}\" \
                -H \"Content-Type: application/json\" \
                -X POST \
                -d \"{\\\"description\\\": \\\"$message\\\", \\\"state\\\": \\\"$state\\\", \\\"context\\\": \\\"$context\\\", \\\"target_url\\\": \\\"${BUILD_URL}\\\"}\"
        """
	}
}