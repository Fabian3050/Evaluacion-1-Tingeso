pipeline {
    agent any
    tools {
        maven 'maven_3_8_1'
    }
<<<<<<< HEAD:jenkinsfile
    stages {
        stage('Checkout repository') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Fabian3050/Evaluacion-1-Tingeso']])
=======
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Fabian3050/Evaluacion-1-Tingeso']])
                bat "mvn clean install"
>>>>>>> 9a5caccd8ed85998de8128ab2cac6d00c2252790:Jenkinsfile
            }
        }

        stage('Build JAR File') {
            steps {
                dir('PrestaBanco-Backend') { // Cambia al subdirectorio del backend donde está el pom.xml
                    bat 'mvn clean install' // Ejecuta el comando Maven en el directorio correcto
                }
            }
        }

        stage('Test') {
            steps {
                dir('PrestaBanco-Backend') { // Cambia al subdirectorio del backend
                    bat 'mvn test' // Ejecuta las pruebas en el directorio correcto
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                dir('PrestaBanco-Backend') { // Cambia al subdirectorio del backend para construir la imagen de Docker
                    script {
                        if (isUnix()) {
                            sh '''
                                docker buildx create --use
                                docker buildx build --platform linux/amd64,linux/arm64 -t fabian3050/prestabanco-backend:latest --push .
                            '''
                        } else {
                            bat '''
                                docker buildx create --use
                                docker buildx build --platform linux/amd64,linux/arm64 -t fabian3050/prestabanco-backend:latest --push .
                            '''
                        }
                    }
                }
            }
        }
    }
}
