


```sh
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/helloworld-ingress.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/helloworld-pod.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/helloworld-service.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/ingress-controller-deployment.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/ingress-example-1.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/ingress-example-2.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/ingress-example-3.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/ingress-ssl-sceret.yaml
wget https://raw.githubusercontent.com/zxcvbnius/k8s-30-day-sharing/master/Day19/demo-ingress/nginx-ingress.yaml

kubectl create -f ./ingress-example-1.yaml
kubectl create -f ./ingress-example-2.yaml
kubectl create -f ./helloworld-pod.yaml
kubectl create -f ./helloworld-service.yaml
curl https://raw.githubusercontent.com/kubernetes\
>/ingress-nginx/master/deploy/namespace.yaml \
>| kubectl apply -f -
curl https://raw.githubusercontent.com/kubernetes\
>/ingress-nginx/master/deploy/default-backend.yaml \
>| kubectl apply -f -
curl https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/configmap.yaml | kubectl apply -f -

curl https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/tcp-services-configmap.yaml | kubectl apply -f -

curl https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/udp-services-configmap.yaml | kubectl apply -f -
curl https://raw.githubusercontent.com/kubernetes\
>/ingress-nginx/master/deploy/without-rbac.yaml \
>| kubectl apply -f -
minikube addons enable ingress
minikube ip
sudo nano /etc/host
curl -vL $(minikube service apiserver --url)

```