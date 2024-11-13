pipeline {
    agent any
    environment {
        REGISTRY = 'localhost:5000'
        IMAGE_NAME = 'spring-boot-app:latest'
    }
    stages {
        stage('Build') {
            steps {
                sh '''
                   set -e
                   mvn clean package -DskipTests
                '''
            }
        }
        stage('Docker Build') {
            steps {
                sh '''
                   set -e
                   docker build -t $IMAGE_NAME .
                '''
            }
        }
        stage('Docker Push to Minikube') {
            steps {
                sh '''
                   set -e
                   eval $(minikube docker-env)
                   docker tag $IMAGE_NAME $REGISTRY/$IMAGE_NAME
                   docker push $REGISTRY/$IMAGE_NAME
                '''
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                   set -e
                   kubectl apply -f deployment.yaml
                   kubectl apply -f service.yaml
                '''
            }
        }
    }
}
