package Controller.Supplier;


import DBConnection.DBConnection;
import model.Employee;
import model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    private static SupplierController instance;

    private SupplierController() {
    }

    public static SupplierController getInstance() {
        return instance == null ? instance = new SupplierController() : instance;
    }

    public List<String> getAllSupplierNames(){
        List<String> supplierList = new ArrayList<>();

        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT name FROM Supplier");
            while (rst.next()) {

                supplierList.add(rst.getString(1));

            }
            return supplierList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
