pipeline {
    agent any
    environment {
        REGISTRY = 'localhost:5000'
        IMAGE_NAME = 'spring-boot-app:latest'
        // Add any additional paths you might need
        PATH+EXTRA = '/usr/local/bin'
    }
    stages {
        stage('Build') {
            steps {
                sh '''#!/bin/bash
                   set -e
                   mvn clean package -DskipTests
                '''
            }
        }
        stage('Docker Build') {
            steps {
                sh '''#!/bin/bash
                   set -e
                   docker build -t $IMAGE_NAME .
                '''
            }
        }
        stage('Docker Push to Minikube') {
            steps {
                sh '''#!/bin/bash
                   set -e
                   eval $(minikube docker-env)
                   docker tag $IMAGE_NAME $REGISTRY/$IMAGE_NAME
                   docker push $REGISTRY/$IMAGE_NAME
                '''
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh '''#!/bin/bash
                   set -e
                   kubectl apply -f deployment.yaml
                   kubectl apply -f service.yaml
                '''
            }
        }
    }
}
