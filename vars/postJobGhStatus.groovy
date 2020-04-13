// vars/postJobGhStatus.groovy

def onSuccess() {
  ghSetStatus("The job completed successfully.", "success", "ci")
}

def onFailure() {
  ghSetStatus("The job failed.", "failure", "ci")
}

def onAborted() {
  ghSetStatus("The job was aborted.", "error", "ci")
}

def call(Closure body) {
  postJob(body, onSuccess, onFailure, onAborted)
}