import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost/loja_virtual?Timezone=true&serverTimezone=UTC", "root", "20Emanu02");

        Statement stm = connection.createStatement();
        stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");

        ResultSet rst = stm.getResultSet();

        while (rst.next()) {
            Integer id = rst.getInt("id");
            System.out.println(id);
            String nome = rst.getNString("Nome");
            System.out.println(nome);
            String descricao = rst.getNString("descricao");
            System.out.println(descricao);

        }

        connection.close();
    }
}
