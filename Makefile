.PHONY: version
version: # versions
	mvn --version
	java --version

.PHONY: clean
clean: # clean artifacts
	mvn clean

.PHONY: fbuild
fbuild: # build without tests, no docker
	mvn -DskipTests package

.PHONY: start
start: clean erase build up show-port

.PHONY: stop
stop: erase clean

.PHONY: erase
erase:
	docker rm -f game-engine-service
	docker-compose down -v --rmi all

.PHONY: build
build: # validate, generate-sources, process-sources, generate-resources, process-resources, compile, docker package
	mvn package
	docker build -t game-engine-service -f Dockerfile .
	docker-compose build

.PHONY: up
up: # spin up environment, run the app
	docker-compose up -d

.PHONY: show-port
show-port:
	@echo
	@PORT=$$(docker port game-engine-service | awk -F ':' '{print $$NF}') ; \
	echo "\033[32mhttp://localhost:$$PORT/swagger-ui/index.html\033[0m"
