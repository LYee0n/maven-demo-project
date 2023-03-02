```sh
docker build -t jyasu/demo-image:latest .
docker push jyasu/demo-image:latest

docker compose up -d

docker compose logs -f

```