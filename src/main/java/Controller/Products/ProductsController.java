package Controller.Products;


import DBConnection.DBConnection;
import javafx.scene.input.MouseEvent;
import model.Employee;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {
    private static ProductsController instance;

    private ProductsController() {
    }

    public static ProductsController getInstance() {
        return instance == null ? instance = new ProductsController() : instance;
    }

    public boolean addProducts(Product product){
        Connection connection = DBConnection.getInstance().getConnection();
        try {

            ResultSet rst = connection.createStatement().executeQuery("Select SupplierID from Supplier where Name='"+product.getSuppler()+"'");

            if (rst.next()) {
                PreparedStatement stm = connection.prepareStatement("Insert Into Product(ProductName,Category,Size,Price,Quantity,Image,SupplierID) values (?,?,?,?,?,?,?)");
                stm.setString(1, product.getName());
                stm.setString(2, product.getCategory());
                stm.setString(3, product.getSize());
                stm.setDouble(4, product.getPrice());
                stm.setInt(5, product.getQty());
                stm.setString(6, product.getImgPath());
                stm.setInt(7, rst.getInt(1));
                return stm.executeUpdate()>0;
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void click(MouseEvent mouseEvent) {
        
    }

    public List<Product> getAllProduct() {

        List<Product> productList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM Product");
            while (rst.next()) {
                productList.add(new Product(rst.getInt(1),rst.getString(2), rst.getString(3),rst.getString(4), rst.getDouble(5),rst.getInt(6), rst.getString(7), rst.getString(8) ));
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
