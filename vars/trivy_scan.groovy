def call(Map config = [:]) {

    if (config.image) {

        echo "======================================"
        echo "Trivy Docker Image Security Scan"
        echo "Image: ${config.image}"
        echo "======================================"

        sh """
            trivy image \
              --scanners vuln \
              --severity HIGH,CRITICAL \
              --exit-code 1 \
              --no-progress \
              ${config.image}
        """

    } else {

        echo "======================================"
        echo "Trivy Filesystem Security Scan"
        echo "======================================"

        sh '''
            trivy fs \
              --scanners vuln,secret,misconfig \
              --severity HIGH,CRITICAL \
              --exit-code 1 \
              --no-progress \
              .
        '''
    }
}
