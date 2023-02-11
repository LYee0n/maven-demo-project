

```
kubectl apply -f nginx.yml
kubectl get pod
kubectl apply -f nginx-service.yml
kubectl get service
minikube service nginx-service --url
curl http://192.168.49.2:31616

kubectl logs <pod_name> -c nginx

kubectl exec -it <pod_name> -c nginx -- /bin/bash

```