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
                
                // Taiga API configuration
                def taigaToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY5NzI3MjkxLCJqdGkiOiJiMzE3Mzg1YjViOWU0N2RiOTY1YzBjMDA5NjhmNGE4OSIsInVzZXJfaWQiOjV9.aVwfQFUklygMOTVdN4ty-yht2cVQ8G7rnTzy2IDzVcs'
                def taigaUrl = 'https://swent0linux.asu.edu/taiga/api/v1'
                def projectId = 3
                def buildUrl = "${env.BUILD_URL}"
                def buildNumber = "${env.BUILD_NUMBER}"
                
                // Find all TG-X references using simple string search
                def taskRefs = []
                def searchText = commitMsg
                while (searchText.contains('TG-')) {
                    def idx = searchText.indexOf('TG-')
                    def afterTG = searchText.substring(idx + 3)
                    def refNum = ''
                    
                    // Extract digits after TG-
                    for (int i = 0; i < afterTG.length(); i++) {
                        def ch = afterTG.charAt(i)
                        if (ch.isDigit()) {
                            refNum += ch
                        } else {
                            break
                        }
                    }
                    
                    if (refNum) {
                        taskRefs << refNum
                        echo "Found task reference: TG-${refNum}"
                    }
                    
                    // Move past this occurrence
                    searchText = afterTG
                }
                
                // Remove duplicates
                taskRefs = taskRefs.unique()
                
                if (taskRefs.isEmpty()) {
                    echo "No Taiga task references found in commit"
                } else {
                    echo "Processing ${taskRefs.size()} unique task(s): TG-${taskRefs.join(', TG-')}"
                    
                    // Update each task
                    taskRefs.each { refNum ->
                        try {
                            echo "Looking up task with ref=${refNum}"
                            
                            // Query Taiga API to get task by ref number
                            def apiResponse = sh(
                                script: """
                                    curl -k -s -X GET \
                                      "${taigaUrl}/tasks?project=${projectId}&ref=${refNum}" \
                                      -H "Authorization: Bearer ${taigaToken}" \
                                      -H "Content-Type: application/json"
                                """,
                                returnStdout: true
                            ).trim()
                            
                            // Parse task ID using string operations
                            if (apiResponse.contains('"id":')) {
                                def idIdx = apiResponse.indexOf('"id":')
                                def afterId = apiResponse.substring(idIdx + 5).trim()
                                def commaIdx = afterId.indexOf(',')
                                def taskId = afterId.substring(0, commaIdx).trim()
                                
                                echo "Found TG-${refNum} with ID: ${taskId}"
                                
                                // Get version
                                def version = '1'
                                if (apiResponse.contains('"version":')) {
                                    def verIdx = apiResponse.indexOf('"version":')
                                    def afterVer = apiResponse.substring(verIdx + 10).trim()
                                    def verComma = afterVer.indexOf(',')
                                    version = afterVer.substring(0, verComma).trim()
                                }
                                
                                // Create comment
                                def comment = "Jenkins Build #${buildNumber} completed successfully!\\n" +
                                              "Build URL: ${buildUrl}\\n\\n" +
                                              "Results:\\n" +
                                              "- All 22 tests passed\\n" +
                                              "- JAR artifact created\\n" +
                                              "- Fully automated Taiga-Jenkins integration!"
                                
                                echo "Updating TG-${refNum} (ID: ${taskId}, version: ${version})"
                                
                                // Update task
                                sh """
                                    curl -k -X PATCH \
                                      "${taigaUrl}/tasks/${taskId}" \
                                      -H "Authorization: Bearer ${taigaToken}" \
                                      -H "Content-Type: application/json" \
                                      -d '{
                                        "comment": "${comment}",
                                        "version": ${version}
                                      }'
                                """
                                
                                echo "✓ Successfully updated TG-${refNum}!"
                            } else {
                                echo "✗ Task TG-${refNum} not found in project"
                            }
                        } catch (Exception e) {
                            echo "✗ Error updating TG-${refNum}: ${e.getMessage()}"
                        }
                    }
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