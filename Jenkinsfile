pipeline {
    agent any
    stages {
        stage ('Build maven'){
            steps {
                sh 'cd ./hr-eureka-server/'
                sh 'cd mvn install -DskipTests'
            }
        }
        stage ('Tests Unitarios'){
            steps {
                sh 'echo "Testando"'
            }
        }
    }
}
