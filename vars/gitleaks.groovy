def call() {

    echo "======================================"
    echo "Gitleaks Secret Scan"
    echo "======================================"

    sh '''
        gitleaks detect \
          --source . \
          --no-banner \
          --redact \
          --exit-code 1
    '''
}
