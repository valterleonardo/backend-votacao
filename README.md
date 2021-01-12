# backend-votacao

Este backend auxiliará um projeto de votação.


### Cadastrar nova Pauta
#### --- Post (banco)

### Abrir sessão de votação vinculado a pauta
#### --- receber tempo que deve ficar aberto
#### --- receber pauta
#### --- gravar no redis e banco

### Receber Votos associados a pauta
#### --- Receber sim / não
#### --- aceitar apenas 1 voto por usuario por pauta

#### Utilizar redis para controlar sessao aberta
#### validar CPF com GET https://user-info.herokuapp.com/users/{cpf}
Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode
usar geradores de CPF para gerar CPFs válidos;
Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não
pode (UNABLE_TO_VOTE) executar a operação


#### scheduler que verificar sessoes
#### Se a sessao fechou, postar o resultado da sessao numa fila

#### versionamento da api v1/

#### dockerizar