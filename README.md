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

### Dados para popular os CSV's. (Caso copie e cole retire os "- " no começo da frase, no csv verificar o cabeçalho para evitar erros).
#### Cursos.csv 
- Código,Nome,Área de Conhecimento 
- ADS1,Análise e desenvolvimento de sistemas,Tecnologia da informação
- ADS2,Análise e desenvolvimento de sistemas,Tecnologia da informação
- ADS3,Análise e desenvolvimento de sistemas,Tecnologia da informação
- ADS4,Análise e desenvolvimento de sistemas,Tecnologia da informação
- COMEX1,Comércio exterior,Negócios
- COMEX2,Comércio exterior,Negócios
- COMEX3,Comércio exterior,Negócios
- COMEX4,Comércio exterior,Negócios

#### Professores.csv
- CPF,Nome,Área de Conhecimento,Pontos
- 451.213.983-02,João,Lógica,90
- 541.965.125.12,Maria,Matemática,95
- 348.372.382-34,cléber,Economia,80
- 082.346.325-36,Renata,Marketing,75

#### Inscrições.csv
- CPF do Professor,Código da Dsiciplina,Código do Processo
- 451.213.983-02,101,001
- 541.965.125.12,102,002
- 348.372.382-34,203,003
- 082.346.325-36,204,004

#### Disciplinas.csv
- Código,Nome,Dia da Semana,Horário Inicial,Horas Diárias,Código do Curso
- 101,Lógica,Segunda-feira,07:00,2,ADS1
- 102,Calculo,Terça-feira,09:00,4,ADS2
- 103,Sistema Operacional,Quarta-feira,10:00,4,ADS3
- 104,Banco de Dados,Quinta-feira,11:00,4,ADS4
- 201,Logística,Sexta-feira,19:00,4,COMEX1
- 202,Legislação,Sabádo,08:00,4,COMEX2
- 203,Economia,Segunda-feria,20:00,2,COMEX3
- 204,Marketing,Quarta-feira,21:00,2,COMEX4