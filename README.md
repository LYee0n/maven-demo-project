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