pipeline {
    agent any
    stages {
        stage ('Build maven'){
            steps {
                sh 'cd hr-eureka-server/ && mvn install -DskipTests'
            }
        }
        stage ('Teste BDD'){
            steps {
                sh 'echo "Testando"'
            }
        }
        stage ('Teste TDD'){
            steps {
                sh 'echo "Testando"'
            }
        }
        stage ('SonarQube Analysis'){
            environment {
                sonarscanner = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    sh "cd hr-user/ && ${sonarscanner}/bin/sonar-scanner -e -Dsonar.projectKey=Libras4j -Dsonar.projectName='Libras4j' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_e35f2a08e3555aac8f1d4353c0c7d33936d63808"
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
