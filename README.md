# Trabalho de Estrutura de Dados

## Descrição
Este projeto foi desenvolvido para aplicar conceitos de Estruturas de Dados, como listas encadeadas, tabelas hash e filas. Ele gerencia disciplinas, inscrições e realiza operações de CRUD usando estruturas eficientes.

## Tecnologias Utilizadas
- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para facilitar o desenvolvimento da aplicação.
- **Maven**: Gerenciamento de dependências.
- **Arquivos CSV**: Utilizados como base de dados.

## Como Rodar

1. Clone o repositório para sua máquina:
   ```bash
   git clone https://github.com/PhelipeSantos22/TrabalhoEd
   
2. Navegue até a classe TrabalhoEdApplication.

3. Rode a aplicação diretamente pela IDE clicando em Run ou executando o método main.

4. Abra o projeto no navegador acessando a URL: http://localhost:8080/

## Estrutura do Projeto
- model: Contém as classes principais como Disciplina, Inscricao, e implementações das estruturas de dados (HashTable, ListaEncadeada, Fila).
- service: Implementa a lógica para manipular dados de disciplinas e inscrições.
- resources: Pasta onde os arquivos CSV e os arquivos HTML estão armazenados.

## Funcionalidades
### CRUD dos arquivos csv
- Adicionar, consultar, atualizar e remover.

### Estruturas de dados:
- Tabelas Hash: Para consultas rápidas por código do curso.
- Filas: Para manipulação de disciplinas em ordem FIFO.
- Listas Encadeadas: Para CRUD de dados no CSV.

### Instruções Específicas
- Para consultar disciplinas por curso, use o método consultarDisciplinasPorCurso na DisciplinaService. Este método realiza buscas com base no código do curso usando a tabela hash.
- Para consultar uma disciplina específica, use o método consultarDisciplina. Este método utiliza uma fila para retornar a disciplina correspondente ao código.
- Ao remover uma disciplina, as inscrições vinculadas a ela também serão removidas. Além disso, a disciplina será excluída da tabela hash automaticamente.
- Os arquivos CSV são criados automaticamente caso não existam.