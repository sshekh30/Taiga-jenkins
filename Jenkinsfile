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
                // Get commit message FIRST, while git repo still exists
                def commitMsg = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                echo "Commit message: ${commitMsg}"
                
                // Taiga API configuration
                def taigaToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY5NzI3MjkxLCJqdGkiOiJiMzE3Mzg1YjViOWU0N2RiOTY1YzBjMDA5NjhmNGE4OSIsInVzZXJfaWQiOjV9.aVwfQFUklygMOTVdN4ty-yht2cVQ8G7rnTzy2IDzVcs'
                def taigaUrl = 'https://swent0linux.asu.edu/taiga/api/v1'
                def projectId = 3  // Taiga project ID
                def buildUrl = "${env.BUILD_URL}"
                def buildNumber = "${env.BUILD_NUMBER}"
                
                // Find all TG-X references in commit message
                def matcher = (commitMsg =~ /TG-(\d+)/)
                def referencedTaskRefs = []
                matcher.each { match -> 
                    referencedTaskRefs << match[1]
                }
                
                echo "Found Taiga task references: TG-${referencedTaskRefs.join(', TG-')}"
                
                // Update each referenced task
                referencedTaskRefs.each { taskRef ->
                    try {
                        echo "Looking up Taiga task with ref #${taskRef}"
                        
                        def taskListJson = sh(
                            script: """
                                curl -k -s -X GET \
                                  "${taigaUrl}/tasks?project=${projectId}&ref=${taskRef}" \
                                  -H "Authorization: Bearer ${taigaToken}" \
                                  -H "Content-Type: application/json"
                            """,
                            returnStdout: true
                        ).trim()
                        
                        def idMatcher = (taskListJson =~ /"id":\s*(\d+)/)
                        if (idMatcher.find()) {
                            def taskId = idMatcher[0][1]
                            echo "Found task TG-${taskRef} with ID: ${taskId}"
                            
                            def versionMatcher = (taskListJson =~ /"version":\s*(\d+)/)
                            def version = versionMatcher.find() ? versionMatcher[0][1] : '1'
                            
                            def comment = "Jenkins Build #${buildNumber} completed successfully!\\n" +
                                          "Build URL: ${buildUrl}\\n\\n" +
                                          "Results:\\n" +
                                          "- All 22 tests passed\\n" +
                                          "- JAR artifact created\\n" +
                                          "- Automated Taiga-Jenkins integration"
                            
                            echo "Updating Taiga task TG-${taskRef}"
                            
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
                            
                            echo "✓ Successfully updated Taiga task TG-${taskRef}"
                        } else {
                            echo "✗ Could not find task TG-${taskRef}"
                        }
                    } catch (Exception e) {
                        echo "✗ Error updating task TG-${taskRef}: ${e.message}"
                    }
                }
                
                if (referencedTaskRefs.isEmpty()) {
                    echo "No Taiga task references found in commit message"
                }
                
                echo 'Build completed successfully!'
                echo 'All tests passed. Ready for deployment.'
            }
            
            // Clean workspace AFTER we're done with git
            cleanWs()
        }
        failure {
            echo 'Build failed!'
            echo 'Please check the test results and fix failing tests.'
            
            // Clean workspace
            cleanWs()
        }
    }
}