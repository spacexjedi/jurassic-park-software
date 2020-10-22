# jurassic-park-software

<p align="center">
  <a href="src">
    <img alt="Logo" src="https://anmtv.com.br/wp-content/uploads/jurassic-world-acampamento-jurassico-1.png" width="100" />
  </a>
</p>
<h1 align="center">
  Gerenciamento de animais <a href="https://www.netflix.com/title/81009646">em geral</a>
</h1>

<p align="center">
  <a href="https://github.com/spacexjedi/jurassic-park-software/graphs/commit-activity" alt="Maintenance">
    <img src="https://img.shields.io/badge/Maintained%3F-yes-1EAE72.svg" />
  </a>


  <!-- License -->
  <a href="./LICENSE" alt="License: MIT">
    <img src="https://img.shields.io/badge/License-MIT-1EAE72.svg" />
  </a>


  <br/>
  
  <!-- Social -->
  <a href="https://github.com/spacexjedi/jurassic-park-software/stargazers">
    <img alt="GitHub stars" src="https://img.shields.io/github/stars/spacexjedi/jurassic-park-software?style=social">
  </a>
</p>

<!-- summary -->
<p align="center">
  <a href="#clipboard-descrição">Descrição</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#sparkles-funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-o-que-tem-dentro">O que tem dentro</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-licença">Licença</a>
</p>

---

## :construction_worker: Maintainers
- @spacexjedi


## :clipboard: Descrição
O primeiro sistema (https://github.com/spacexjedi/spockfyj)
simulava um serviço de streaming de música como o Spotify, permite ouvir músicas e adicionar
playlists.   

Foi implementada a função **ouvir música** completo com playlist e integração com a API do spotify, os formulários de login e cadastro foram criados
em frontend, mas sem validação em backend.

Motivo do abandono: estava aprendendo as tecnologias por isso ficou desorganizado e com casos
de testes.

O segundo sistema em python não faz nenhuma integracação com API externa (https://github.com/spacexjedi/spock-music)
as músicas são arquivos .mp3 em uma pasta, o sistema possui persistência e um player solo.

Motivo do abandono: não correspondia a tecnologia escolhida pelo professor.
Motivo de ter feito: precisava entender como funcionava para integrar um banco, backend e templates de forma geral por isso fiz na tecnologia que me sentia confortável.

## Atual sistema
Se baseia no aprendizado das tecnologias ao longo do semestre, bem como seguindo a minha ideia de software (algo voltado para animais),
já que o streaming de música foi idéia de outra pessoa quando eu estava em uma equipe. 

Foi utilizado um template de spring-boot disponivel para desenvolvimento com docker e gitpod
(vscode em cloud, para que não precisar depender dos recursos da própria máquina. O motivo de utilizar template foi para que rodasse em cloud)
Os créditos do template estão associados aos respectivos autores,
apartir daí eu implementei as demais funcões.

## Condiderações
Como as decisões sobre o sistema cabiam apenas a mim,
achei mais importante aprender o processo de desenvolvimento e tecnologias.
Se fosse o pedido de um cliente eu certamente teria seguindo regras de negócios.

## Documentação 
Há uma pasta chamada docs que contém screenshots dos sistemas anteriores,
bem como os sketchs do atual.

## :sparkles: Funcionalidades  

- Ao entrar na aplicação você vai encontrar:  
  - **Listar**: 
  - **Cadastrar**:   
  

### :fire: Requisitos escolhidos

  - [x] Springboot  
  - [x] tymeleaf html
  - [x] h2/mysql  


## 🧐 O que tem dentro?

### :label: Linguagem
- Java 11 + Springboot


### :art: Ferramentas 
- [gitpod](gitpod.io)   


### :loud_sound: Sites, Recursos e APIS

[gitpod](gitpod.io) 
  

### :package:  Requisitos para rodar projeto

detalhamento do estudo, pesquisas e livros lidos

![Repo Caderno](https://github.com/spacexjedi/software_eng_101)  

```
comandos
```
**gitpod**

./mvnw springboot:run

**computador** 
utilize sua IDE 


### horas de projeto  

| Lockdown until ADNP start                                                                          | QUA | QUI | SEX | SAB | DOM |
|----------------------------------------------------------------------------------------------------|-----|-----|-----|-----|-----|
| 1 hora por dia de treino em programação e aplicações  com a Codecademy e Twitch Science Technology | 8hs | 8hs | 8hs | 8hs | 8hs |  


| QUA | QUI | SEX | TER | QUI | SEX |
|-----|-----|-----|-----|-----|-----|
| 2hs | 2hs | 2hs | 2hs | 2hs | 2hs |


| SEG | TER | QUA | 
|-----|-----|-----|
| 1hs | 2hs | 2hs | 


| SEG | TER |
|-----|-----|
| 5hs | 5hs |

Período de pesquisa e leituras [não contabilizado em horas exatas]

- Última semana
| QUI | SEX | SAB | SEG | 
|-----|-----|-----|-----|
| 6hs | 8hs | 8hs | 8hs | 



## :memo: Licença

Este projeto está sobre a licença MIT. Veja o arquivo [LICENSE] (LICENSE) para mais detalhes.

