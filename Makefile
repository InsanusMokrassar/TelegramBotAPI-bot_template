#!make

.ONESHELL:
.PHONY:

clean:
	./gradlew clean

build:
	./gradlew build

start:
	./gradlew run

startCompose:
	docker-compose build && docker-compose up

buildAndStartCompose:
	make clean build startCompose
