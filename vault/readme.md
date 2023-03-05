


```sh
docker compose exec vault vault kv put secret/mysecret value=myvalue
docker compose exec vault vault kv get secret/mysecret
```