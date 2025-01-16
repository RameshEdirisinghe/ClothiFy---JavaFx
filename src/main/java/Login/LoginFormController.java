package Login;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInLeft;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    public AnchorPane anchorPaneSignUp;
    public AnchorPane loginAnchorPane;
    public AnchorPane img1AnchorPane;
    public AnchorPane img2AnchorPane;
    public AnchorPane img3AnchorPane;
    public AnchorPane img0AnchorPane;
    public PasswordField txtPassword;
    public TextField txtEmail;


    public void mounseOnClickimg3(MouseEvent mouseEvent) {

        img1AnchorPane.setVisible(false);     // Hide img1AnchorPane
        img2AnchorPane.setVisible(false);     // Hide img2AnchorPane
        img3AnchorPane.setVisible(true);      // Show img3AnchorPane
        new FadeIn(img3AnchorPane).play();    // Apply FadeIn animation
        img3AnchorPane.toFront();
    }

    public void mounseOnClickimg2(MouseEvent mouseEvent) {

        img1AnchorPane.setVisible(false);     // Hide img1AnchorPane
        img2AnchorPane.setVisible(true);      // Show img2AnchorPane
        img3AnchorPane.setVisible(false);     // Hide img3AnchorPane
        new FadeIn(img2AnchorPane).play();    // Apply FadeIn animation
        img2AnchorPane.toFront();
    }

    public void mounseOnClickimg1(MouseEvent mouseEvent) {

        img1AnchorPane.setVisible(true);      // Show img1AnchorPane
        img2AnchorPane.setVisible(false);     // Hide img2AnchorPane
        img3AnchorPane.setVisible(false);     // Hide img3AnchorPane
        new FadeIn(img1AnchorPane).play();    // Apply FadeIn animation
        img1AnchorPane.toFront();
    }

    public void btnOnActionRecoveryPassword(ActionEvent actionEvent) {

    }

    public void btnOnClickActionLogin(ActionEvent actionEvent) {
        
    }
}
