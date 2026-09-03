def call() {

    echo "======================================"
    echo "Checkov Infrastructure Security Scan"
    echo "======================================"

    sh '''
        checkov \
          -d . \
          --framework terraform \
          --compact \
          --quiet \
          --soft-fail
    '''
}
