// vars/ghSetStatus.groovy

def call(String message, String state, String context="ci") {
	withCredentials([string(credentialsId: 'github-ci', variable: 'TOKEN')]) {
		sh """
            //set -x
            curl --silent --output /dev/null --show-error --fail \"https://api.github.com/repos/	${env.REPO}/statuses/${env.GIT_COMMIT}\" \
				-H \"Authorization: ${TOKEN}\" \
                -H \"Content-Type: application/json\" \
                -X POST \
                -d \"{\\\"description\\\": \\\"$message\\\", \\\"state\\\": \\\"$state\\\", \\\"context\\\": \\\"$context\\\", \\\"target_url\\\": \\\"${env.BUILD_URL}\\\"}\"
        """
	}
}