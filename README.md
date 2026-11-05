# AppLoginFirebase - Trabalho Prático de Mobile

Aplicativo Android desenvolvido em **Kotlin** utilizando o **Android Studio**, integrado ao **Firebase Authentication** com a interface pronta do **FirebaseUI**, cumprindo os requisitos da disciplina de Desenvolvimento de Aplicações Mobile.

## 🚀 Tecnologias Utilizadas
- **Kotlin**: Linguagem moderna e oficial para desenvolvimento Android.
- **Android Studio**: Ambiente de desenvolvimento integrado (IDE).
- **Firebase Authentication**: Mecanismo seguro de autenticação e gerenciamento de usuários.
- **FirebaseUI Auth**: Biblioteca oficial do Google que fornece fluxos prontos e customizáveis para telas de login e cadastro.

## 📋 Funcionalidades Implementadas
1. **Tela de Autenticação Completa:** Suporte nativo para login e cadastro de novos usuários utilizando e-mail e senha através do FirebaseUI.
2. **Gerenciamento de Sessão:** Verificação automática de usuário logado utilizando o cache de tokens do Firebase.
3. **Opção de Logout (Sair):** Botão dedicado na tela principal para encerrar a sessão atual de forma segura e retornar ao fluxo de login.

## ⚙️ Como Configurar e Executar o Projeto

Para clonar e testar este projeto em sua máquina local, siga os passos abaixo:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/GuBarone/AppLoginFirebase.git
   ```

2. **Abra o projeto no Android Studio:**
   - Abra o Android Studio, clique em *Open* e selecione a pasta clonada.
   - Aguarde o Gradle sincronizar todas as dependências automaticamente.

3. **Configuração do Firebase:**
   - Acesse o [Console do Firebase](https://console.firebase.google.com/) e crie/abra seu projeto.
   - No menu *Authentication*, ative o provedor de login por **E-mail/Senha**.
   - Obtenha e posicione o arquivo **`google-services.json`** estritamente dentro da pasta `app/` do projeto.
   - Cadastre a chave SHA-1 de debug da sua máquina nas configurações do projeto no Firebase.

4. **Execução:**
   - Conecte um smartphone via USB (com a depuração USB ativa) ou inicie um emulador Android (AVD).
   - Clique no botão **Run** (ícone de play verde ou `Shift + F10`) na barra superior do Android Studio.

## 👨‍💻 Autores
- **Gustavo Pinheiro Barone** (RA: 243027)
- **Felipe Ramos** (RA: 242339)
- Universidade Santa Cecília (Unisanta) — Sistemas de Informação
