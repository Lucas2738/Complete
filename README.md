# Build with maven projects images
in home folder:
	- launch "docker-compose up" for single docker instance
	- launch "docker stack deploy --compose-file docker-compose.yml STACK" for Docker Swarm cluster
	- run ./docker-master-build.sh for Docker Swarm Cluster

Docker Swarm

# Init the swarm
docker swarm init --advertise-addr 192.168.1.124


# retrieve the join command for node worker
docker swarm join-token worker

# the result will be similar to
docker swarm join --token SWMTKN-1-2xvspgo7ogomak3arfu5inewttqaypdfrw79w6h7v4h9kjyja1-5ny97psfkg0erqst95uv070vg 192.168.1.124:2377

# start portainer
docker stack deploy -c portainer-agent-stack.yml portainer

# or start portainer in this way
sudo docker run -d -p 8000:8000 -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer


# run stack from docker-compose
sudo docker stack deploy --compose-file docker-compose.yml STACK

# remove all stack
sudo docker stack rm STACK

# create folder with following files for launch scripts for DBs initialization in any worker node because docker compose will search scripts in these path:
	/home/luca/Public/Dev/Projects/demo/scripts/schema.sql
	/home/luca/Public/Dev/Projects/demo/scripts/rxschema.sql
	
# create folder /home/luca/Public/Dev/Projects/logs/demo because demo app in docker compose mount a volume for logging in this path


# see containers
sudo docker ps

# exe bash inside container and navigate container file system
docker exec -it 7a99aabc0135 /bin/sh

# extends memory for elasticsearch container
sudo sysctl -w vm.max_map_count=262144

# reload imager and restart container into stack named STACK
./docker-master-build.sh <PRJ_FOLDER_NAME>

ex. ./docker-master-build.sh demo

# run portainer container
docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock portainer/portainer


The following instruction has been executed in order to generate certificate using JDK8

# generate demo_keystore.p12 with private and public key for Demo
/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/keytool -genkeypair -alias demoKeyPair -keyalg RSA -keysize 2048 \
  -dname "CN=Demo" -validity 365000 -storetype PKCS12 \
  -keystore demo_keystore.p12 -storepass demopwd

# generate demo_certificate.cer with certificate that contains public key of Demo
 /usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/keytool -exportcert -alias demoKeyPair -storetype PKCS12 \
  -keystore demo_keystore.p12 -file \
  demo_certificate.cer -rfc -storepass demopwd

# read certificate and generate demo_cert_keystore.p12 containing Demo public key
 /usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/keytool -importcert -alias demoCertKeyPair -storetype PKCS12 \
  -keystore demo_cert_keystore.p12 -file \
  demo_certificate.cer -rfc -storepass democlientpwd


# generate demo_client_keystore.p12 with  private and public key for DemoClient
/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/keytool -genkeypair -alias demoClientKeyPair -keyalg RSA -keysize 2048 \
  -dname "CN=DemoClient" -validity 365000 -storetype PKCS12 \
  -keystore demo_client_keystore.p12 -storepass democlientpwd

# generate demo_client_certificate.cer with certificate that contains public key of DemoClient
 /usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/keytool -exportcert -alias demoClientKeyPair -storetype PKCS12 \
  -keystore demo_client_keystore.p12 -file \
  demo_client_certificate.cer -rfc -storepass democlientpwd

# read certificate and generate demo_client_cert_keystore.p12 containing DemoClient public key
 /usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/keytool -importcert -alias demoClientCertKeyPair -storetype PKCS12 \
  -keystore demo_client_cert_keystore.p12 -file \
  demo_client_certificate.cer -rfc -storepass demopwd

# Kafka
  when starting Kafka delete the content of folder /kafka/k1-logs/ (the volume is comment in docker-compose.yml)

  if starting demo and demo-client in IDE change this line
    KAFKA_ADVERTISED_LISTENERS: INSIDE://kafka:9092,OUTSIDE://kafka:9093
  with this
    KAFKA_ADVERTISED_LISTENERS: INSIDE://kafka:9092,OUTSIDE://localhost:9093
  otherwise demo and demo-client will not reach kafka. This change causing kafdrop not working.

