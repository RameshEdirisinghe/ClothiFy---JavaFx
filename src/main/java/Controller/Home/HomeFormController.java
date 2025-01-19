package Home;

import Employee.EmployeeController;
import animatefx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Employee;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeFormController implements Initializable {
    public Label lbltopic2;
    public Label lbltopic1;
    public AnchorPane lblPane1;
    public AnchorPane lblPane02;
    public NumberAxis StackedAreaChartYAxis;
    public NumberAxis StackedAreaChartXAxis;
    public javafx.scene.chart.StackedAreaChart StackedAreaChart;
    public AnchorPane pane2ndImgs;
    public AnchorPane pane1stImgs;
    public TableColumn colEmpEmail;
    public TableColumn colEmpName;
    public TableColumn colEmpId;
    public TableView tblEmployee;
    public TextField txtEmpPassword;
    public TextField txtEmpEmail;
    public TextField txtEmpName;
    public TextField txtSearchEmpId;
    public AnchorPane anchorPaneImgLoader;
    public TextField txtEmpCompany;
    public TableColumn colEmpCompany;
    public ImageView imgViewUpImg;
    private String currentImagePath;
//    public Static String usrId;

    public static void setUserId(String usrId){
        String userId=usrId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Start Animation
        ZoomInLeft ZoomInLeftAnimation = new ZoomInLeft(lblPane1);
        ZoomInLeftAnimation.setSpeed(0.25);
        ZoomInLeftAnimation.play();

        ZoomInRight ZoomInRightAnimation = new ZoomInRight(lblPane02);
        ZoomInRightAnimation.setSpeed(0.25);
        ZoomInRightAnimation.play();


        //load charts
        StackedAreaChartXAxis.setLabel("Days (Last 10 Days)");
        StackedAreaChartYAxis.setLabel("Sales");

        // Create series for each product
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("FlatCaps");

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("WoolSuits");

        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("High-Waist");

        // Example sales data for the last 10 days (static)
        int[] salesProductA = {10, 15, 20, 17, 12, 25, 30, 18, 22, 28}; // Product A sales
        int[] salesProductB = {12, 14, 18, 16, 20, 27, 31, 24, 19, 26}; // Product B sales
        int[] salesProductC = {8, 10, 14, 19, 15, 21, 24, 22, 26, 30};  // Product C sales

        // Add data to the series for each day (1 to 10)
        for (int i = 0; i < 10; i++) {
            // Adding data points to the series
            series1.getData().add(new XYChart.Data<>(i + 1, salesProductA[i]));
            series2.getData().add(new XYChart.Data<>(i + 1, salesProductB[i]));
            series3.getData().add(new XYChart.Data<>(i + 1, salesProductC[i]));
        }

        // Add the series to the StackedAreaChart
        StackedAreaChart.getData().addAll(series1, series2, series3);

        // Optional: Customize axis ranges
        StackedAreaChartXAxis.setLowerBound(1); // Start at day 1
        StackedAreaChartXAxis.setUpperBound(10); // End at day 10
        StackedAreaChartYAxis.setLowerBound(0);
        StackedAreaChartYAxis.setUpperBound(100); // Adjust the max sales value as needed

        //set Tables Cols
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        loadTable();


    }

    public void btnOnClickActionNext(MouseEvent mouseEvent) {
        pane1stImgs.setVisible(false);     // Hide img1AnchorPane
        pane2ndImgs.setVisible(true);      // Show img3AnchorPane
        new FadeIn(pane2ndImgs).play();    // Apply FadeIn animation
        pane2ndImgs.toFront();
    }

    public void btnOnActionPrevious(MouseEvent mouseEvent) {
        pane2ndImgs.setVisible(false);     // Hide img1AnchorPane
        pane1stImgs.setVisible(true);      // Show img3AnchorPane
        new FadeIn(pane1stImgs).play();    // Apply FadeIn animation
        pane1stImgs.toFront();
    }

    public void loadTable(){
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        for (Employee employee : EmployeeController.getInstance().getAllEmployee()) {
            employeeObservableList.add(employee);
        }
        tblEmployee.setItems(employeeObservableList);
    }

    public void btnOnClickActionEmpDelete(ActionEvent actionEvent) {
        if (EmployeeController.getInstance().deleteEmployee(txtSearchEmpId.getText(),txtEmpName.getText())){
            loadTable();
            new Alert(Alert.AlertType.INFORMATION,"Employee Delete successfully").show();

        }else {
            new Alert(Alert.AlertType.INFORMATION,"Employee Delete fail").show();
        }

    }

    public void btnOnClickActionEmpAdd(ActionEvent actionEvent) {
        if (EmployeeController.getInstance().addEmployee(new Employee(0,txtEmpName.getText(),txtEmpEmail.getText(),txtEmpPassword.getText(),txtEmpCompany.getText()))){
            loadTable();
            new Alert(Alert.AlertType.INFORMATION,"Employee Added successfully").show();

        }else {
            new Alert(Alert.AlertType.INFORMATION,"Employee Added fail").show();
        }
    }

    public void btnOnClickActionEmpUpdate(ActionEvent actionEvent) {
        if (EmployeeController.getInstance().updateEmployee(new Employee( Integer.parseInt(txtSearchEmpId.getText()),txtEmpName.getText(),txtEmpEmail.getText(),txtEmpPassword.getText(),txtEmpCompany.getText()))){
            loadTable();
            new Alert(Alert.AlertType.INFORMATION,"Employee updated successfully").show();

        }else {
            new Alert(Alert.AlertType.INFORMATION,"Employee updated fail").show();
        }
    }

    public void btnOnClickActionEmpSearch(ActionEvent actionEvent) {
        Employee employee = EmployeeController.getInstance().searchEmployee(txtSearchEmpId.getText());

        if (employee==null){
            new Alert(Alert.AlertType.INFORMATION,"Please Enter Valid Employee Id").show();
        }else {

            txtEmpName.setText(employee.getName());
            txtEmpEmail.setText(employee.getEmail());
            txtEmpCompany.setText(employee.getCompany());
        }
    }

    public void btnOnActionImportImg(ActionEvent actionEvent) throws IOException {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter exxtFilterJpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter exxtFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        chooser.getExtensionFilters().addAll(exxtFilterJpg, exxtFilterPNG);

        File file = chooser.showOpenDialog(null);

        if (file != null) {
                String imagePath = file.getAbsolutePath();
                Image image = new Image(imagePath);
                imgViewUpImg.setImage(image);
        }

    }

    public void btnOnClickActionAdd(ActionEvent actionEvent) {

        imgViewUpImg.
    }
}
