def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    // Storing the credentials ID in the 'credentials' variable
    def credentials = config.credentials ?: 'docker-hub-credentials'
    
    echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentials, // <-- FIXED: Use the variable defined above
        usernameVariable: 'dockerhubuser',
        passwordVariable: 'dockerhubpass'
    )]) {
        sh """
            echo "\$dockerhubpass" | docker login -u "\$dockerhubuser" --password-stdin
            docker push ${imageName}:${imageTag}
            docker push ${imageName}:latest
        """
    }
}
