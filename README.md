# speed-demo

A Spring Boot app to test Api responsiveness in various environments (Docker, K8S, plain Java, etc)


## Docker

docker build --tag=speed-demo:latest .

docker run -p 8090:8090 speed-demo:latest

## Docker Compose

docker-compose build

docker-compose up --build

docker-compose down