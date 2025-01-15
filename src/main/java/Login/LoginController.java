package Login;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    public AnchorPane anchorPaneSignUp;
    public AnchorPane loginAnchorPane;
    public AnchorPane img1AnchorPane;
    public AnchorPane img2AnchorPane;
    public AnchorPane img3AnchorPane;

    public void btnOnActionSignUp(ActionEvent actionEvent) {

        loginAnchorPane.setVisible(false);
        new FadeIn(anchorPaneSignUp).play();
        anchorPaneSignUp.toFront();
    }

    public void btnOnActionLogIn(ActionEvent actionEvent) {
        loginAnchorPane.setVisible(true);
//        anchorPaneSignUp.setVisible(false);

        new FadeIn(loginAnchorPane).play();
        loginAnchorPane.toFront();
    }

    public void btnOnActionCreateAcc(ActionEvent actionEvent) {
        loginAnchorPane.setVisible(true);
//        anchorPaneSignUp.setVisible(false);

        new FadeIn(loginAnchorPane).play();
        loginAnchorPane.toFront();
    }

    public void mounseOnClickimg3(MouseEvent mouseEvent) {
        System.out.println("awo3");


        img1AnchorPane.setVisible(true);
        new FadeIn(img1AnchorPane).play();
        img1AnchorPane.toFront();
        img1AnchorPane.toFront();
    }

    public void mounseOnClickimg2(MouseEvent mouseEvent) {
        System.out.println("awo2");
        img1AnchorPane.setVisible(false);
        img2AnchorPane.setVisible(false);
        new FadeIn(img3AnchorPane).play();
        img3AnchorPane.toFront();
    }

    public void mounseOnClickimg1(MouseEvent mouseEvent) {
        System.out.println("awo1");
        img1AnchorPane.setVisible(false);
        new FadeIn(img2AnchorPane).play();
        img2AnchorPane.toFront();

    }
}
