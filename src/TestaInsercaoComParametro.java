import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {

        String nome = "Mouse'";
        String descricao = "Mouse sem fio";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()) {

            connection.setAutoCommit(false);

            try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (NOME, descricao) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                adicionaVariavel("SmartTV", "45 polegadas", stm);
                adicionaVariavel("Radio", "bateria", stm);

                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback executado com sucesso!");
                connection.rollback();
            }
        }
    }

    private static void adicionaVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        if (nome.equals("Radio")) {
            throw new RuntimeException("Não foi possivel adicionar o produto");
        }

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O ID criado foi " + id);
            }
        }
    }
}
