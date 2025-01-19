package User;

import DBConnection.DBConnection;
import Employee.EmployeeController;
import model.Employee;

import java.awt.dnd.DnDConstants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private static UserController instance;

    private UserController() {
    }

    public static UserController getInstance() {
        return instance == null ? instance = new UserController() : instance;
    }

    public boolean addUser(Employee employee){

        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert Into user(Name,Email,Password,Role) values (?,?,?,?)");
            stm.setString(1,employee.getName());
            stm.setString(2,employee.getEmail());
            stm.setString(3,employee.getPassword());
            stm.setString(4,"Employee");

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean updateUser(Employee employee){
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE user SET Name = ?, Email = ?, password = ? WHERE Name =?;");
            stm.setString(1,employee.getName());
            stm.setString(2,employee.getEmail());
            stm.setString(3,employee.getPassword());
            stm.setString(4,employee.getName());

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(String userName){
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM user WHERE name=?");
            stm.setString(1, userName);

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
