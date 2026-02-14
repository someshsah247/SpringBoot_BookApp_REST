pipeline {
    agent any

    tools {
        maven 'Maven'  // Configure Maven in Jenkins Global Tool Config
        jdk 'JDK-8'    // Configure Java 8 for your Spring Boot app
    }

    environment {
        DOCKER_HUB_USER = 'someshsah'
        DOCKER_HUB_REPO = 'someshsah/bookapp'
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-someshsah')  // Create this in Jenkins
    }

    // CI -- code checkout
    stages {
        stage('SCM Checkout') {
            steps {
                echo 'Checking out code from Git...'
                git branch: 'master',
                    url: 'https://github.com/someshsah247/SpringBoot_BookApp_REST.git'  // Update with your repo
            }
        }

        // CI -- build code and get jar
        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                sh 'mvn clean install -DskipTests'  // Creates JAR in target/
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        // CD -- Build Docker Image and Push/Run Image
        stage('Docker Build') {
            steps {
                script {
                    echo 'Building Docker image...'
                    def appName = "bookapp"
                    def appVersion = "1.0.${BUILD_NUMBER}"  // Dynamic versioning
                    def tagToImage = "someshsah" // tag image means username/imagename
                    def image = docker.build("${tagToImage}/${appName}:${appVersion}")

                   // Login and Push to Docker Hub (optional)
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                       image.push("${appVersion}")
                       image.push("latest")
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
        success {
            emailext (
                subject: "SUCCESS: Spring Boot Build #${BUILD_NUMBER}",
                body: """Build successful!
                Check console output at ${BUILD_URL}to view the results.
                JAR: bookapp-1.0.${BUILD_NUMBER}.jar
                Docker Image: bookapp:1.0.${BUILD_NUMBER}""",
                to: 'your-email@company.com',  // Update your email
                from: 'jenkins@company.com'
            )
        }
        failure {
            emailext (
                subject: "FAILED: Spring Boot Build #${BUILD_NUMBER}",
                body: """Build failed!
                Console output: ${BUILD_URL}
                Please check the logs for errors.""",
                to: 'your-email@company.com',
                from: 'jenkins@company.com'
            )
        }
    }
}
