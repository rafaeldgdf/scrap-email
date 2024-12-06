
# **Scrap Email Application**

## **Descrição**
Este é um projeto em Java utilizando Spring Boot que realiza web scraping de um site e envia os dados extraídos por email usando o protocolo SMTP (Gmail).

### **Principais Funcionalidades**
1. Extrai informações de títulos de livros de um site fictício de testes: [Books to Scrape](http://books.toscrape.com/).
2. Envia os dados extraídos para um email configurado no arquivo `application.properties`.

---

## **Configuração do Projeto**

### **1. Pré-requisitos**
- **Java 17** ou superior.
- **Maven** instalado (ou use o Maven Wrapper incluído no projeto).
- Uma conta Gmail com senha de aplicativo configurada (veja as instruções abaixo).

---

### **2. Criando o arquivo `application.properties`**

1. **Localização do arquivo:**
   - O arquivo `application.properties` deve ser criado no seguinte diretório:
     ```
     src/main/resources/application.properties
     ```

2. **Conteúdo do arquivo:**
   Copie o exemplo abaixo para configurar as credenciais e parâmetros necessários:

   ```properties
   # Configurações de email
   email.username=seuemail@gmail.com         # Seu email Gmail
   email.password=sua_senha_de_app           # Senha de aplicativo gerada no Google
   email.to=destinatario@email.com           # Email do destinatário
   email.from=seuemail@gmail.com             # Email do remetente (igual ao username)

   # URL do site para scrap
   scrap.url=http://books.toscrape.com/
   ```

3. **Gerando a senha de aplicativo no Gmail:**
   - Acesse [https://myaccount.google.com/security](https://myaccount.google.com/security).
   - Ative a **Verificação em duas etapas**.
   - Em **Senhas de App**, gere uma senha e use-a no lugar de `email.password`.

---

## **Como Rodar o Projeto**

1. **Baixar Dependências do Maven:**
   Execute o comando abaixo para baixar as dependências:
   ```bash
   mvn clean install
   ```

2. **Executar o Projeto:**
   Use o comando:
   ```bash
   mvn spring-boot:run
   ```

3. **Resultados Esperados:**
   - O programa realizará o web scraping da URL configurada (`http://books.toscrape.com/`) e enviará os dados extraídos para o email configurado em `email.to`.

---

## **Exemplo de Uso**
- O email enviado incluirá uma lista de livros disponíveis no site de exemplo.

---

## **Dependências Utilizadas**
- **Spring Boot**: Para facilitar a configuração e execução do projeto.
- **JSoup**: Para realizar o web scraping.
- **Jakarta Mail**: Para envio de emails.

---

Se tiver dúvidas ou problemas, entre em contato! 😊
