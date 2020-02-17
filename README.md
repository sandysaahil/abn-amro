# ABN-AMRO
Technical challenge for ABN-AMRO

### Prerequisites

1. Git - https://git-scm.com/
2. Docker - If planning to run as an docker image - https://www.docker.com/
3. Application can be run without docker as well. In that case Java and Maven need to be installed before running the application.

### Commands to run the project

1. Clone the application

`cmd> git clone https://github.com/sandysaahil/abn-amro.git`

Followig command will build the docker image and start the container

`project root> docker image build -t abn-amro . && docker container run -p 8080:8080 abn-amro`

This will build the application and run the application. It will take a while (5-7 minutes) to build and start the container. Once hosted the application will be available at http://localhost:8080/v1/report

### Output looks like

![Output for Daily transaction report](https://github.com/sandysaahil/abn-amro/blob/master/src/main/resources/static/Screenshot%202020-02-17%20at%204.45.35%20PM.png)


### API Documentation (Swagger)
API documentation is available at (once application is deployed) - http://locahost:8080/swagger-ui.html


### Assumptions

1. Input.txt will be available locally. Though the application is written in the way that later external files can be used. As of now no support for external files.
2. Structure of the input file will not change as the parsing logic depends on the index of elements.

