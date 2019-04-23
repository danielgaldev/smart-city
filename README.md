

## Install
#### Pre-requisites
* Docker (Docker for Windows, Docker for Mac)
* JDK 1.8

Navigate to the project directory and run this command:
```
docker build . -t jasonbuilder
```

## Run
### Build Jason project
```
docker run -v "$(pwd):/code" jasonbuilder <your-mas2j-file>.mas2j
```
### Execute Jason project
```
java -jar <your-jar-file>.jar
```
