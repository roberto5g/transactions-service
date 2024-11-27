#!/bin/bash

cd ./docker

echo "Building the project..."
sh build.sh

echo "Removing specific containers..."
docker rm ts_database ts_service

echo "Removing images for ts_service (if applicable)..."
docker rmi $(docker images -q ts_service) 2>/dev/null

echo "Starting new containers..."
docker-compose up -d --build

echo "Waiting for 'ts_service' to be fully started..."
api_container=""
while [ -z "$api_container" ]; do
    sleep 2
    echo -n "."
    api_container=$(docker ps --filter "name=ts_service" --filter "status=running" -q)
done
echo ""
echo "'ts_service' is now running."

# Aguardar até que a API esteja pronta (opcionalmente, verificar uma URL de saúde)
host_port=$(docker port "$api_container" 8080 | cut -d ':' -f2)

echo "Checking if the API is responding..."
until curl -s "http://localhost:$host_port/actuator/health" | grep -q '"status":"UP"'; do
    echo -n "."
    sleep 3
done
echo ""
echo "API is now ready!"

echo ""
echo "====================================================="
echo "API is now running!"
echo "You can access it using the following endpoints:"
echo ""
echo "API Base URL: http://localhost:$host_port"
echo "Health Check: http://localhost:$host_port/actuator/health"
echo "Swagger UI:   http://localhost:$host_port/swagger-ui/index.html"
echo ""
echo "====================================================="
