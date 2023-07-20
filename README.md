# AFazer - Gerenciador de tarefas

​	O projeto foi feito com base no padrão de projetos MVC (Model View Controller), com quatro pacotes, model, controller, veiw e util, os três primeiros cuidam das funcionalidades de acordo com o padrão de projeto e o pacote util contém algumas classes que auxiliam a otimização de alguns componentes.

## Pacote model

​	Representa o modelo de objetos que nossa aplicação vai trabalhar. Nele guardamos duas classes, uma para representar os projetos e outras para representar as tarefas contidas em cada projeto.

- __Classe Projeto:__ Implementada no arquivo Project.java, tem cinco atributos que foram necessários para definir um projeto na aplicação, são eles, id, name, description, createdDate e updatedDate, possui dois construtores, um que inicializa todos os atributos com argumentos passados como parâmetros e outro que não possui parâmetros, mas instância os atributos createdDate e updatedDate, além disse possui métodos assessores e modificadores para cada um dos atributos e um método toString.
- __Classe Tarefa:__ Seu código fonte está contido no arquivo Task.java, conta com os atributos id, idProject, name, description, notes, isCompleted, createdDate, updatedDate e deadLine, possui também dois construtores, um que inicializa os atributos de acordo com argumentos passados como parâmetros e outro que também não possui parâmetros e instância os atributos createdDate e updatedDate, além disso também possui métodos assessores e modificadores para cada atributo e um método toString.

## Pacote controller

​	Aqui armazeno as classes responsáveis de fazer a ponte entre a aplicação e o banco de dados, uma faz o trabalho de fazer o controle dos projetos e a outra cuida do controle das tarefas.

__*Observação:*__ No projeto foi utilizado o XAMPP para criar e gerenciar o banco de dados, porém, apesar da ferramenta disponibilizar uma interface para construção de banco de dados, o banco e as tabelas da aplicação foram criados via código mySQL.

- __Classe Controladora dos Projetos:__ Contida no arquivo ProjectController.java, não possui atributos, mas possui quatro métodos que são responsáveis por salvar, atualizar, remover e obter projetos do banco de dados, são eles, save, update, removeById e getAll.
  - __Método save:__ Possui um parâmetro da classe Project, e insere um novo projeto com os dados do projeto passado pelo parâmetro na tabela de projetos do banco através do comando SQL insert into.
  - __Método update:__ Possui um parâmetro da classe Project. Sua função é atualizar o projeto na tabela de projetos do banco de dados com o comando SQL update. O projeto é atualizado de acordo o id do projeto passado como parâmetro.
  - __Método removeById:__ Possui um parâmetro do tipo inteiro, que representa o id do projeto a ser removida do banco de dados. Através do comando SQL delete.
  - __Método getAll:__ Retorna uma lista com todos os projetos contidos na base de dados. 
- __Classe Controladora de Tarefas:__ Implementada no arquivo TaskController, é muito semelhante a classe ProjectController, possui os mesmos métodos, porém o que a diferencia da outra classe controladora são os parâmetros declarados nos métodos, ao invés de serem da classe Project, são da classe Task, com exceção dos métodos removeById que recebe o id da tarefa como parâmetro e o getAll que recebe o id do projeto do qual se quer obter a lista de tarefas.

## Pacote View

