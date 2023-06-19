


```sh
docker compose exec vault vault kv put secret/mysecret value=myvalue
docker compose exec vault vault kv get secret/mysecret

curl \
  -H "X-Vault-Token: hvs.OPvskH4NsPXuIMhmfYmxB0NN" \
  --request POST \
  --data @secret.json \
  http://localhost:8200/v1/secret/data/mysecret

curl \
  -H "X-Vault-Token: hvs.OPvskH4NsPXuIMhmfYmxB0NN" \
  http://localhost:8200/v1/secret/data/mysecret | jq '.data.data'

```


```sh
# vault gui cli
read secret/data/mysecret

ansible-playbook playbook.yaml
```