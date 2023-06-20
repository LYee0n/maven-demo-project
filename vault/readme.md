


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


docker compose exec vault sh

export VAULT_TOKEN=hvs.oChRuGDiXOLX37MXIvvqiGB2

vault secrets enable ssh

vi a.json
{
  "key_type": "otp",
  "default_user": "default",
  "cidr_list": "0.0.0.0/0",
  "port": 22,
  "key": "rsa",
  "ttl": "1h",
  "max_ttl": "24h"
}

vault write ssh/roles/otp_role @a.json


```