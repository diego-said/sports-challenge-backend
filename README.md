# sports-challenge-backend

Este é um repositório para um teste relasido para o Grupo Netshoes.

Ele é composto por dois serviços:

* campaign-service
* fan-service


## Deadlock

Uma resposta bem simplificada seria que é um problema que ocorre quando um processo A precisa acessar um recurso, mas não é possível porque o processo B está utilizando. E o processo B precisa acessar um recurso, mas não é possível porque o processo A está utilizando. Assim ambos os processos A e B ficam aguardando a liberação de acesso os recursos, mas isso nunca irá ocorrer porque ambos os processos estão impedindo o término de suas execuções.

Formas de se evitar:

*	Pode deixar o acesso aos recursos não serem exclusivos, utilizando um pool de acesso por exemplo
*	Pode deixar um tempo limite para a aquisição do recurso, assim seu processo pode informar que não foi possível adquirir o recurso e finalizar a execução
*	Pode-se tentar pegar todos os recursos no início do processo, assim caso um dos recursos não esteja disponível você não irá travar os demais

Nós podemos detectar este problema verificando o processo, normalmente um processo em espera fica alocando os recursos, então quantidade de processamento, memória consumida e tempo de execução podem indicar que um processo está travado.
