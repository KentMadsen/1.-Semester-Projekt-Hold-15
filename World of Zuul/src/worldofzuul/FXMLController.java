/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bytof
 */
public class FXMLController implements Initializable {

    @FXML
    private ProgressBar healthbar;
    @FXML
    private ProgressBar hungerbar;
    @FXML
    private ProgressBar thirstbar;
    @FXML
    private ProgressBar illnessbar;
    @FXML
    private Label invSlot1;
    @FXML
    private Label invSlot2;
    @FXML
    private Label invSlot3;
    @FXML
    private Label invSlot4;
    @FXML
    private Label tempLabel;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button mainBtn;
    @FXML
    private Button btnContinue;
    @FXML
    private Button btnQuit;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == btnContinue) {
            //get reference to the button's stage         
            stage = (Stage) btnContinue.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        } else if (event.getSource() == btnNewGame) {
            //get reference to the button's stage         
            stage = (Stage) btnNewGame.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        } else {
            stage = (Stage) mainBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        }

        //create a new scene with root and set the stage
        Scene scene = new Scene(root, 1200, 680);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void quitGame(ActionEvent event) throws IOException {
        //hider scenen, og slukker spillet
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (event.getX() < 50 && event.getX() > 0 && event.getY() < 365 && event.getY() > 315) {
            NewFXMain.spil.goRoom("west");
            tempLabel.setText("You walked west");
            updateStats();
        } else if (event.getX() < 1000 && event.getX() > 950 && event.getY() < 365 && event.getY() > 315) {
            NewFXMain.spil.goRoom("east");
            tempLabel.setText("You walked east");
            updateStats();
        } else if (event.getX() < 525 && event.getX() > 475 && event.getY() < 50 && event.getY() > 0) {
            NewFXMain.spil.goRoom("north");
            tempLabel.setText("You walked north");
            updateStats();
        } else if (event.getX() < 525 && event.getX() > 475 && event.getY() < 680 && event.getY() > 630) {
            NewFXMain.spil.goRoom("south");
            tempLabel.setText("You walked south");
            updateStats();
        } else if (event.getX() < 50 && event.getX() > 0 && event.getY() < 50 && event.getY() > 0) {
            NewFXMain.spil.goRoom("northwest");
            tempLabel.setText("You walked northwest");
            updateStats();
        } else if (event.getX() < 1000 && event.getX() > 950 && event.getY() < 50 && event.getY() > 0) {
            NewFXMain.spil.goRoom("northeast");
            tempLabel.setText("You walked northeast");
            updateStats();
        } else if (event.getX() < 50 && event.getX() > 0 && event.getY() < 680 && event.getY() > 630) {
            NewFXMain.spil.goRoom("southwest");
            tempLabel.setText("You walked southwest");
            updateStats();
        } else if (event.getX() < 1000 && event.getX() > 950 && event.getY() < 680 && event.getY() > 630) {
            NewFXMain.spil.goRoom("southeast");
            tempLabel.setText("You walked southeast");
            updateStats();
        }

    }

    private void updateStats() {
        double passnumber = NewFXMain.spil.player.getThirst();
        this.thirstbar.setProgress(passnumber / 100);
        passnumber = NewFXMain.spil.player.getHunger();
        this.hungerbar.setProgress(passnumber / 100);
        passnumber = NewFXMain.spil.player.getHealth();
        this.healthbar.setProgress(passnumber / 100);
        passnumber = NewFXMain.spil.player.getIllness();
        this.illnessbar.setProgress(passnumber / 100);
    }

}