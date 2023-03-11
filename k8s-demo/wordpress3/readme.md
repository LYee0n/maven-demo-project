


```sh
kubectl create -f ./mysql-secret.yaml
kubectl create -f ./mysql-server-pod.yaml
kubectl create -f ./mysql-server-service.yaml
kubectl create -f wordpress-pod.yaml
kubectl create -f wordpress-service.yaml
minikube service wordpress-service --url
```