
# Desafio Pismo Backend
Este projeto de API foi criado com o objetivo de demonstrar as habilidades e conhecimentos necessários para um desenvolvedor backend. Ele serve como parte do processo seletivo, permitindo que o candidato mostre sua compreensão de conceitos fundamentais de desenvolvimento, arquitetura de software, boas práticas e ferramentas utilizadas no backend, como parte da avaliação conduzida pelo líder técnico e pelos desenvolvedores da Pismo.

# Arquitetura
> O desafio de desenvolvimento da API foi realizado utilizando a arquitetura hexagonal, uma abordagem que organiza o sistema de forma a manter o núcleo da aplicação — suas regras de negócio — totalmente independente das tecnologias externas, como bancos de dados ou integrações. Essa arquitetura, também conhecida como Ports and Adapters, permite criar sistemas mais flexíveis e fáceis de evoluir, garantindo que mudanças externas não impactem diretamente o coração do sistema.
> Na prática, a arquitetura organiza o código em três camadas principais:
> - Core (ou Domínio): onde estão as regras de negócio, sem dependências externas.
> - Ports: interfaces que representam pontos de entrada (input) ou saída (output) da aplicação.
> - Adapters: implementações concretas que conectam as ports às tecnologias externas, como bancos de dados, serviços externos, ou interfaces de usuário.
<div align="center">

![Arquitetura](https://www.arnaudlanglade.com/hexagonal-architecture-by-example/hexgonal-architecture-flow-control.svg "Arquitetura")


![](https://img.shields.io/badge/Autor-Roberto%20Gualberto%20dos%20Santos-brightgreen)
![](https://img.shields.io/badge/Language-Java-brightgreen)
![](https://img.shields.io/badge/Framework-SpringBoot-brightgreen)
![](https://img.shields.io/badge/Arquitetura-Hexagonal-brightgreen)

</div> 

<div align="center">

## SonarCloud
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=roberto5g_transactions-service2)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=ncloc)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=coverage)](https://sonarcloud.io/summary/new_code?id=roberto5g_transactions-service2)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=security_rating)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)

[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=bugs)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=code_smells)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=roberto5g_transactions-service2&metric=sqale_index)](https://sonarcloud.io/dashboard?id=roberto5g_transactions-service2)

</div>

##  Pré -requisitos

- [ `Java 17` ](https://www.oracle.com/java/technologies/downloads/#java11)
- [ `Docker` ](https://www.docker.com/)
- [ `Docker-Compose` ](https://docs.docker.com/compose/install/)

### Start da aplicação via script
- Na raiz do projeto execute o seguinte comando:
  ```
  /bin/bash start-service.sh
  ```
### Start da aplicação via IDE (IntellJ)
- Entre no diretorio dos docker `cd docker`
- Execute o comando:
  ```
  docker-compose up ts_database -d
  ```
- Em seguida inicie a aplicação pela IDE

## Links

- Swagger
    - http://localhost:8080/swagger-ui/index.html
- API Docs
    - http://localhost:8080/v3/api-docs
- Endpoints
  - [POST] http://localhost:8080/accounts
  - [POST] http://localhost:8080/transactions
  - [GET] http://localhost:8080/accounts/{accountId}

 