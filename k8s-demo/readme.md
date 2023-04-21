

```
kubectl apply -f nginx.yml
kubectl get pod
kubectl apply -f nginx-service.yml
kubectl get service
minikube service nginx-service --url
curl http://192.168.49.2:31616

kubectl logs <pod_name> -c nginx

kubectl exec -it <pod_name> -c nginx -- /bin/bash

kubectl create -f ./cronjob.yml

kubectl logs hello-28034653-v6nq8

kubectl get all

kubectl describe cronjob.batch/hello


kubectl create secret docker-registry regcred --docker-server=registry.gitlab.com --docker-username=? --docker-password=? --docker-email=?

kubectl get secret regcred --output=yaml

kubectl delete -f ./cronjob.yml

kubectl get pods

kubectl logs hello-28034660-ghhvl

kubectl apply -f ./demo1.yml 

kubectl exec --stdin --tty nginx-668cb599f4-lsqkc -- /bin/bash


```