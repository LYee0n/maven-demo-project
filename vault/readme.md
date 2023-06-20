


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


wget https://releases.hashicorp.com/vault-ssh-helper/0.2.0/vault-ssh-helper_0.2.0_linux_amd64.zip

unzip vault-ssh-helper_0.2.0_linux_amd64.zip 

sudo mv vault-ssh-helper /usr/local/bin

vault write ssh/roles/otp_key_role key_type=otp default_user=ubuntu cidr_list=0.0.0.0/0

vault read -format=json ssh/creds/otp_role

vault write ssh/creds/otp_key_role ip=10.25.81.248
vault write ssh/creds/otp_key_role ip=172.17.0.1
vault write ssh/creds/otp_key_role ip=172.16.5.4

vault ssh -role otp_key_role -mode otp -strict-host-key-checking=no ubuntu@10.25.81.248

```