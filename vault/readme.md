


```sh
docker compose exec vault vault kv put secret/mysecret value=myvalue
docker compose exec vault vault kv get secret/mysecret

curl \
  -H "X-Vault-Token: hvs.0vHDJOohXXisBSaAVsdQQsSl" \
  --request POST \
  --data @secret.json \
  http://localhost:8200/v1/secret/data/mysecret

curl \
  -H "X-Vault-Token: hvs.0vHDJOohXXisBSaAVsdQQsSl" \
  http://localhost:8200/v1/secret/data/mysecret | jq '.data.data'

```