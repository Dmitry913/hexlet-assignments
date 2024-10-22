package exercise.repository;

import exercise.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product foundProduct = new Product(title, price);
                foundProduct.setId(id);
                return Optional.of(foundProduct);
            } else {
                return Optional.empty();
            }
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products";
        try (Connection conn = dataSource.getConnection();
             Statement preparedStatement = conn.createStatement()) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            List<Product> entities = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                long id = resultSet.getLong("id");
                Product foundProduct = new Product(title, price);
                foundProduct.setId(id);
                entities.add(foundProduct);
            }
            return entities;
        }
    }
    // END
}
