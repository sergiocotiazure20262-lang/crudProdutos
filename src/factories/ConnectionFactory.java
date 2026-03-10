package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //Método para retornar a conexão com o banco de dados
    public static Connection getConnection() throws Exception {

        //Parâmetros para conexão com o banco de dados
        var host = "jdbc:postgresql://localhost:5432/bdprodutos";
        var user = "postgres";
        var pass = "coti";

        //Retornar a conexão com o banco
        return DriverManager.getConnection(host, user, pass);
    }
}
