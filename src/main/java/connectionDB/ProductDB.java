package connectionDB;

import beens.Product;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/Products";
    private static final String USER_NAME = "Kiryl";
    private static final String PASSWORD = "12344321";

    public static List<Product> select(){
        List<Product> products = new ArrayList<>();
           try(Connection conn = getConnection()){
               Statement statement = conn.createStatement();
               String sql = "SELECT * FROM products";
               ResultSet resultSet = statement.executeQuery(sql);

               while(resultSet.next()){
                   int id  = resultSet.getInt("id");
                   String name = resultSet.getString("name");
                   int price = resultSet.getInt("price");
                   Product product = new Product(id,name,price);
                   products.add(product);
               }
           } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                    InstantiationException | IllegalAccessException e) {
               throw new RuntimeException(e);
           }

        return products;
    }

    public static Product selectOne(int id){
        Product product = null;
        try(Connection conn = getConnection()){
          String sql = "SELECT * FROM products WHERE id=?";
          try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
              preparedStatement.setInt(1,id);
              ResultSet resultSet = preparedStatement.executeQuery();
              if (resultSet.next()){
                  int prodId = resultSet.getInt("id");
                  String name = resultSet.getString("name");
                  int price = resultSet.getInt("price");
                  product = new Product(prodId,name,price);
              }
          }
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    public static int insert(Product product) {

        try(Connection conn = getConnection()) {
            try(PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO products (name, price) VALUES (?, ?)")){
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2,product.getPrice());

                return preparedStatement.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public static int update(Product product) {
        try(Connection conn = getConnection()) {
            try(PreparedStatement preparedStatement = conn.prepareStatement
                    ("UPDATE products SET name = ?, price = ? WHERE id = ?")){
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2,product.getPrice());
                preparedStatement.setInt(3, product.getId());

                return preparedStatement.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public static int delete(Product product) {
        try(Connection conn = getConnection()) {
            try(PreparedStatement preparedStatement = conn.prepareStatement
                    ("DELETE FROM products WHERE id = ?")){
                preparedStatement.setInt(1, product.getId());

                return preparedStatement.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }



    private static Connection getConnection() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.postgresql.jdbc.Driver").getDeclaredConstructor().newInstance();
       return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
