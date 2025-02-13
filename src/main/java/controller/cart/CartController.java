package controller.cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CartDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    private static ObservableList<CartDetails> cartItems;
    Double total;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colProductPrice;

    @FXML
    private TableColumn colProductQty;

    @FXML
    private Label lnlTotal;

    @FXML
    private TableView tblCart;

    @FXML
    private TextField txtFieldCustomerName;

    @FXML
    void btnOnClickActionCancel(ActionEvent event) {

    }

    @FXML
    void btnOnClickActionPrintBill(ActionEvent event) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Draw border
            contentStream.setLineWidth(2);
            contentStream.moveTo(50, 800);
            contentStream.lineTo(550, 800);
            contentStream.lineTo(550, 50);
            contentStream.lineTo(50, 50);
            contentStream.lineTo(50, 800);
            contentStream.stroke();

            // Shop Name
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(200, 770);
            contentStream.showText("Shelby Threads");
            contentStream.endText();

            // Date and Time
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = formatter.format(new Date());
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(70, 740);
            contentStream.showText("Date: " + dateTime);
            contentStream.endText();

            // Customer Name
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(70, 710);
            contentStream.showText("Customer: " + txtFieldCustomerName.getText());
            contentStream.endText();

            // Table Headers
            contentStream.beginText();
            contentStream.newLineAtOffset(70, 680);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.showText("Item                     Qty    Price     Total");
            contentStream.endText();

            int yPosition = 660;
            double totalAmount = 0;
            for (CartDetails product : cartItems) {
                double total = product.getQty() * product.getPrice();
                totalAmount += total;

                contentStream.beginText();
                contentStream.newLineAtOffset(70, yPosition);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.showText(String.format("%-20s %5d  $%6.2f  $%6.2f", product.getProductName(), product.getQty(), product.getPrice(), total));
                contentStream.endText();

                yPosition -= 20;
            }

            // Total Bill Amount
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(70, yPosition - 30);
            contentStream.showText("Total Bill: $" + String.format("%.2f", total));
            contentStream.endText();

            contentStream.close();

            String filePath = "ShelbyThreads_Bill.pdf";
            document.save(filePath);
            document.close();

            System.out.println("Bill generated successfully: " + filePath);
            printPDF(filePath);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printPDF(String filePath) {
        try {
            // Load the PDF file
            PDDocument document = PDDocument.load(new File(filePath));

            // Get the default printer
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            if (printService != null) {
                // Create a printer job
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintService(printService);

                // Wrap the PDDocument in a PDFPrintable object
                PDFPrintable printable = new PDFPrintable(document, Scaling.SHRINK_TO_FIT);

                // Set the printable to the PDF document
                job.setPrintable(printable);

                // Print the document
                job.print();
                System.out.println("PDF printed successfully.");
            } else {
                System.out.println("No printer found.");
            }

            // Close the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCartArray(ArrayList<CartDetails> cartArray){
        cartItems = FXCollections.observableArrayList();
        cartArray.forEach(cartItem ->{
            cartItems.add(cartItem);
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadTable();
    }

    public void loadTable(){
        tblCart.setItems(cartItems);
        total=0.0;

       for(CartDetails item : cartItems){
            total += item.getPrice()* item.getQty();
        }
        lnlTotal.setText(total.toString());
    }
}
