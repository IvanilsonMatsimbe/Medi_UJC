# Medi_UJC
MediUjc - Sistema de Agendamento Médico
=======================================

Este projeto é uma API REST para gerenciamento de agendamentos médicos.

------------------------------------------------------------

LINK DA DOCUMENTAÇÃO (SWAGGER):
http://localhost:8080/swagger-ui/index.html

------------------------------------------------------------

Tecnologias Utilizadas
----------------------
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / PostgreSQL
- OpenAPI 3 (Swagger)
- Maven

------------------------------------------------------------

Como Executar o Projeto
-----------------------
1. Clonar o repositório:
   git clone https://github.com/seu-usuario/mediujc-api.git

2. Navegar até a pasta:
   cd mediujc-api

3. Executar a aplicação:
   ./mvnw spring-boot:run

4. Acessar a API:
   http://localhost:8080

------------------------------------------------------------

Rotas da API
------------

Utilizadores (/utilizadores)
----------------------------
GET     /utilizadores
GET     /utilizadores/{id}
POST    /utilizadores
PUT     /utilizadores/{id}
DELETE  /utilizadores/{id}
GET     /utilizadores/tipo?tipo=MEDICO

Consultas (/consultas)
----------------------
GET     /consultas
GET     /consultas/{id}
POST    /consultas
PUT     /consultas/{id}
DELETE  /consultas/{id}
PATCH   /consultas/{id}/cancelar     (com motivo no corpo)
PATCH   /consultas/{id}/confirmar
GET     /consultas/medico/{id}
GET     /consultas/paciente/{id}

------------------------------------------------------------

Exemplos de Requisições
------------------------

Criar Utilizador (Paciente/Médico)
curl -X POST http://localhost:8080/utilizadores \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "Dr. João",
           "email": "joao@medico.com",
           "tipo": "MEDICO"
         }'

Agendar Consulta
curl -X POST http://localhost:8080/consultas \
     -H "Content-Type: application/json" \
     -d '{
           "paciente": {"id": 1},
           "medico": {"id": 2},
           "dataHoraInicio": "2025-06-10T10:00:00",
           "dataHoraFim": "2025-06-10T10:30:00"
         }'

Cancelar Consulta
curl -X PATCH http://localhost:8080/consultas/1/cancelar \
     -H "Content-Type: application/json" \
     -d '"Paciente indisponível"'

------------------------------------------------------------

Tratamento de Erros
-------------------
404 - ResourceNotFoundException: Recurso não encontrado
409 - ConflictException: Conflito de dados (ex: email existente)
500 - Exception: Erro interno

------------------------------------------------------------

Observações Finais
------------------
- Modularização em: config, model, repository, service, exception.
- Possível expandir para controle de autenticação.
- API pronta para produção com Swagger/OpenAPI.

------------------------------------------------------------