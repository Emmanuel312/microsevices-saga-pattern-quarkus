# microservice-saga-pattern-quarkus

## Overview
O Repositorio contem 2 microserviços que em conjunto aplicam o padrão saga coreografado com o objetivo de manter a consistência de um dado em um sistema distribuído.

Booking: Cria um agendamento

Hotel Availability: Valida se o hotel possui vaga 

## Fluxo
Quando um booking é criado pela rota POST /booking o mesmo será salvo no banco com o status de PENDING. O booking irá enviar um evento de criação para o kafka e o hotel-availability irá processar tal evento com o objetivo de checar se o hotel em questão possui vaga. Em seguida, a depender do resultado, o hotel-availability enviará para o tópico booking_available ou booking_not_available. Para manter a consistência o booking consumirá os dois tópicos e mudará o status do agendamento a depender do tópico.

