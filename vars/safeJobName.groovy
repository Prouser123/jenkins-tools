// vars/safeJobName.groovy

def call() {
  return JOB_NAME.replaceAll( "/",".")
}