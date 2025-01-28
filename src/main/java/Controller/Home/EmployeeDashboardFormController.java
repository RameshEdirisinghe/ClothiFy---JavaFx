package Controller.Home;

import Controller.Products.MyListener;
import Controller.Products.ProductFormController;
import Controller.Products.ProductsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDashboardFormController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private ComboBox<?> cmbProductCategory;

    @FXML
    private ComboBox<?> cmbProductSize;

    @FXML
    private ComboBox<?> cmbProductSupplier;

    @FXML
    private TableColumn<?, ?> colEmpCompany;

    @FXML
    private TableColumn<?, ?> colEmpEmail;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private ImageView fruitImg;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView imgViewUpImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtEmpCompany;

    @FXML
    private TextField txtEmpEmail;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpPassword;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductQty;

    @FXML
    private TextField txtSearchEmpId;
    private List<Product> products = new ArrayList();
    private Image image;
    private MyListener myListener;

    @FXML
    void btnOnActionImportImg(ActionEvent event) {

    }

    @FXML
    void btnOnClickActionAdd(ActionEvent event) {

    }

    @FXML
    void btnOnClickActionEmpAdd(ActionEvent event) {

    }

    @FXML
    void btnOnClickActionEmpDelete(ActionEvent event) {

    }

    @FXML
    void btnOnClickActionEmpSearch(ActionEvent event) {

    }

    @FXML
    void btnOnClickActionEmpUpdate(ActionEvent event) {

    }

    private List<Product> getData() {
        List<Product> product = new ArrayList();
        for(Product p:ProductsController.getInstance().getAllProduct()){
            product.add(p);
        }
        return product;
    }

    private void setChosenProduct(Product product) {
        this.fruitNameLable.setText(product.getName());
        this.fruitPriceLabel.setText("$" + product.getPrice());
        this.image = new Image(product.getImgPath());
        this.fruitImg.setImage(this.image);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.products.addAll(this.getData());

        System.out.println(products);
        if (this.products.size() > 0) {
            this.setChosenProduct(this.products.get(0));
            this.myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                }
            };

        }

        int column = 0;
        int row = 1;

        try {
            for(int i = 0; i < this.products.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/view/product.fxml"));
                AnchorPane anchorPane = (AnchorPane)fxmlLoader.load();
                ProductFormController itemController = (ProductFormController)fxmlLoader.getController();
                itemController.setData(this.products.get(i), this.myListener);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                this.grid.add(anchorPane, column++, row);
                this.grid.setMinWidth(-1.0);
                this.grid.setPrefWidth(-1.0);
                this.grid.setMaxWidth(Double.NEGATIVE_INFINITY);
                this.grid.setMinHeight(-1.0);
                this.grid.setPrefHeight(-1.0);
                this.grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(10.0));
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }
    }

    public void addToCart(MouseEvent mouseEvent) {
        
    }
}
