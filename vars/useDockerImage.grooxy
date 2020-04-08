// vars/useDockerImage.groovy

// Same as docker.image('image').inside {} but will automatically update before usage.

def call(String image, Closure body) {
  
  // Define the image
  def dimage = docker.image(image)
  
  // Pull the image
  dimage.pull()
  
  // Use the image with the Closure body
  dimage.inside {
    body()
  }
  
}