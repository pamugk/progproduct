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

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainForm.fxml"));

        Parent root = loader.load();
        MainForm form = loader.getController();
        primaryStage.setTitle("Извлекатель корней");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("graphics/sqrt.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        form.setSqrtSolver(new SqrtSolver());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
