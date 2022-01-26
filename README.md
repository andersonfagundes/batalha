# batalha

Trabalho da disciplina Serviços em Nuvem e Projeto de Bloco do Instituto Infnet. Este trabalho consiste em fazer um jogo rpg, em que cada funcionalidade do jogo é realizado através de um micro-serviço.

O projeto batalha é pra ser usado em conjunto com configServer, eurekaServer e rolldice.

batalha tem como objetivo chamar o serviço que rola os dados (rolldice), receber o valor da rolagem dos dados, realizar os ataques, defesas e danos e devolver o vencedor da batalha para quem o chamou a API.

Para utilizá-lo, será necessário um ambiente java (com a versão 11 do java) para subir a aplicação, ter o MySql instalado, chamar o serviço iniciativa e passar os parâmetros necessários. Ex:

http://localhost:8282/api/batalha/iniciativa?idHero=3&agilityHero=3&idMonster=4&agilityMonster=4

idHero = id do herói
agilityHero = valor da agilidade do herói
idMonster = id do monstro
agilityMonster = valor da agilidade do monstro

É necessário que os serviços configServer, eurekaServer e rolldice estejam iniciados antes de iniciar este serviço.

Para acessar a documentação criada pelo swagger, é necessário digitar o seguinte endereço após inicializar o projeto:
http://localhost:57294/swagger-ui/index.html
Obs: a porta deverá ser trocada para a porta em que o projeto inicializado, pois a porta usada é aleatória
