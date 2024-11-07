pipeline{
    agent any
    tools{
        maven "maven"

    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Fabian3050/Evaluacion-1-Tingeso']])
                dir("PrestaBanco-Backend"){
                    bat "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps{
                dir("PrestaBanco-Backend"){
                    bat "mvn test"
                }
            }
        }        
        stage("Build and Push Docker Image"){
            steps{
                dir("PrestaBanco-Backend"){
                    script{
                         withDockerRegistry(credentialsId: 'docker-credentials'){
                            bat "docker build -t fabian3050/prestabanco-backend ./PrestaBanco-Backend"
                            bat "docker push fabian3050/prestabanco-backend"
                        }
                    }                    
                }
            }
        }
    }
}