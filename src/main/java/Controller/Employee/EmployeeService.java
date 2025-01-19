package Employee;

import DBConnection.DBConnection;
import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
}
