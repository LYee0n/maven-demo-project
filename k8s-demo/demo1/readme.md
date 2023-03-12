


```sh

docker build . -t jyasu/hellok8s:v1
docker images

docker run -p 3000:3000 --name hellok8s -d jyasu/hellok8s:v1

curl localhost:3000 

docker push jyasu/hellok8s:v1

```