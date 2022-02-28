package webshop;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductRepository {


    private JdbcTemplate jdbcTemplate;

    public ProductRepository(MariaDbDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            //language=sql
                            "insert into products(product_name, price, stock) values (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, productName);
                    ps.setLong(2, price);
                    ps.setLong(3, stock);
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject(
                //language=sql
                "select * from products where id =?",
                (rs, i) -> new Product(
                        rs.getLong("id"),
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock")),
                id);
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update(
                //language=sql
                "update products set stock = stock-? where id = ?",
                amount, id);
    }
}
