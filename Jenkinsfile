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
        stage ('Teste estatico com o SonarQube'){
            steps {
                sh 'echo "Testando"'
            }
        }
        stage ('Deploy microservices'){
            steps {
                sh 'echo "Testando"'
            }
        }
    }
}
