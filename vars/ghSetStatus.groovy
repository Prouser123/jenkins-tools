// vars/ghSetStatus.groovy

def call(String message, String state, String context="ci") {
	
	String origin = sh(returnStdout: true, script: "git config --get remote.origin.url")
	String repo = ((origin.split("/")[-2]).split(".")[0])
	
	withCredentials([string(credentialsId: 'github-ci', variable: 'TOKEN')]) {
		sh """
            curl --silent --output /dev/null --show-error --fail \"https://api.github.com/repos/$repo/statuses/${gitCommit()}\" \
				-H \"Authorization: token ${TOKEN}\" \
                -H \"Content-Type: application/json\" \
                -X POST \
                -d \"{\\\"description\\\": \\\"$message\\\", \\\"state\\\": \\\"$state\\\", \\\"context\\\": \\\"$context\\\", \\\"target_url\\\": \\\"${BUILD_URL}\\\"}\"
        """
	}
}