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

    private static Connection getConnection() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.postgresql.jdbc.Driver").getDeclaredConstructor().newInstance();
       return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
