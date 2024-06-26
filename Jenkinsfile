pipeline {
    agent any
    stages {
        stage ('Build maven'){
            steps {
                sh 'cd hr-eureka-server/ && mvn install -DskipTests'
            }
        }
    }
}