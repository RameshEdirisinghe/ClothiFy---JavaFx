package Controller.Products;


import DBConnection.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            ResultSet rst = connection.createStatement().executeQuery("Select Name form supplier where Name='"+product.getSuppler()+"'");

            PreparedStatement stm = connection.prepareStatement("Insert Into Product(ProductName,Category,Size,Price,Quantity,Image,SupplierID) values (?,?,?,?,?,?,?)");
            stm.setString(1,product.getName());
            stm.setString(2,product.getCategory());
            stm.setString(3,product.getSize());
            stm.setDouble(4,product.getPrice());
            stm.setInt(5,product.getQty());
            stm.setString(6,product.getImgPath());
            stm.setInt(7,rst.getInt(1));

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
