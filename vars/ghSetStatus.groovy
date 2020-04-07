// vars/ghSetStatus.groovy

def call(String message, String state, String context="ci") {
	
​	def origin = sh(returnStdout: true, script: "git config --get remote.origin.url")
	def originArr = origin.split("/")
	def repo = ((originArr[-2]  + "/" + originArr[-1]).replace(".git", ""))
	
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