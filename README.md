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