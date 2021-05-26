if [ -z "$1" ]
then
	docker stack deploy --compose-file docker-compose.yml STACK
    exit
else
    IMAGE=$1
fi


docker service rm STACK_$IMAGE

docker rmi $IMAGE
cd $IMAGE && docker build -t $IMAGE .

echo ""
echo " -- Rebuild on NODE 1 ---> Complete"
echo ""

echo ""
echo " -- Deployng Docker Stack ..."
echo ""

docker stack deploy STACK --compose-file docker-compose.yml

echo ""
echo " -- Done"
