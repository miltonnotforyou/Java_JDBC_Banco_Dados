import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost/loja_virtual?Timezone=true&serverTimezone=UTC", "root", "20Emanu02");

        System.out.println("Fechando a conexão");

        connection.close();
    }
}
