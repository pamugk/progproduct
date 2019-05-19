/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Complex;
import common.RootException;
import common.SqrtSolverInterface;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MainForm implements Initializable {
    private URL location;
    private ResourceBundle resources;
    
    @FXML
    private MenuBar mainBar;

    //<editor-fold defaultstate="collapsed" desc="Раздел локализации в меню">
    @FXML
    private Menu localisationMenu;

    @FXML
    private RadioMenuItem enLocalisationItem;

    @FXML
    private RadioMenuItem esLocalisationItem;

    @FXML
    private RadioMenuItem frLocalisationItem;

    @FXML
    private RadioMenuItem hiLocalisationItem;

    @FXML
    private RadioMenuItem plLocalisationItem;

    @FXML
    private RadioMenuItem ptLocalisationItem;

    @FXML
    private RadioMenuItem ruLocalisationItem;

    @FXML
    private RadioMenuItem zhLocalisationItem;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Раздел помощи в меню">
    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem referenceItem;

    @FXML
    private MenuItem aboutItem;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Раздел ввода-вывода в форме">
    @FXML
    private TitledPane inputPane;

    @FXML
    private Button calculateRootBtn;

    @FXML
    private Label numberLabel;

    @FXML
    private TextField numberTextField;

    @FXML
    private Label precisionLabel;

    @FXML
    private TextField precisionTextField;

    @FXML
    private TitledPane resultPane;

    @FXML
    private TextField resultTextField;
    //</editor-fold>

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        location = url;
        resources = rb;

        ToggleGroup localizationGroup = new ToggleGroup();
        localizationGroup.getToggles().addAll(enLocalisationItem, esLocalisationItem, frLocalisationItem,
                hiLocalisationItem, plLocalisationItem, ptLocalisationItem, ruLocalisationItem, zhLocalisationItem);

        ruLocalisationItem.setSelected(true);
        numberTextField.textProperty().addListener(this::listenToNumberTextField);
        precisionTextField.textProperty().addListener(this::listenToPrecisionTextField);

    }

    public void setLocalisation(ResourceBundle localisation){
        resources = localisation;

        localisationMenu.setText(resources.getString("language"));
        helpMenu.setText(resources.getString("help"));
        referenceItem.setText(resources.getString("reference"));
        aboutItem.setText(resources.getString("about"));

        inputPane.setText(resources.getString("input"));
        numberLabel.setText(resources.getString("number"));
        precisionLabel.setText(resources.getString("precision"));
        calculateRootBtn.setText(resources.getString("calculateRoot"));

        resultPane.setText(resources.getString("result"));
    }

    public void setSqrtSolver(SqrtSolverInterface solver){
        this.sqrtSolver = solver;
    }

    private Consumer<Locale> translator;
    public void setLocalizer(Consumer<Locale> translator){
        this.translator = translator;
    }

    private SqrtSolverInterface sqrtSolver;

    //<editor-fold defaultstate="collapsed" desc="Обработчики изменения содержимого текстовых полей">
    private Object number;
    private Double precision;

    private static final String realNumberRegEx = "^-?\\d+(\\.\\d+)?$";
    private static final String complexNumberRegEx = "^(-?\\d+(\\.\\d+)?)([+-](\\d+(\\.\\d+)?)?)i";
    private static final Pattern complexNumberPattern = Pattern.compile(complexNumberRegEx);

    private void checkCalculateButton(){
        calculateRootBtn.setDisable(number == null || precision == null);
    }

    private void listenToNumberTextField(ObservableValue<? extends String> observable, String oldValue, String newValue){
        String trimmedNewValue = newValue.trim();
        number = null;
        if (!trimmedNewValue.isEmpty())
        {
            if (trimmedNewValue.matches(realNumberRegEx)){
                try{
                    number = Double.parseDouble(trimmedNewValue);
                }
                catch (NumberFormatException doubleException){
                    try {
                        number = new BigDecimal(trimmedNewValue);
                    }
                    catch (NumberFormatException ignored){
                    }
                }
            }
            else{
                Matcher matcher = complexNumberPattern.matcher(trimmedNewValue);
                if (matcher.find()){
                    try{
                        double real = Double.parseDouble(matcher.group(1));
                        double imaginary = 1;
                        if (!matcher.group(3).equals("+") && !matcher.group(3).equals("-"))
                            imaginary = Double.parseDouble(matcher.group(3));
                        number = new Complex(real, imaginary);
                    }
                    catch (NumberFormatException ignored){
                    }
                }
            }
        }
        checkCalculateButton();
    }

    private void listenToPrecisionTextField(ObservableValue<? extends String> observable, String oldValue, String newValue){
        String trimmedNewValue = newValue.trim();
        precision = null;
        if (!trimmedNewValue.isEmpty()){
            if (trimmedNewValue.matches(realNumberRegEx)){
                precision = Double.parseDouble(trimmedNewValue);
                if (precision <= 0)
                    precision = null;
            }
        }
        checkCalculateButton();
    }
    //</editor-fold>

    @FXML
    private void showHelpMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, resources.getString("referenceText"));
        alert.setHeaderText(resources.getString("reference"));
        alert.show();
    }

    @FXML
    private void showAboutMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, resources.getString("aboutText"));
        alert.setHeaderText(resources.getString("about"));
        alert.show();
    }

    @FXML
    private void calculateRootBtn_onAction(){
        StringBuilder resultingMessage = new StringBuilder();
        try{
            if (number instanceof Double){
                resultingMessage.append(sqrtSolver.calculateArithmeticalRoot((Double)number, precision));
            }
            else{
                if (number instanceof Complex){
                    sqrtSolver.calculateRootOfComplexNumber((Complex)number, precision)
                            .forEach(num -> resultingMessage.append(String.format("%s\n", num)));
                }
                else{
                    resultingMessage.append(sqrtSolver.calculateRootOfLongNumber((BigDecimal)number, precision));
                }
            }
        } catch (RootException e) {
            resultingMessage.append(String.format("%s\n", resources.getString("error1")));
            resultingMessage.append(resources.getString("error2"));
        }
        resultTextField.setText(resultingMessage.toString());
    }

    //<editor-fold defaultstate="collapsed" desc="Обработчики для смены локализации">
    @FXML
    private void onEnLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("en", "EN"));
    }

    @FXML
    private void onEsLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("es", "ES"));
    }

    @FXML
    private void onFrLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("fr", "FR"));
    }

    @FXML
    private void onHiLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("hi", "IN"));
    }

    @FXML
    private void onPlLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("pl", "PL"));
    }

    @FXML
    private void onPtLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("pt", "PT"));
    }

    @FXML
    private void onRuLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("ru", "RU"));
    }

    @FXML
    private void onZhLocalisationRadioItemChanged(){
        assert translator != null;
        translator.accept(new Locale("zh", "CN"));
    }//</editor-fold>
}
