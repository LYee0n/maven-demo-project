

[reference](https://github.com/comoser/snipe-it-docker-compose)


```sh
@jyasuu ➜ /workspaces/maven-demo-project/snipe-it (main) $ docker exec -it snipe-it-snipe-it-1 sh
# php artisan key:generate --show
base64:Y36Py9yji1FT+m4Oc8i1nsfCaITyKti8urqRtzAbQCY=
# exit
@jyasuu ➜ /workspaces/maven-demo-project/snipe-it (main) $ 
@jyasuu ➜ /workspaces/maven-demo-project/snipe-it (main) $ docker-compose down && docker-compose up -d --build
Stopping snipe-it-snipe-it-1 ... done
Stopping snipe-mysql         ... done
Removing snipe-it-snipe-it-1 ... done
Removing snipe-mysql         ... done
Removing network snipe-it_default
Creating network "snipe-it_default" with the default driver
Creating snipe-mysql ... done
Creating snipe-it_snipe-it_1 ... done
@jyasuu ➜ /workspaces/maven-demo-project/snipe-it (main) $ 

```