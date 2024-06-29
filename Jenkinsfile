pipeline {
    agent any
    stages {
        stage ('Build maven'){
            steps {
                sh 'cd hr-user/ && mvn install -DskipTests'
            }
        }
        stage ('Teste BDD'){
            steps {
                sh 'echo "Testando"'
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
                    sh "cd hr-user/ && ${sonarscanner}/bin/sonar-scanner -e -Dsonar.projectKey=Libras4j -Dsonar.projectName='Libras4j' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_4cd7bdaa1a85455bfe3789bfadeca4344e69ce8f -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/entities/**,**/src/test/java/com/devsuperior/hruser/**,**Application.java,**/target/surefire-reports/**"
                }
            }
        }
        stage ('Deploy microservices'){
            steps {
                sh 'echo "Testando"'
            }
        }
    }
}
