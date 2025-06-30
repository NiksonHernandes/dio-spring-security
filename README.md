
# DIO - Spring Security

#### Autores
- [Nikson Hernandes](https://github.com/NiksonHernandes)


## 🔐 Autenticação com Spring Security usando Chave Pública e Privada (JWT)


Este projeto utiliza **Spring Security** com autenticação baseada em **JWT (JSON Web Token)**, garantindo segurança nas comunicações entre cliente e servidor. A assinatura dos tokens é feita com uma **chave privada**, enquanto a validação é realizada com a **chave pública**.

## 📌 Como funciona

1. O usuário realiza login com credenciais válidas.

2. O servidor gera um token JWT, **assinado digitalmente** com a chave **privada RSA**.
 
3. O cliente armazena esse token e o envia em todas as requisições protegidas no cabeçalho `Authorization: Bearer <token>`.

4. O backend valida o token usando a **chave pública RSA**.

5. Se válido, o acesso é concedido ao recurso.

## 🔐 Vantagens

* A assinatura com chave assimétrica (RSA) aumenta a segurança, pois a chave privada nunca é exposta.
* A verificação com chave pública permite a escalabilidade, podendo validar o token em múltiplos serviços sem compartilhar a chave privada.

## 🔧 Estrutura de Segurança

* `private.pem`: Chave privada usada para assinar tokens.
* `public.pem`: Chave pública usada para validar tokens.
* `JwtTokenProvider`: Classe responsável por gerar e validar tokens JWT.
* `JwtAuthenticationFilter`: Filtro que intercepta requisições e valida o token.
* `SecurityConfig`: Configura o Spring Security com as rotas públicas e protegidas.

## 🤔 Como gerar a chave pública e privada para minha aplicação?


![Logo Spring Security](https://spring.io/images/spring-security-5f8dc0979a36a2e7bdf7b8c8dd0c4dc2.svg)


