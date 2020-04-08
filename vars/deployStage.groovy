// vars/deployStage.groovy

// Usage || deployStage() || deployStage("exampleServerLabel")

// deployServer - label of the deployment jenkins node with access to jcxdeploy and docker-cli.

def call(String deployServer='jcx.xray', String branch='master') {

  if (gitBranch() == branch) {
  
    node(deployServer) {
	
	  stage('Deploy') {
	  
	    // Build docker container
		unstash 'scm'
		
		sh "docker build . -t jcxdeploy/${safeJobName()}:latest"
		
		// Deploy it (this will fail - exit code 22 - if the endpoint returns a failure
		sh "curl --show-error --fail --unix-socket /var/run/jcx-deploy/jcxdeploy.sock http://invalid.invalid/recreate?safejobname=${safeJobName()}"
	  }
	}
  }
}