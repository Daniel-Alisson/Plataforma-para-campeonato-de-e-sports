# 🎯 Plataforma para Campeonatos de E-Sports

API para desenvolver uma plataforma web que possibilite a realização de campeonatos de e-sports, tanto presenciais quanto virtuais. A solução permitirá que organizadores criem e gerenciem eventos, jogadores se inscrevam em campeonatos e o público acompanhe partidas em tempo real. Além disso, a plataforma fornecerá ferramentas para facilitar a administração de torneios, incluindo rankings, premiações.

## 🚀 Tecnologias

- Java 21
- Maven
- Spring Boot 3.4.4
    - Spring Web
    - Spring Data MongoDB
    - Spring Security
    - Spring Boot DevTools
- MongoDB Atlas

## ⚠️ Configuração
O Spring Security foi desativado temporariamente para facilitar os testes e não realizei o commit do application.properties por conta da configuração do MongoDB (email e senha).

```
# application.properties - Config do MongoDB
spring.data.mongodb.uri=mongodb+srv://<usuário>:<senha>@<cluster>.mongodb.net/<banco>?retryWrites=true&w=majority
```
```
# application.properties - Desativar o Spring Security
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
```

## ⚙ Executar

- Clone o repositório:
```
git clone https://github.com/Daniel-Alisson/Plataforma-para-campeonato-de-e-sports.git
```

- Configure o arquivo application.properties
- Execute:
```
mvn spring-boot:run
```

## 🌐 Endpoints

### Usuários
- ```GET /usuarios/id``` - Busca usuário por ID
- ```GET /usuarios``` - Busca paginada de usuários
- ```POST /usuarios``` - Cadastra novo usuário
- ```PUT /usuarios/id``` - Atualizar informações do perfil
- ```DELETE /usuarios/id``` - Deletar usuário

##### EXEMPLO DE CADASTRO:
```
{
  "email": "daniel@email.com",
  "senha": "SenhaSegura123"
}
```
Depois do cadastro, o usuário poderá atualizar o restante do perfil.
##### EXEMPLO DE ATUALIZAÇÃO DO PERFIL:
```
{
  "nome": "Daniel-san",
  "nickName": "CondeManga",
  "email": "daniel@email.com",
  "senha": "SenhaSegura123",
  "dataNascimento": "2001-05-15",
  "imgUrl": "https://exemplo.com/foto.jpg",
  "status": "PROMOTOR" 
}
```

### Campeonatos
- ```POST /campeonatos``` - Criar novo campeonato
- ```GET /campeonatos``` - Busca paginada de campeonatos
- ```GET /campeonatos/id``` - Buscar campeonato por Id
- ```PUT /campeonatos/id``` - Atualizar informações do campeonato
- ```DELETE /campeonatos/id``` - Deletar campeonato
- 
##### EXEMPLO DE CADASTRO DE CAMPEONATO
```
{
"nomeCampeonato": "Torneio de Teste Promotor",
"nomeJogo": "MANGA",
"tipo": "INDIVIDUAL",
"numeroMaximoParticipantes": 30,
"numeroInscritos": 0,
"valorInscricao": 100.00,
"dataInicio": "2026-04-20T19:00:00",
"dataTermino": "2026-08-22T22:00:00",
"formato": "ELIMINACAO_SIMPLES",
"localizacao": "https://twitch.tv/mangaesports",
"regras": "MD1 até as quartas e MD3 na semi e final",
"descricao": "1º lugar: R$ 1000 | 2º lugar: R$ 500",
"capa": "https://storage.com/capas/manga-torneio.jpg",
"logo": "https://storage.com/logos/manga-logo.png",
"premiacao": "MANGA DE PRESENTE",
"modalidade": "Remoto"
}
```
### Inscricao
- ```POST /inscricao/id``` - Inscrever em um campeonato por Id
- ```GET /inscricao``` - Busca paginada de inscrições (GERAL)
- ```GET /inscricao/id``` - Buscar inscrições por Id
- ```PUT /inscricao/id``` - Atualizar informações da inscrição do usuário
- ```DELETE /inscricao/id``` - Cancelar inscrição

##### EXEMPLO DE INSCRIÇÃO
```
{
"idCampeonato": "6819107041f357547c1b04c8",
"idJogador": "6802f99f03892277089ce38a",
"idEquipe": null,
"tipo": "INDIVIDUAL"
}
```

```
{
"idCampeonato": "6819107041f357547c1b04c8",
"idJogador": null,
"idEquipe": "6802f99f03892277089ce38a",
"tipo": "EQUIPE"
}
```
## 🏗️ Estrutura

```
src/
├── main/
│   ├── java/
│   │   ├── controllers/
│   │   ├── dto/
│   │   ├── exceptions/
|   |   |   └── handlers/
│   │   ├── models/
│   │   ├── repositories/
│   │   └── services/
│   |── resources/
│   └── application.properties
└── test/
```
---

🤝 Sinta-se à vontade para mandar dicas ou sugerir melhorias.
Abra uma issue ou entre em contato!