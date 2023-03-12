


```sh

docker build . -t jyasu/hellok8s:v1
docker images

docker run -p 3000:3000 --name hellok8s -d jyasu/hellok8s:v1

curl localhost:3000 

docker push jyasu/hellok8s:v1

kubectl apply -f nginx.yaml        
# pod/nginx-pod created

kubectl get pods
# nginx-pod         1/1     Running   0           6s

kubectl port-forward nginx-pod 4000:80
# Forwarding from 127.0.0.1:4000 -> 80
# Forwarding from [::1]:4000 -> 80

curl localhost:4000

kubectl exec -it nginx-pod /bin/bash

echo "hello kubernetes by nginx!" > /usr/share/nginx/html/index.html

kubectl port-forward nginx-pod 4000:80

kubectl logs -f nginx-pod
                              
kubectl exec nginx-pod -- ls

kubectl delete pod nginx-pod
# pod "nginx-pod" deleted

kubectl delete -f nginx.yaml
# pod "nginx-pod" deleted

kubectl apply -f hellok8s.yaml

kubectl get pods

kubectl port-forward hellok8s 3000:3000

```

