package repositories;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {

    //Método para inserir um produto no banco de dados
    public void create(Produto produto) throws Exception {

        //Abrir conexão com o banco de dados
        try (var connection = ConnectionFactory.getConnection()) {

            //Escrevendo uma sentença SQL para inserir um produto na tabela do banco de dados
            var statement = connection.prepareStatement("INSERT INTO produtos(nome, preco, quantidade) VALUES(?,?,?)");

            //Passando os parametros da query SQL para gravar o produto no banco
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());

            //Executando a sentença SQL no banco de dados
            statement.execute();
        }
    }
}
