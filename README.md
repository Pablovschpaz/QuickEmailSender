# QuickEmailSender

QuickEmailSender foi desenvolvido em Kotlin, serve exclusivamente para o envio de emails. **Não é um cliente de email completo**, apenas uma solução rápida para enviar emails.

## Configuração

Para configurar o QuickEmailSender, defina as seguintes variáveis de ambiente:

- `SMTP_HOST`: Endereço do servidor SMTP
- `SMTP_PORT`: Porta do servidor SMTP
- `EMAIL_USER`: Nome de usuário para autenticação no servidor SMTP
- `EMAIL_PASSWORD`: Senha para autenticação no servidor SMTP

### Windows

1. Abra o Prompt de Comando como Administrador.
2. Execute os seguintes comandos:

    ```sh
    setx SMTP_HOST "seu_servidor_smtp"
    setx SMTP_PORT "sua_porta_smtp"
    setx EMAIL_USER "seu_usuario"
    setx EMAIL_PASSWORD "sua_senha"
    ```
3. Reinicie a máquina

   
### Linux

1. Abra o terminal.
2. Adicione as seguintes linhas ao seu arquivo de configuração de ambiente (por exemplo, `~/.bashrc`, `~/.bash_profile`, `~/.profile`):

    ```sh
    export SMTP_HOST="seu_servidor_smtp"
    export SMTP_PORT="sua_porta_smtp"
    export EMAIL_USER="seu_usuario"
    export EMAIL_PASSWORD="sua_senha"
    ```

3. Atualize o ambiente:

    ```sh
    source ~/.bashrc
    ```

## Uso

1. Clone este repositório:

    ```sh
    git clone https://github.com/seu_usuario/QuickEmailSender.git
    ```

2. Navegue até o diretório do projeto:

    ```sh
    cd QuickEmailSender
    ```

3. Compile o projeto com Gradle:

    ```sh
    gradle build
    ```

4. Execute o aplicativo:

    ```sh
    gradle run
    ```
