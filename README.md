# 🚀 Cadastro Processo Infra
### Backend + Infraestrutura como Código + CI/CD na AWS

Projeto Full Cycle que implementa um **sistema de cadastro de usuários em produção real**, contemplando:

- ✅ Desenvolvimento do backend (Java / Spring Boot)
- ✅ Provisionamento completo da infraestrutura (IaC)
- ✅ Deploy automatizado (CI/CD)
- ✅ Segurança em nuvem
- ✅ Observabilidade e Health Checks
- ✅ Escalabilidade com Auto Scaling
- ✅ Banco de dados gerenciado (RDS)

Este repositório demonstra atuação **de ponta a ponta (Backend + Cloud + DevOps + Produção)** utilizando práticas modernas de engenharia.

---

## Objetivo

Construir um ambiente **100% reproduzível e automatizado**, simulando um cenário real de produção, onde:

- Toda infraestrutura é criada por código
- O deploy é automático via GitHub Actions
- Não existem credenciais expostas
- O sistema escala via Auto Scaling
- O backend é monitorado por health checks (Actuator)
- O ambiente pode ser destruído e recriado sob demanda

---

## Arquitetura da Solução

A infraestrutura foi projetada em **camadas modulares**, separando responsabilidades e facilitando manutenção e evolução.

### Fundação (Core Infra)
Base de rede e persistência.

- VPC
- Subnets públicas e privadas
- NAT Gateway
- Security Groups
- RDS PostgreSQL (em subnet privada)

### Aplicação (Compute)
Camada de execução do backend Java.

- Application Load Balancer (HTTPS)
- Auto Scaling Group
- EC2 (Spring Boot)
- Health Checks via Actuator
- Deploy automático do JAR

### Entrega (Frontend)
Distribuição do frontend estático.

- S3 (static hosting)
- CloudFront (CDN global)

### DNS
Domínio e certificados.

- Route53
- ACM (TLS/HTTPS)
- `api.asantanadev.com`

---

## Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot 3.3.x
- Spring Data JPA (Hibernate)
- Flyway (database migrations)
- PostgreSQL (produção)
- H2 (testes)
- Spring Boot Actuator (health checks)
- JUnit + Mockito (testes unitários)
- RestAssured (preparado para testes de API – em implementação)


### Cloud / DevOps
- AWS EC2
- AWS RDS
- AWS S3
- AWS CloudFront
- AWS Route53
- AWS CloudFormation (IaC)
- GitHub Actions (CI/CD)
- OIDC (autenticação sem chaves fixas)
- AWS Secrets Manager
- AWS SSM Parameter Store

---

### Resgistros em Vídeos do Sistema em Produção 

</p>
<p>
  <a href="https://drive.google.com/file/d/1sJT_CokPcVpBWL7ZCBRkoHsdlhFaPx9O/view?usp=sharing">
    🔗 Validação do Backend em Produção via AWS CLI (curl + ALB)
  </a>
</p>


<p>
  <a href="https://drive.google.com/file/d/1IBnUKaaWM9yztQNNHI0J0XfuOB0eS3Ri/view?usp=sharing">
    🔗 Validação do Backend em Produção via Postman + ALB
  </a>
</p>



</p>
<p>
  <a href="https://drive.google.com/file/d/1Qu2zgXYpkuJ_Tpve6S_RQiHEMMBqBNCy/view?usp=sharing">
    🔗 Validação do Backend em Produção (Postman/Newman via GitHub Actions) — DNS/Domínio + ALB
  </a>
</p>


## 📦 Backend (API REST)

### Base URL (Produção)
```txt
https://api.asantanadev.com


## 🧑‍💻 Autora

**Ana Santana**

📧 **Email:** anapedra.mil@gmail.com  
📱 **WhatsApp:** +55 31 99750-2148  
💼 **LinkedIn:** https://www.linkedin.com/in/anasantana  


