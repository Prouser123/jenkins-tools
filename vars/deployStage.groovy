// vars/deployStage.groovy

// Usage || deployStage() || deployStage("exampleServerLabel")

// deployServer - label of the deployment jenkins node with access to jcxdeploy and docker-cli.

def call(Boolean commitStatus=false, String deployServer='jcx.xray', String branch='master') {

  if (gitBranch() == branch) {
    echo '[deployStage] Branch correct.'
  
    node(deployServer) {
	
	  stage('Deploy') {
		unstash 'scm'
		
		// Build the docker container
		sh "docker build . -t jcxdeploy/${safeJobName()}:latest --build-arg ${BUILD_ID}"
		
		// Remove any images created in this build that have a tag of 'builder'
		sh "docker image prune --filter label=stage=builder --filter label=build=${BUILD_ID}"
		
		// Deploy it (this will fail - exit code 22 - if the endpoint returns a failure
		sh "curl --show-error --fail --unix-socket /var/run/jcx-deploy/jcxdeploy.sock http://invalid.invalid/recreate?safejobname=${safeJobName()}"
		
		if (commitStatus) {
			ghSetStatus("The deployment succeeded.", "success", "ci/deploy")
		}
	  }
	}
  } else {
    echo "[deployStage] Branch incorrect, got '${gitBranch()}', expecting '$branch'."
  }
}