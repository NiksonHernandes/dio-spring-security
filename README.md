
# DIO - Spring Security

## 🧑🏻‍🎓 Autor:

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

#### Com Linux ou sistema MacOs:   
1. Acesse [CryptoTools.net](https://cryptotools.net/rsagen) e gere a chave pública e privada.

#### Com Windows, vamos usar o Ubuntu via WSL:
1. Baixar o Ubunto WSL na sua máquina.

2. Gerar a chave privada `.pem`:

   `openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048`

- Isso cria a chave **privada** no formato **PKCS#8**, ideal para usar com JWT e Java.

3. Gerar a chave pública `.pem`:

   `openssl rsa -pubout -in private_key.pem -out public_key.pem`

- Isso extrai a chave **pública** do arquivo `.pem` anterior

#### Copiar os arquivos para seu projeto 
1. Você pode acessar as chaves em /home/SEU_USUARIO/ ou onde você estiver.

2. Ou copie para sua pasta do projeto com (Caminho de exemplo: *C:/Users/Acer*):

  `cp private_key.pem /mnt/c/Users/Acer/`

  `cp public_key.pem /mnt/c/Users/Acer/`

* Isso copiará as chaves para o caminho de destinho *C:/Users/Acer*

🟥 *Se precisar de ajuda para carregar as chaves no Spring Boot ou converter formatos, consulte a documentação do projeto ou entre em contato.*


## Contributing

Contribuições são bem-vindas!

Veja `README.md` para mais informações e comece a contruibuir.



## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nikson-hernandes-55492b207/)

