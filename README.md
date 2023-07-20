# AFazer - Gerenciador de tarefas

​	O projeto foi feito com base no padrão de projetos MVC (Model View Controller), utilizando o NetBeans como IDE e o gradle como gerenciador de depêndencias.  O projeto conta com quatro pacotes, model, controller, veiw e util, os três primeiros cuidam das funcionalidades de acordo com o padrão de projeto e o pacote util contém algumas classes que auxiliam a otimização de alguns componentes.

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

## Pacote util

​	Este pacote conta com algumas classes utilizadas para customizar componentes da tabela de tarefas e também para fazer a conexão com o banco de dados. Possui os arquivos ButtonColumnCellRenderer.java, ConectionFactory.java, DeadLineColumnCelRenderer.java e TaskTableModel.java.

- __Classe para renderizar Botões nas colunas:__ Estende a classe DefaultTableCellRenderer. Possui um atributo do tipo string, métodos construtor, assessor e modificador do atributo e além disso o método getTableCellRendererComponent.
  - __Método construtor:__ Modifica o valor do atributo com o argumento passado como parâmetro.
  - __Método getTableCellRendererComponent:__ Método herdado da super classe, porém é sobrescrito aqui para fazer algumas alterações na tabela de tarefas.
- __Classe  Fabrica de conexão:__ Implementada no arquivo ConectionFactory.java, tem a responsabilidade de definir a conexão com o banco de dados. Possui os atributos DRIVER, URL, USER e PASS. Como métodos tem o getConnection e três closeConnection usados para diferentes tipos de situação.
- __Classe prazo para tarefas:__ Feita no arquivo DeadLineColumnRenderer.java, tem como objetivo customizar a coluna de data da tabela de tarefas. Não possui atributos, porém possui o método herdado da super classe getTableCellRenderer.
  - __Método getTableCellRenderer:__ Customiza a coluna de prazo da tabela de tarefas com para alterar a cor dependendo da data de prazo.
- __Classe de modelo para tabela de tarefas:__ Implementada no arquivo TaskTableModel, estende a classe AbstractTableModel, possui dois atributos um vetor de strings columns que contém os títulos das colunas da lista de tarefas e outro do tipo List contendo objetos do tipo Task para armazenar a lista de tarefas. A classe conta um método assessor do atributo columns e métodos assessor e e modificador do atributo tasks, além desses, possui os métodos herdados da super classe, aos quais foram modificados, o isCellEditable e o getValueAt.
  - __Método isCellEditable:__ Retorna se a coluna da linha é editável.
  - __Método getValueAt:__ Retorna o valor da linha e da coluna determinada pelos argumentos recebidos como parâmetros.

## Pacote View

​	O pacote view conta com quatro arquivos, MainScreen.java, ProjectDialogScreen.java, TaskDialogScreen.java e TaskUpdateDialogScreen.java, cada representa uma das telas utilizadas na GUI da aplicação. Utilizando os recursos da biblioteca swing para construir a interface gráfica, a maioria feita através da ferramenta drag and drop do NetBeans, porém alguns dos recursos tiveram que ser implementados a mão por limitações neste recurso.

- __Janela principal:__ Implementada no arquivo MainScreen.java, esta é a tela inicial da aplicação. Feita a partir de uma extensão da classe JFrame do pacote swing. A classe possui quatro atributos, Um do tipo ProjectController, outro do tipo TaskController e os dois últimos são do pacote util, um do tipo DefautListModel e o outro TaskTableModel. Contém os métodos construtor, jLabelProjectsAddMouseClicked, jLabelTasksAddMouseClicked, jTableTasksMouseClicked, jListProjectsMouseClicked, decorateTableTask, initDataController, initComponentsModel, loadTasks, showTableTasks e loadProjects. 

  - __Método construtor:__ Chama os métodos initComponents, initDataController, initComponentsModel e decorateTableTask.
  - __Método jLabelProjectsAddMouseClicked:__ Abre a janela de dialogo para adicionar um novo projeto.
  - __Método jLabelTasksAddMouseClicked:__ Abre a janela de dialogo para adicionar uma nova tarefa.
  - __Método jTableTasksMouseClicked:__ Seleciona a linha na tabela de tarefas e além disso dispara alguns eventos dependendo em qual coluna for clicada. Por exemplo:
    - A coluna 3 possui uma check box marcando se a tabela esta concluída ou não;
    - A coluna 4 possui um botão para atualizar a tarefa selecionada;
    - A coluna 5 remove a tarefa selecionada;
  - __Método jListProjectsMouseClicked:__ Seleciona um projeto e carrega suas tarefas na tabela de tarefas.
  - __Método decorateTableTask:__ Customiza o cabeçalho da tabela de tarefas.
  - __Método initDataController:__ Inicializa os atributos das classes ProjectController e TaskController.
  - __Método initComponentsModel:__ Indica o estado inicial das listas quando a aplicação é aberta.
  - __Método loadTasks:__ Carrega as tarefas do projeto ao qual o id é passado como argumento.
  - __Método showTableTasks:__ Determina o que deve ser mostrado na tabela de tarefas.
  - __Método loadProjects:__ Carrega a lista de projetos no painel de projetos.

  ![MainScreen](https://github.com/Charlesson-MP/AFazer-Gerenciador-de-Tarefas/assets/86690606/016946a3-d720-4efb-ad8a-cb510402f0e8)

- __Janela de cadastro de projeto:__ Contida no arquivo ProjectDialogScreen.java, esta já é uma extensão da classe JDialog, pois é usada para cadastrar um projeto na lista de projetos e também no banco de dados. Possui um atributo do tipo ProjectController, métodos herdados de sua super classe, um construtor e o  jLabelSaveMouseClicked.

  - __Método construtor:__ Chama o método initComponents e inicializa o atributo controller.

  - __Método jLabelSaveMouseClicked:__ Salva o projeto com as informações passadas na janela de diálogo.

  ![ProjectDialogScreen](https://github.com/Charlesson-MP/AFazer-Gerenciador-de-Tarefas/assets/86690606/86eb850e-5903-4236-8503-9a31d5cc1697)

- __Janela de cadastro de tarefa:__ Salva no arquivo TaskDialogScreen.java, também é uma extensão da classe JDialog, porém esta tem como objetivo cadastrar tarefas. Possui dois atributos, um do TaskController e outro do tipo project. Conta com os métodos herdados da super classe, um construtor, jLabelSaveMouseClicked,  setProject, hideErrorFields e isFieldsValid.

  - __Método construtor:__ Chama os métodos initComponents, hieErrorFields e inicializa o atributo taskController.

  - __Método jLabelSaveMouseClicked:__ Salva a tarefa com as informações passadas na janela de diálogo.
  - __Método setProject:__ Método modificador do atributo Project da classe.
  - __Método hideErrorFIelds:__ Torna as labels de erro invisíveis ao abrir a janela de cadastro de tarefa.
  - __Método isFieldsValid:__ Testa se os campos foram devidamente preenchidos. 

  ![TaskDialogScreen](https://github.com/Charlesson-MP/AFazer-Gerenciador-de-Tarefas/assets/86690606/7f79faf7-ffba-4647-a262-85c993800883)

- __Janela de atualização de tarefa:__ Construída no arquivo TaskUpdateDialogScreen.java, é semelhante a janela de cadastro de tarefas, porém ela é acionada quando a coluna 4 da tabela de tarefas é selecionada. Possui atributos do tipo Project, TaskController e Task. Possui dois métodos construtores, jLabel2MouseClicked, isFieldsValid, hideErrorFields e setProject.

  - __Método construtor 1:__ Construtor padrão da classe, chama o método initComponents, hideErrorFields e inicializa o atributo taskController.
  - __Método construtor 2:__ Tem um parâmetro da classe Task além dos outros parâmetros. Realiza as mesmas atividades do construtor um porém inicializa o atributo task com as informações do argumento task passado como parâmetro.
  - __Método jLabel2SaveMouseClicked:__ Atualiza a tarefa com as informações passadas na janela de diálogo.
  - __Método setProject:__ Método modificador do atributo Project da classe.
  - __Método hideErrorFIelds:__ Torna as labels de erro invisíveis ao abrir a janela de cadastro de tarefa.
  - __Método isFieldsValid:__ Testa se os campos foram devidamente preenchidos. 

  ![TaskUpdateDialogScreen](https://github.com/Charlesson-MP/AFazer-Gerenciador-de-Tarefas/assets/86690606/00c6ae5d-e2f6-467c-a0fa-120c3e7959a4)

