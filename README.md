# Projeto de Consolidação de Salários

O projeto consiste em 3 pacotes: `Model`, `Bean` e `Service`.

## Pacote Model
No pacote `Model`, possui as classes entidades com seus respectivos annotations para o Hibernate: `Cargo.java`,`CargoVencimento.java`,`Pessoa.java`,`PessoaSalarioConsolidado.java`,`Vencimento.java`

---

## Pacote Bean
No pacote `bean` consta o `ConsolidacaoBean.java`.

Este é um Managed Bean do JSF que está no escopo de visualização (`@ViewScoped`). Ele interage com o serviço de consolidação (`ConsolidacaoService`) para consolidar salários e armazenar os resultados.

### Atributos:
- `consolidacaoService`: Instância do serviço que realiza a consolidação dos salários.
- `resultados`: Lista que armazena os resultados da consolidação.

### Métodos:
- `consolidarSalarios()`: Chama o método `consolidarSalarios` do serviço e armazena os resultados na lista `resultados`.
- `getResultados()`: Retorna a lista de resultados.

---

## Pacote Service
No pacote `service`, consta o `ConsolidacaoService.java`.

Este serviço é responsável por consolidar os salários das pessoas e armazenar os resultados no banco de dados.

### Atributos:
- `sessionFactory`: Instância do `SessionFactory` do Hibernate para gerenciar as sessões.
- `resultados`: Lista que armazena os resultados da consolidação.

### Métodos:
- `ConsolidacaoService()`: Construtor que configura o `SessionFactory` usando a configuração padrão do Hibernate.
- `deletarDadosExistentes()`: Método que deleta todos os dados existentes na tabela `PessoaSalarioConsolidado`.
- `consolidarSalarios()`: Método principal que realiza a consolidação dos salários. Ele:
  1. Abre uma sessão e inicia uma transação.
  2. Deleta os dados existentes chamando `deletarDadosExistentes()`.
  3. Recupera todas as pessoas do banco de dados.
  4. Calcula o salário consolidado para cada pessoa, considerando os créditos e débitos.
  5. Cria uma nova instância de `PessoaSalarioConsolidado` para cada pessoa e salva no banco de dados.
  6. Adiciona cada instância de `PessoaSalarioConsolidado` à lista `resultados`.
  7. Comita a transação e fecha a sessão.
  8. Retorna a lista de resultados.

### Integração entre Bean e Service
No `ConsolidacaoBean`, o método `consolidarSalarios()` chama o método `consolidarSalarios()` do `ConsolidacaoService`, que realiza todo o processo de consolidação e retorna os resultados. Esses resultados então são armazenados na lista `resultados` do `ConsolidacaoBean`, que pode ser acessada na camada de visualização (JSF).


O persistence.xml configura a unidade de persistência, especificando o provedor de persistência, as classes de entidade e as propriedades de conexão e comportamento do Hibernate. Ele é fundamental para que o JPA saiba como se conectar ao banco de dados e gerenciar as entidades.
O hibernate.cfg.xml configura a conexão com o banco de dados, define o dialeto SQL, ativa as configurações de debug e mapeia as entidades que o Hibernate deve gerenciar. Ele é essencial para que o Hibernate saiba como se conectar ao banco de dados e como mapear as entidades para as tabelas do banco.
O faces-config.xml configura o bean gerenciado ConsolidacaoBean para ser utilizado na aplicação JSF. Ele especifica o nome do bean, a classe correspondente e o escopo de visualização. Isso permite que o bean seja acessado e utilizado nas páginas JSF para interagir com a lógica de negócios e a camada de serviço.
O web.xml configura a aplicação web Java, definindo parâmetros de contexto, mapeamentos de servlets e outras configurações essenciais. Nesse caso, ele configura o servlet JSF (Faces Servlet) para tratar todas as requisições para arquivos .xhtml e define o estágio do projeto como Development.
O arquivo consolidacao.xthml representa a página JSF permite que o usuário calcule ou recalcule os salários consolidados ao clicar no botão. Os resultados são exibidos em uma tabela, mostrando o ID da pessoa, o nome da pessoa, o nome do cargo e o salário formatado como moeda brasileira.

Instruções de execução:

Importar o projeto no Eclipse através do git, e exportar em formato .war.
Feito isso basta realizar o deploy do arquivo em um servidor web como o Tomcat na pasta webapps.

obs: levar em consideração que o banco de dados em POSTGRESQL exista com as 4 tabelas preenchidas e uma tabela vazia de pessoa_salario_consolidado.
O nome do banco é o mesmo da aplicacao: finanweb
postgresql://localhost:5432/finanweb

As tabelas/colunas possuem a seguinte estrutura:
cargo: id,nome
cargo_vencimentos: id,cargo_id,vencimento_id
pessoa: id,nome,cidade,email,cep,endereco,pais,usuario,telefone,data_nascimento,cargo_id
pessoa_salario_consolidado: pessoa_id,nome_pessoa,nome_cargo,salario
vencimentos: id,descricao,valor,tipo


