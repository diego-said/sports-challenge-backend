# sports-challenge-backend

Este é um repositório para um teste para o Grupo Netshoes.

Ele é composto por dois serviços:

* campaign-service: controla todas as informações das campanhas, expondo uma API Rest para CRUD e algumas outras operações.
* fan-service: controla toas as informações dos fãs em um programa de sócio torcedor, também expõe uma API Rest para CRUD e consume informções do serviço acima.

O desenho da arquitetura do serviço segue abaixo:

![alt text](https://github.com/diego-said/sports-challenge-backend/blob/master/arch_layout.png)

E mais informações podem ser encontradas na pasta ```/src/main/doc``` de cada projeto.

## Deadlock

Uma resposta bem simplificada seria que é um problema que ocorre quando um processo A precisa acessar um recurso, mas não é possível porque o processo B está utilizando. E o processo B precisa acessar um recurso, mas não é possível porque o processo A está utilizando. Assim ambos os processos A e B ficam aguardando a liberação de acesso os recursos, mas isso nunca irá ocorrer porque ambos os processos estão impedindo o término de suas execuções.

Formas de se evitar:

*	Pode deixar o acesso aos recursos não serem exclusivos, utilizando um pool de acesso por exemplo
*	Pode deixar um tempo limite para a aquisição do recurso, assim seu processo pode informar que não foi possível adquirir o recurso e finalizar a execução
*	Pode-se tentar pegar todos os recursos no início do processo, assim caso um dos recursos não esteja disponível você não irá travar os demais

Nós podemos detectar este problema verificando o processo, normalmente um processo em espera fica alocando os recursos, então quantidade de processamento, memória consumida e tempo de execução podem indicar que um processo está travado.

## Stream e ParallelStreams

A Stream representa uma sequência de elementos em que se pode aplicar diversos tipos de operações nesses elementos como: filtrar, ordenar, percorrer, entre outros.

A principal diferença entra uma Stream e uma ParallelStream é que na ParallelStream as operações feitas nos elementos podem ocorrer de forma paralela. Desta forma tem-se uma vantagem no processamento caso hardware possua mais de um processador ou se o processador tem mais de um núcleo.

Você pode utilizar ParallelStream quando as operações feitas em um elemento da sequência não têm influência sobre as operações de outro elemento. Por exemplo, se você quer enviar um mesmo email para diversos destinatários, você pode usar uma ParallelStream. Neste caso como o conteúdo do email é o mesmo não importa em qual ordem os destinatários serão processados.
Por outro lado, se você quiser analisar o conteúdo de um arquivo é melhor utilizar um Stream. Porque neste caso a ordem do conteúdo dentro do arquivo será importante.

