


```sh
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day18/demo-configmap/apiserver-pod.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day18/demo-configmap/my-nginx.conf
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day18/demo-configmap/my-pod.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day18/demo-configmap/my-redis.conf

kubectl create configmap redis-config --from-file=my-redis.conf
kubectl delete configmap mysql-host
kubectl create configmap nginx-conf --from-file=./my-nginx.conf
kubectl create -f ./my-pod.yaml
kubectl expose pod apiserver --port=80 --type=NodePort
minikube service apiserver --url
curl -vL $(minikube service apiserver --url)

```