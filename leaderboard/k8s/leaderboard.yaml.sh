#!/bin/bash
cat <<YAML
apiVersion: apps/v1beta1
kind: Deployment
metadata:
  name: leaderboard
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: leaderboard
    spec:
      containers:
        - name: leaderboard
          image: gcr.io/$GCP_PROJECT/leaderboard:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
          env:
            - name: foobar
              value: "$(date +%s)"
            - name: ZIPKIN_SERVICE_HOST
              value: "zipkin"
            - name: ZIPKIN_SERVICE_PORT
              value: "9411"
---
apiVersion: v1
kind: Service
metadata:
  name: leaderboard
spec:
  type: NodePort
  selector:
    app: leaderboard
  ports:
   - port: 8080
     targetPort: 8080
     protocol: TCP
     name: rest
YAML