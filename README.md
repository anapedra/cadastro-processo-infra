# 🚀 Cadastro Processo Infra

Este projeto contém a infraestrutura completa como código (IaC) e as esteiras de CI/CD para o ecossistema do Cadastro de Usuários. A arquitetura foi desenhada de forma modular na AWS para garantir escalabilidade, segurança e automação total.

---

## 🏗️ Arquitetura de Infraestrutura

A infraestrutura é provisionada via **AWS CloudFormation** e está dividida em camadas lógicas para facilitar a manutenção:

* **Fundação**: Configuração de rede (VPC, Subnets), Banco de Dados (RDS) e Security Groups.
* **Aplicação**: Gerenciamento do Load Balancer (ALB) e Auto Scaling Group (ASG) para o backend Java.
* **Entrega**: Bucket S3 para o frontend estático e distribuição global via CloudFront.
* **DNS**: Gestão de domínios e certificados SSL via Route53.


---

## 🛠️ Tecnologias Utilizadas

* **Cloud**: AWS (EC2, RDS, S3, CloudFront, Route53, SSM).
* **IaC**: CloudFormation (YAML).
* **CI/CD**: GitHub Actions com autenticação OIDC (Segurança sem chaves fixas).
* **Backend**: Java 17 / Spring Boot.
* **Segurança**: AWS Secrets Manager & Parameter Store.

---

## 🤖 Automação de Deploy (CI/CD)

O pipeline de deploy está configurado no arquivo `.github/workflows/deploy-infra.yml`. Ele segue um fluxo rigoroso de promoção de código:

1.  **Trigger**: O deploy para produção ocorre exclusivamente através de merge na branch `main`.
2.  **Orquestração**:
    * `fundacao` -> `aplicacao` -> `backend` -> `entrega` -> `dns`.
3.  **Segurança**: Utiliza o **GitHub OIDC** para assumir Roles temporárias na AWS, eliminando o uso de `AWS_ACCESS_KEY` estáticas.

---

