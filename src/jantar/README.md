# Jantar dos Filósofos

  ![ ](https://upload.wikimedia.org/wikipedia/commons/7/7b/An_illustration_of_the_dining_philosophers_problem.png)

  Problema de sincronização proposto por Dijkstra em 1965 utilizado até hoje para entendimento e testes de sistemas de sincronização.
  
## Definição
  
  Consiste em N (normalmente 5) filósofos sentados em uma mesa redonda de jantar, cada um com um prato de espaguete e um garfo. Porém o macarrão está muito escorregadio, portanto,
  é necessário o uso de dois garfos.
  
  Logo, cada filósofo alterna entre dois estados, **comendo** e **pensando**. Após pensar, fica com fome, tentando assim pegar o segundo garfo à esqueda, 
  esperando até que esse esteja disponível.
  
  Após comer, devolve o garfo a mesa, à sua direita, e volta a pensar, continuando assim o ciclo.
  
  [Fonte](https://blog.pantuza.com/artigos/o-jantar-dos-filosofos-problema-de-sincronizacao-em-sistemas-operacionais)
