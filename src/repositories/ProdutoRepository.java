package repositories;

import entities.Produto;
import factories.ConnectionFactory;

import java.sql.Statement;

public class ProdutoRepository {

    //Método para inserir um produto no banco de dados
    public Integer create(Produto produto) throws Exception {

        //Abrir conexão com o banco de dados
        try (var connection = ConnectionFactory.getConnection()) {

            //Escrevendo uma sentença SQL para inserir um produto na tabela do banco de dados
            var statement = connection.prepareStatement(
                    "INSERT INTO produtos(nome, preco, quantidade) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            //Passando os parametros da query SQL para gravar o produto no banco
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());

            //Executando a sentença SQL no banco de dados
            statement.execute();

            //Obtendo o ID gerado no banco de dados
            var keys = statement.getGeneratedKeys();
            if(keys.next()) {
                return keys.getInt(1); //retornar o ID gerado
            }

            return 0;
        }
    }

    //Método para atualizar um produto no banco de dados
    public boolean update(Produto produto) throws Exception {

        //Abrir conexão com o banco de dados
        try (var connection = ConnectionFactory.getConnection()) {

            //Escrevendo a sentença SQL para atualizar um produto no banco de dados.
            var statement = connection.prepareStatement("UPDATE produtos SET nome=?, preco=?, quantidade=? WHERE id=?");
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setInt(4, produto.getId());

            //Executa o comando SQL e retorna true se algum registro foi atualizado
            return statement.executeUpdate() > 0;
        }
    }

    //Método para excluir um produto no banco de dados
    public boolean delete(Integer id) throws Exception {

        //Abrir conexão com o banco de dados
        try (var connection = ConnectionFactory.getConnection()) {

            //Escrevendo uma sentença SQL para excluir um produto no banco de dados
            var statement = connection.prepareStatement("DELETE FROM produtos WHERE id=?");
            statement.setInt(1, id);

            //Executa o comando SQL e retorna true se algum registro foi excluído
            return statement.executeUpdate() > 0;
        }
    }

    //Método para consultar e exibir todos os produtos contidos na tabela do banco de dados
    public void findAll() throws Exception {

        //Abrir conexão com o banco de dados
        try (var connection = ConnectionFactory.getConnection()) {

            //Escrevendo uma sentença SQL para consultar todos os produtos da tabela
            var statement = connection.prepareStatement("SELECT * FROM produtos ORDER BY id");

            //Executa a consulta e retorna todos os registros obtidos
            var data = statement.executeQuery();

            //percorrer cada registro obtido do banco de dados
            while(data.next()) {
                System.out.println("Id..............: " + data.getInt("id"));
                System.out.println("Nome............: " + data.getString("nome"));
                System.out.println("Preço...........: " + data.getDouble("preco"));
                System.out.println("Quantidade......: " + data.getInt("quantidade"));
                System.out.println("Data e hora.....: " + data.getTimestamp("datahoracadastro"));
                System.out.println("...");
            }
        }
    }

}
