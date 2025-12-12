# 🍦 Sorveteria Ice Vanilla Web

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)

## 📖 Descrição

O **Sorveteria Ice Vanilla Web** é um sistema web desenvolvido para facilitar a gestão de uma sorveteria. O objetivo principal da aplicação é permitir o controle eficiente de clientes, o gerenciamento do catálogo de sorvetes e o registro de vendas.

O projeto foi construído utilizando a arquitetura MVC (Model-View-Controller) com Spring Boot, garantindo um código organizado, escalável e de fácil manutenção. O front-end utiliza **Thymeleaf** para renderização dinâmica das páginas HTML.

## 🚀 Tecnologias Utilizadas

### Back-end
* **Java**: Linguagem principal do projeto.
* **Spring Boot**: Framework para facilitar a configuração e o desenvolvimento.
* **Spring Web**: Para criar a aplicação web, incluindo RESTful e MVC.
* **Spring Data JPA**: Para persistência de dados e abstração de repositórios.
* **MySQL Driver**: Conector para o banco de dados MySQL.
* **Lombok**: Para reduzir a verbosidade do código (Getters, Setters, Construtores).
* **Validation**: Para validação de dados nos DTOs e Entidades.
* **Spring Boot DevTools**: Para recarregamento automático durante o desenvolvimento.
* **Maven**: Gerenciamento de dependências e build.

### Front-end
* **Thymeleaf**: Template engine para integrar Java com HTML.
* **HTML5 & CSS3**: Estrutura e estilização das páginas.
* **JavaScript**: Interatividade nas páginas.

### Ferramentas
* **NetBeans**: IDE utilizada para o desenvolvimento.
* **MySQL Workbench**: Utilizado para modelagem e administração do banco de dados.

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura em camadas bem definida para separar responsabilidades:

```
src/main/java
└── com.seuprojeto.icevanilla
    ├── controller   # Camada que recebe as requisições HTTP e direciona o fluxo
    ├── dto          # Data Transfer Objects (Objetos para transporte de dados entre camadas)
    ├── model        # Entidades JPA que representam as tabelas do banco de dados
    ├── repository   # Interfaces que estendem JpaRepository para acesso ao banco
    └── service      # Regras de negócio da aplicação
```

## ✨ Funcionalidades Principais

* **Gerenciamento de Clientes**: Cadastro, listagem e atualização de informações dos clientes.

* **Catálogo de Sorvetes**: Cadastro de novos sabores, preços e descrição dos produtos.

* **Registro de Vendas**: Funcionalidade para registrar a venda de sorvetes para os clientes cadastrados.

* **Interface Amigável**: Telas responsivas e dinâmicas utilizando Thymeleaf.


## ⚙️ Pré-requisitos e Configuração

Para rodar este projeto localmente, você precisará ter instalado:

* Java JDK 21 ou superior

* Maven

* MySQL Server e MySQL Workbench

* NetBeans (opcional, mas recomendado)


### Configurando o Banco de Dados
* Abra o MySQL Workbench.

* Crie um banco de dados (schema) com o nome de sua preferência (ex: icevanilla_db).

* No arquivo src/main/resources/application.properties, configure as credenciais (configure o username e a senha com suas credenciais do MySQL):

> Configuração do Banco de Dados
```
spring.application.name=IceVanilla
spring.datasource.url=jdbc:mysql://localhost:3306/ice_vanilla_db
spring.datasource.username=root
spring.datasource.password=
````
> Configurações do JPA
```
server.port=8080
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
server.error.include-message=Always
spring.jpa.show-sql=true
spring.web.locale=pt_BR
spring.web.locale-resolver=fixed
```


## ▶️ Executando o Projeto

1. Clone o repositório ou abra a pasta do projeto no **NetBeans**.

2. Aguarde o Maven baixar todas as dependências listadas no pom.xml.

3. Execute a classe principal (IceVanillaApplication.java).

4. O sistema estará acessível em: http://localhost:8080.


## 💡 Possíveis Melhorias

Futuras funcionalidades planejadas para evoluir o sistema:

* **Autenticação e Autorização (Spring Security)**: Implementar login para administradores e funcionários.

* **Controle de Estoque**: Deduzir automaticamente a quantidade de sorvetes disponíveis após uma venda.

* **Relatórios (Dashboard)**: Gráficos de vendas diárias e sorvetes mais vendidos.

* **Exportação**: Gerar comprovantes de venda em PDF.


## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## 📝 Licença
Este projeto é desenvolvido para fins educacionais.
