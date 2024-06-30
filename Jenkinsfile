pipeline {
    agent any
    stages {
        stage ('Build maven'){
            parallel {
                stage ('Build maven User'){
                    steps {
                        sh 'cd hr-user/ && mvn install -DskipTests'
                    }
                }
                stage ('Build maven Oauth'){
                    steps {
                        sh 'cd hr-oauth/ && mvn install -DskipTests'
                    }
                }
                stage ('Build maven Server'){
                    steps {
                        sh 'cd hr-eureka-server/ && mvn install -DskipTests'
                    }
                }
                stage ('Build maven Api-Gateway'){
                    steps {
                        sh 'cd hr-api-gateway-zuul/ && mvn install -DskipTests'
                    }
                }
                stage ('Build maven Config Server'){
                    steps {
                        sh 'cd ~/environment/Libras4j/Libras4j/hr-config-server/ && mvn install -DskipTests'
                    }
                }
            }
        }
        stage ('Teste TDD'){
            steps {
                sh 'cd hr-user/ && mvn clean test install'
            }
        }
        stage ('SonarQube Analysis'){
            environment {
                sonarscanner = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    sh "cd hr-user/ && ${sonarscanner}/bin/sonar-scanner -e -Dsonar.projectKey=Libras4j -Dsonar.projectName='Libras4j' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_4cd7bdaa1a85455bfe3789bfadeca4344e69ce8f -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/java/com/devsuperior/**,**/entities/**,**Application.java"
                }
            }
            
        }        
        stage ('Deploy microservices'){
            steps {
                sh 'cd hr-user/ && docker-compose up -d'
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'hr-user/target/surefire-reports/*.xml'
        }
    }
}
