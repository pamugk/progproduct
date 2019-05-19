import common.Complex;
import common.RootException;
import common.SqrtSolver;
import common.SqrtSolverInterface;
import gui.MainForm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    private Stage stage;
    private MainForm form;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainForm.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("localisation/Localisation", Locale.getDefault());

        Parent root = loader.load();
        form = loader.getController();
        primaryStage.setTitle(bundle.getString("title"));
        form.setLocalisation(bundle);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("graphics/sqrt.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        form.setSqrtSolver(new SqrtSolver());
    }

    private void updateLocalization(Locale locale){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainForm.fxml"));
        ResourceBundle localisationBundle = ResourceBundle.getBundle("localisation/Localisation", locale);
        loader.setResources(localisationBundle);
        stage.setTitle(localisationBundle.getString("title"));
        form.setLocalisation(localisationBundle);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
