def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    def credentials = config.docker ?: 'docker-hub-credentials'
    
    echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: docker,
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
