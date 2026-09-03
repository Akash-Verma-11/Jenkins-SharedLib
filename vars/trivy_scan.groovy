def call() {

    sh '''
        echo "======================================"
        echo "Trivy Filesystem Security Scan"
        echo "======================================"

        trivy fs \
          --scanners vuln,secret,misconfig \
          --severity HIGH,CRITICAL \
          --exit-code 1 \
          --no-progress \
          .
    '''
}
