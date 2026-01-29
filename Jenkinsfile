pipeline {
    agent any
    
    tools {
        maven 'Maven'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                // Git checkout is automatic when using Pipeline from SCM
                echo "Building project for Taiga User Story: Integration Demo"
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling Java code...'
                echo 'Taiga Task: TG-5 - Configure Jenkins Build Pipeline'
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running JUnit tests...'
                echo 'Taiga Tasks: TG-3 (Calculator Tests) and TG-4 (StringUtils Tests)'
                sh 'mvn test'
            }
            post {
                always {
                    // Publish JUnit test results
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging application...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Archive') {
            steps {
                echo 'Archiving artifacts...'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
    
    post {
        success {
            script {
                // Get commit message
                def commitMsg = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                echo "Commit message: ${commitMsg}"
                
                // Check if commit contains TG-5
                if (commitMsg.contains('TG-5')) {
                    echo "Found TG-5 reference in commit"
                    
                    // Taiga API configuration
                    def taigaToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY5NzI3MjkxLCJqdGkiOiJiMzE3Mzg1YjViOWU0N2RiOTY1YzBjMDA5NjhmNGE4OSIsInVzZXJfaWQiOjV9.aVwfQFUklygMOTVdN4ty-yht2cVQ8G7rnTzy2IDzVcs'
                    def taigaUrl = 'https://swent0linux.asu.edu/taiga/api/v1'
                    def taskId = 110  // Hardcoded TG-5 task ID
                    def buildUrl = "${env.BUILD_URL}"
                    def buildNumber = "${env.BUILD_NUMBER}"
                    
                    try {
                        echo "Updating Taiga task TG-5 (ID: ${taskId})"
                        
                        def comment = "Jenkins Build #${buildNumber} completed successfully!\\n" +
                                      "Build URL: ${buildUrl}\\n\\n" +
                                      "Results:\\n" +
                                      "- All 22 tests passed\\n" +
                                      "- JAR artifact created\\n" +
                                      "- Fully automated Taiga-Jenkins integration!"
                        
                        sh """
                            curl -k -X PATCH \
                              "${taigaUrl}/tasks/${taskId}" \
                              -H "Authorization: Bearer ${taigaToken}" \
                              -H "Content-Type: application/json" \
                              -d '{
                                "comment": "${comment}",
                                "version": 4
                              }'
                        """
                        
                        echo "✓ Successfully updated Taiga task TG-5!"
                    } catch (Exception e) {
                        echo "✗ Error: ${e.getMessage()}"
                    }
                } else {
                    echo "No TG-5 reference found in commit"
                }
                
                echo 'Build completed successfully!'
                echo 'All tests passed. Ready for deployment.'
            }
            
            cleanWs()
        }
        failure {
            echo 'Build failed!'
            echo 'Please check the test results and fix failing tests.'
            cleanWs()
        }
    }
}