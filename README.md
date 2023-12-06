# Usuarios-Carros
Teste PITANG

#ESTÓRIAS DE USUÁRIO

Historia_1 - Criacao de Branch
Historia_2 - Commit do projeto Spring Base Criado com JPA e WebService Base H2
Historia_3 - Commit com a primeira model e controller da classe Usuario
Historia_4 - Model e Controller completo da classe Carro
Historia_5 - Criando Spring Configuration para Validar Login e criptografando senha de usuario.
Historia_6 - Criando e retornando o Token de autorizacao de acesso ok.
Historia_7 - Criacao de Todas as validacoes  e tratamento de excessoes, Liberaçoes de todos os metodos no Security Configuration 			além da criação de todos os  metodos necessarios nos Devidos controllers e services do backend.
Historia_8 - Criacao de todos os metodos nos controladores e Services, testados todos os endpoints.
Historia_9 - Geracao de Pacote e importaco para maquina virtua da Amazon AWC no endereco 34.212.244.100:8080 com a api já funcional.

#SOLUÇÃO

Foi desenvolvido a API restful com o springboot 4.2 e Java 17.
Nao estava familiarizado com todas as novas notations, do spring nem tanto o Java 17, mas consegui desenrolar.
Foi implementado a Criptografia da senha como sugerido nos requisitos desejaveis, mas fora isso só tive tempo pra tratar o minimo da especificacao, como o JWT, Persistencia em Memoria no H2, e servidor tomcat do proprio maven.
Pensei em rodar o Ng New Project do angular, apenas para não ficar sem nada, mas visto o pouco tempo, e as limitacoes da minha propria experiencia, seria inviavel me comprometer com o Front em tao pouco tempo.

Gostei bastante do Desafio, apesar desses quase 4 anos desenvolvendo, foi a primeira vez que fiz tudo do zero apenas com minha experiencia e o stackoverflow. 

Gostaria se possivel que o meu trabalho ate aqui fosse avaliado, e se fizer sentido para os meus avaliadores, gostaria de ter uma chance de provar meu valor numa empresa tao boa como a Pitang, que só ouço coisas boas desde que comecei na area de programacao.

Vou subor o projeto para o AWS Amanha, para cumprir com o prazo prometido, mas ainda pretendo depois fazer um front para que a API possa ser testada sem o postman.  Mas de qq forma vou anexar a colection que usei.

no demais, Obrigado pela oportunidade.

# Getting Started

Para executar a build do projeto é necessario a partir do eclipse rodar o comando maven->Install, para geracao do pacote. (Estarei fornecendo o arquivo ja pronto no formato Usuarios-Carros-api-0.0.1-SNAPSHOT.jar.

e de posse dele em qualquer terminal que tenha acesso ao comando java(executar java -version e garantir que esteja com a versao 17), dentro da pasta onde se encontra o pacote Usuarios-Carros-api-0.0.1-SNAPSHOT.jar, rodar "java -jar Usuarios-Carros-api-0.0.1-SNAPSHOT.jar"
Assim api já deve funcionar localmente na maquina onde se deseja testar.

A parti dai com uma ferramente como o postman, podemos disparar requisicoes para os endpoints e observar os resultados observados.
Estarei fornecendo tb o collection do JSON que usei para testar.

no futuro, devo criar um front pra fazer essa intermediacao e não sera necessario mais a utilizacao do JSON. e enviarei novas informacoes de deploy.


