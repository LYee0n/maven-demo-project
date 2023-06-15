# maven-demo-project


```
mvn archetype:generate \
  -DgroupId=com.demo \
  -DartifactId=demo-maven-plugin \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-plugin
```

```
docker compose up -d
```

```
mvn jooq-codegen:generate
```

```
mvn demo:touch
mvn demo:touch -Dsayhi.greeting=?
```

echo "deb [arch=amd64 signed-by=/usr/share/keyrings/oracle-virtualbox-2016.gpg] https://download.virtualbox.org/virtualbox/debian bionic contrib" > virtual.list
 
sudo cp virtual.list /etc/apt/sources.list.d/


 wget -O- https://www.virtualbox.org/download/oracle_vbox_2016.asc | sudo gpg --dearmor --yes --output /usr/share/keyrings/oracle-virtualbox-2016.gpg

 sudo apt update 

 sudo apt install virtualbox

 curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube_latest_amd64.deb

sudo dpkg -i minikube_latest_amd64.deb


curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"

sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl

minikube start

minikube service wordpress-service --url


sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

  sudo chmod a+r /etc/apt/keyrings/docker.gpg
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin

sudo service docker start


# Add Storybook:
npx storybook init

npm run storybook



1. Install the Budibase CLI:

npm i -g @budibase/cli

2. Setup Budibase (select where to store Budibase, and the port to run it on):

budi hosting --init

3. Run Budibase:

budi hosting --start

4. Create your admin user by entering the email and password for the new admin user.


## chatGPT


https://beta.openai.com/docs/api-reference/introduction

curl https://api.openai.com/v1/models \
  -H 'Authorization: Bearer YOUR_API_KEY' \
  -H 'OpenAI-Organization: org-F3dDuF3Xo4CqWqVxBXC4Nq3i'

  curl https://api.openai.com/v1/completions \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_API_KEY" \
-d '{"model": "text-davinci-003", "prompt": "Say this is a test", "temperature": 0, "max_tokens": 7}'



curl -s https://api.openai.com/v1/completions -H "Content-Type: application/json" -H "Authorization: Bearer YOUR_API_KEY" -d '{"model": "text-davinci-003", "prompt": "what about object storage vs file system vs ftp", "temperature": 0, "max_tokens": 100}' | jq .choices



https://ithelp.ithome.com.tw/articles/10207246
https://www.digitalocean.com/community/tutorials/how-to-use-vault-to-protect-sensitive-ansible-data
https://www.51cto.com/article/708612.html
https://www.junmajinlong.com/ansible/12_safer_ansible/

ansible-vault create vault
ansible-vault edit vault

ansible-playbook  site.yml

ansible-galaxy install -r requirements.yml --force

 ansible-playbook playbook.yaml -i inventories/production/hosts --vault-password-file .vault_passwd

 ansible-playbook playbook.yaml -i inventories/test/hosts --vault-password-file .vault_passwd

## sqlite

 sqlite3 testDB.db < testDB.sql

## terraform

curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

sudo apt-get update && sudo apt-get install -y gnupg software-properties-common

wget -O- https://apt.releases.hashicorp.com/gpg | \
    gpg --dearmor | \
    sudo tee /usr/share/keyrings/hashicorp-archive-keyring.gpg

gpg --no-default-keyring \
    --keyring /usr/share/keyrings/hashicorp-archive-keyring.gpg \
    --fingerprint

echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] \
    https://apt.releases.hashicorp.com $(lsb_release -cs) main" | \
    sudo tee /etc/apt/sources.list.d/hashicorp.list

    sudo apt-get install terraform

    terraform -help

    wget https://releases.hashicorp.com/terraform-provider-aws/4.49.0/terraform-provider-aws_4.49.0_linux_amd64.zip

    
unzip terraform-provider-aws_4.49.0_linux_amd64.zip
mkdir -p ~/.terraform.d/plugins/linux_amd64/
mv terraform-provider-aws_v4.49.0_x5 ~/.terraform.d/plugins/linux_amd64/



export PROVIDER={all,google,aws,kubernetes}

export PROVIDER=aws
curl -LO https://github.com/GoogleCloudPlatform/terraformer/releases/download/$(curl -s https://api.github.com/repos/GoogleCloudPlatform/terraformer/releases/latest | grep tag_name | cut -d '"' -f 4)/terraformer-${PROVIDER}-linux-amd64
chmod +x terraformer-${PROVIDER}-linux-amd64
sudo mv terraformer-${PROVIDER}-linux-amd64 /usr/local/bin/terraformer

  terraformer import aws list


 terraformer import aws --regions=us-west-2 --resources=accessanalyzer,acm,alb,api_gateway,appsync,auto_scaling,batch,budgets,cloud9,cloudformation,cloudfront,cloudhsm,cloudtrail,cloudwatch,codebuild,codecommit,codedeploy,codepipeline,cognito,config,customer_gateway,datapipeline,devicefarm,docdb,dynamodb,ebs,ec2_instance,ecr,ecrpublic,ecs,efs,eip,eks,elastic_beanstalk,elasticache,elb,emr,eni,es,firehose,glue,iam,igw,iot,kinesis,kms,lambda,logs,media_package,media_store,msk,nacl,nat,opsworks,organization,qldb,rds,redshift,resourcegroups,route53,route_table,s3,secretsmanager,securityhub,servicecatalog,ses,sfn,sg,sns,sqs,ssm,subnet,swf,transit_gateway,vpc,vpc_peering,vpn_connection,vpn_gateway,waf,waf_regional,wafv2_cloudfront,wafv2_regional,workspaces,xray

 terraformer import aws --regions=us-west-2 --resources=s3

  --filter=vpc=myvpcid 


flock /tmp/demo.lock sleep 10 && date



https://galaxy.ansible.com/community/kubernetes
https://kubernetes.io/blog/2019/03/15/kubernetes-setup-using-ansible-and-vagrant/
https://faun.pub/how-to-create-your-own-kubernetes-cluster-using-ansible-7c6b5c031a5d
https://buildvirtual.net/deploy-a-kubernetes-cluster-using-ansible/
https://ithelp.ithome.com.tw/articles/10205550


https://fly.io/docs/languages-and-frameworks/golang/

https://render.com/

https://dashboard.render.com/

https://jyasu-demo.onrender.com

https://jyasu-demo3.onrender.com



https://developer.hashicorp.com/vagrant/downloads

 wget -O- https://apt.releases.hashicorp.com/gpg | gpg --dearmor | sudo tee /usr/share/keyrings/hashicorp-archive-keyring.gpg
 echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/hashicorp.list
 sudo apt update && sudo apt install vagrant

 sudo apt install virtualbox



https://github.com/openjdk/jmh
https://dafengge0913.github.io/jmh/
https://hezhiqiang8909.gitbook.io/java/docs/javalib/jmh
https://www.baeldung.com/java-microbenchmark-harness

https://cftang0827.medium.com/%E4%BD%BF%E7%94%A8%E8%87%AA%E5%BB%BAnexus-server%E4%BD%9C%E7%82%BAnpm%E7%9A%84registry-%E5%80%89%E5%BA%AB-e48327d47309


```
## username:password
echo -n 'admin:admin123' | openssl base64 # YWRtaW46YWRtaW4xMjM=
```

```
email=you@example.com
always-auth=true
_auth=YWRtaW46YWRtaW4xMjM=
```

wp-config.php
```php
define('WP_HOME','http://yoursitename.com');

define('WP_SITEURL','http://yoursitename.com');
```


https://developer.wordpress.org/rest-api/using-the-rest-api/
https://wordpress.com/learn/

https://www.digitalocean.com/community/tutorials/how-to-install-drupal-with-docker-compose

```
wget https://github.com/liquibase/liquibase/releases/download/v4.19.0/liquibase-4.19.0.tar.gz
tar -xvf liquibase-4.19.0.tar.gz 
```

```
docker exec -it maven-demo-project-main-db-1 bash
sudo postgres
psql
\dn
SET search_path TO liquibase_source;
\d account
SET search_path TO liquibase_target;
\d account
```

insert into "account" (
  "name", 
  "password",
  "email"
)
values (
  'asd', 
  'asd', 
  'asd1'
), (
  'bas', 
  'bas', 
  'asd1'
);

./liquibase --diffTypes=tables,columns,data --changelog-file=myChangelog.xml generate-changelog
./liquibase --diffTypes=tables,columns,data --dataOutputDirectory=myData --changelog-file=output_changelog.xml generate-changelog

./liquibase --url=jdbc:postgresql://localhost:5432/postgres?currentSchema=liquibase_source --username=postgres --password=postgres --referenceUrl=jdbc:postgresql://localhost:5432/postgres?currentSchema=liquibase_target --referenceUsername=postgres --referencePassword=postgres diffChangeLog

./liquibase --url=jdbc:postgresql://localhost:5432/postgres?currentSchema=liquibase_source --username=postgres --password=postgres --referenceUrl=jdbc:postgresql://localhost:5432/postgres?currentSchema=liquibase_target --referenceUsername=postgres --referencePassword=postgres --changeLogFile=data_inserts.xml diffChangeLog



liquibase.command.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=liquibase_target

wget https://get.helm.sh/helm-v3.11.0-linux-amd64.tar.gz
tar -xvf helm-v3.11.0-linux-amd64.tar.gz 
sudo ln -s ./linux-amd64/helm /usr/bin/helm

https://cwhu.medium.com/kubernetes-helm-chart-tutorial-fbdad62a8b61

dd if=/dev/zero of=100MB.test bs=1MB count=100

dd if=/dev/zero of=100MB.test bs=1MB count=2


docker run -it -v $PWD:/e2e -w /e2e cypress/included:8.6.0


https://github.com/cypress-io/cypress-example-docker-compose

https://functional.style/cypress/docker-compose/

https://webapp.io/blog/running-cypress-tests-in-parallel-using-docker-compose/


Stable Diffusion 1.4

stable_diffusion_webui_colab

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_webui_colab.ipynb

Stable Diffusion 1.5

stable_diffusion_1_5_webui_colab

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_1_5_webui_colab.ipynb

Stable Diffusion 1.5 Inpainting

stable_diffusion_inpainting_webui_colab

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_inpainting_webui_colab.ipynb

Stable Diffusion 2.1

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_v2_1_webui_colab.ipynb

Stable Diffusion 2.1 Base

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_v2_1_base_webui_colab.ipynb

Stable Diffusion 2.0

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_v2_webui_colab.ipynb

Stable Diffusion 2.0 Base

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_v2_base_webui_colab.ipynb

Stable Diffusion 2.0 Depth

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_v2_depth_webui_colab.ipynb

Stable Diffusion 2.0 Inpainting

https://colab.research.google.com/github/camenduru/stable-diffusion-webui-colab/blob/main/stable_diffusion_v2_inpainting_webui_colab.ipynb





wget https://github.com/schemaspy/schemaspy/releases/download/v6.1.0/schemaspy-6.1.0.jar
wget https://jdbc.postgresql.org/download/postgresql-42.5.4.jar

java -jar schemaspy-6.1.0.jar -t yaml  -dp ./postgresql-42.5.4.jar -db postgres -host localhost -port 5432 -s public -u postgres -p postgres -o outputs


pg_dump --schema-only --format=yaml postgres > schema.yaml


```yaml
# https://blog.tocandraw.com/2023/02/28/virtualization/docker/503/timhsu/?fbclid=IwAR2QhL77RUMKwlI7OwNVrSODZCcVlgWGWFnW7eKYfJHf3heqVb3CRYRtez4
- name: Check docker hub remaining pull limit
  block:
      - name: Get temp docker token
        ansible.builtin.uri:
            url: https://auth.docker.io/token?service=registry.docker.io&scope=repository:ratelimitpreview/test:pull
            user: "{{ github_user }}"
            password: "{{ docker_password }}"
            method: GET
            return_content: true
        register: http_response

      - name: Parse token
        ansible.builtin.set_fact:
            temp_docker_token: "{{ http_response.content | from_json | json_query('token') }}"

      - name: Get remaining
        ansible.builtin.uri:
            url: https://registry-1.docker.io/v2/ratelimitpreview/test/manifests/latest
            headers:
                Authorization: "Bearer {{ temp_docker_token }}"
            method: GET
            return_content: true
        register: http_response_remaining

      - name: Parse remaining
        ansible.builtin.set_fact:
            docker_remaining: "{{ http_response_remaining.ratelimit_remaining | split(';') }}"

      - name: Use docker hub
        ansible.builtin.set_fact:
            image_owner: "{{ docker_user }}"
            use_registry: "docker"
        when: docker_remaining[0] | int > 10

      - name: Use github container registry
        ansible.builtin.set_fact:
            image_owner: ghcr.io/toc-taiwan
            use_registry: "github"
        when: docker_remaining[0] | int < 10

      - name: Decide registry
        ansible.builtin.debug:
            msg: "Use {{ image_owner }}, remaining {{ docker_remaining[0] }}"

```

https://blog.wu-boy.com/2023/02/automating-docker-container-base-image-updates-cht/?fbclid=IwAR3QyVzKhmQr_G9tcXqRbAZR20-r262eTZKV0hCPoIHSuUULo3bU64_nWuU

https://github.com/containrrr/watchtower




https://tw.coderbridge.com/series/7bc8a0e872fd4ca9b9c49032cc0de0d6/posts/3e0cb19b020e4716b25a7ed30fe047f2

https://medium.com/starbugs/%E5%AF%86%E7%A2%BC%E9%83%BD%E6%94%BE-vault-%E8%A3%A1%E4%BA%86-%E7%84%B6%E5%BE%8C%E5%91%A2-97c7832d59f

https://www.readfog.com/a/1676022748967702528

vault k8s injection 


https://thiscute.world/posts/experience-of-vault/
https://developer.hashicorp.com/vault/docs/platform/k8s/injector/examples
https://developer.hashicorp.com/vault/tutorials/kubernetes/kubernetes-sidecar
https://developer.hashicorp.com/vault/docs/platform/k8s/helm/run
https://developer.hashicorp.com/vault/docs/platform/k8s/injector



stable diffusion tags

https://tags.novelai.dev/



try ansible vault

https://docs.ansible.com/ansible/latest/collections/community/hashi_vault/vault_read_module.html


```yaml
- name: Read a kv2 secret from Vault via the remote host with userpass auth
  community.hashi_vault.vault_read:
    url: https://vault:8201
    path: secret/data/hello
    auth_method: userpass
    username: user
    password: '{{ passwd }}'
  register: secret

- name: Display the secret data
  ansible.builtin.debug:
    msg: "{{ secret.data.data.data }}"

- name: Retrieve an approle role ID from Vault via the remote host
  community.hashi_vault.vault_read:
    url: https://vault:8201
    path: auth/approle/role/role-name/role-id
  register: approle_id

- name: Display the role ID
  ansible.builtin.debug:
    msg: "{{ approle_id.data.data.role_id }}"
```



install ngrok

```sh
curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | sudo tee /etc/apt/sources.list.d/ngrok.list && sudo apt update && sudo apt install ngrok
```

ngrok http 5134

https://timberwolf-mastiff-9776.twil.io/demo-reply

curl http://localhost:5134/Twilio -X POST \
--data-urlencode 'To=whatsapp:+886912562697' \
--data-urlencode 'From=whatsapp:+14155238886' \
--data-urlencode 'Body=Your appointment is coming up on July 21 at 3PM'



curl https://lyee0n-stunning-pancake-vxvjwgvj79vcpvjp-5134.preview.app.github.dev/Twilio -X POST \
--data-urlencode 'To=whatsapp:+886912562697' \
--data-urlencode 'From=whatsapp:+14155238886' \
--data-urlencode 'Body=Your appointment is coming up on July 21 at 3PM'




https://github.com/dotnet/AspNetCore.Docs/tree/main/aspnetcore/fundamentals/websockets/samples/7.x/WebSocketsSample

https://medium.com/swlh/creating-a-simple-real-time-chat-with-net-core-reactjs-and-signalr-6367dcadd2c6




https://apisix.apache.org/

https://velero.io/


https://blog.miniasp.com/post/2023/04/21/Running-Keycloak-in-development-mode?full=1&fbclid=IwAR0QqkBgrFbPtRmh2-zGYg7ODP8PK47KHAQz4Bp8zk_AsU4FT-iIlhHpcTU

https://medium.com/geekculture/using-vault-agent-with-docker-compose-f410d033026f
