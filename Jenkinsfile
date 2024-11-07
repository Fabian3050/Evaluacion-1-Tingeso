pipeline{
    agent any
    tools{
        maven "maven"

    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Fabian3050/Evaluacion-1-Tingeso/tree/main/PrestaBanco-Backend']])
                bat "mvn clean install"
            }
        }
        stage("Test"){
            steps{
                 bat "mvn test"
            }
        }
        stage("Build and Push Docker Image"){
            steps{
                    script{
                         withDockerRegistry(credentialsId: 'docker-credentials'){
                            bat "docker build -t fabian3050/prestabanco-backend:latest ."
                            bat "docker push fabiam3050/prestabanco-backend:latest"
                        }
                }
            }
        }
    }
}
