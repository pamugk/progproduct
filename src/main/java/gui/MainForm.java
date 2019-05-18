/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Complex;
import common.RootException;
import common.SqrtSolverInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MainForm implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private URL location;
    private ResourceBundle resources;
    
    @FXML
    private MenuBar mainBar;

    //<editor-fold defaultstate="collapsed" desc="Раздел локализации в меню">
    @FXML
    private RadioMenuItem ruLocalisationItem;

    @FXML
    private RadioMenuItem enLocalisationItem;

    @FXML
    private RadioMenuItem plLocalisationItem;

    @FXML
    private RadioMenuItem zhLocalisationItem;

    @FXML
    private RadioMenuItem ptLocalisationItem;

    @FXML
    private RadioMenuItem hiLocalisationItem;

    @FXML
    private RadioMenuItem frLocalisationItem;

    @FXML
    private RadioMenuItem esLocalisationItem;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Раздел помощи в меню">
    @FXML
    private MenuItem helpItem;

    @FXML
    private MenuItem aboutItem;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Раздел ввода-вывода в форме">
    @FXML
    private Button calculateRootBtn;

    @FXML
    private TextField numberTextField;

    @FXML
    private TextField degreeTextField;

    @FXML
    private TextField precisionTextField;

    @FXML
    private TextField resultTextField;
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        location = url;
        resources = rb;

        ruLocalisationItem.setSelected(true);
        numberTextField.textProperty().addListener(this::listenToNumberTextField);
        degreeTextField.textProperty().addListener(this::listenToDegreeTextField);
        precisionTextField.textProperty().addListener(this::listenToPrecisionTextField);

    }

    public void setSqrtSolver(SqrtSolverInterface solver){
        this.sqrtSolver = solver;
    }

    private void changeLocalisation(Locale locale){

    }

    private SqrtSolverInterface sqrtSolver;

    //<editor-fold defaultstate="collapsed" desc="Обработчики изменения содержимого текстовых полей">
    private Object number;
    private Double degree;
    private Double precision;

    private static final String realNumberRegEx = "^-?\\d+(\\.\\d+)?$";
    private static final String complexNumberRegEx = "^(-?\\d+(\\.\\d+)?)([+-](%\\d+(\\.\\d+)?)i)?";
    private static final Pattern complexNumberPattern = Pattern.compile(complexNumberRegEx);

    private void checkCalculateButton(){
        calculateRootBtn.setDisable(number == null || degree == null || precision == null);
    }

    private void listenToNumberTextField(ObservableValue<? extends String> observable, String oldValue, String newValue){
        String trimmedNewValue = newValue.trim();
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
                    catch (NumberFormatException bigDecimalException){
                        number = null;
                    }
                }
                calculateRootBtn.setDisable(degree == null || precision == null);
            }
            else{
                Matcher matcher = complexNumberPattern.matcher(complexNumberRegEx);
                if (matcher.find()){
                    try{
                        double real = Double.parseDouble(matcher.group(1));
                        double imaginary = Double.parseDouble(matcher.group(4));
                        number = new Complex(real, imaginary);
                    }
                    catch (NumberFormatException doubleException){
                        number = null;
                    }
                }
            }
            return;
        }
        checkCalculateButton();
    }

    private void listenToDegreeTextField(ObservableValue<? extends String> observable, String oldValue, String newValue){
        String trimmedNewValue = newValue.trim();
        if (!trimmedNewValue.isEmpty()){
            if (trimmedNewValue.matches(realNumberRegEx)){
                degree = Double.parseDouble(trimmedNewValue);
            }
            else degree = null;
        }
        checkCalculateButton();
    }

    private void listenToPrecisionTextField(ObservableValue<? extends String> observable, String oldValue, String newValue){
        String trimmedNewValue = newValue.trim();
        if (!trimmedNewValue.isEmpty()){
            if (trimmedNewValue.matches(realNumberRegEx)){
                precision = Double.parseDouble(trimmedNewValue);
            }
            else precision = null;
        }
        checkCalculateButton();
    }
    //</editor-fold>

    @FXML
    private void showHelpMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Тут будет справка");
        alert.setHeaderText("Справка");
        alert.show();
    }

    @FXML
    private void showAboutMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Это пре-альфа-бета версия программы для вычисления корней чисел, используйте на свой страх и риск");
        alert.setHeaderText("О программе");
        alert.show();
    }

    @FXML
    private void calculateRootBtn_onAction(){
        StringBuilder resultingMessage = new StringBuilder();
        try{
            if (number instanceof Double){
                resultingMessage.append(sqrtSolver.calculateArithmeticalRoot((Double)number, degree, precision));
            }
            else{
                if (number instanceof Complex){
                    sqrtSolver.calculateRootOfComplexNumber((Complex)number, degree, precision)
                            .forEach(num -> resultingMessage.append(String.format("%s\n", num)));
                }
                else{
                    resultingMessage.append(sqrtSolver.calculateRootOfLongNumber((BigDecimal)number, degree, precision));
                }
            }
        } catch (RootException e) {
            resultingMessage.append("К сожалению, во время вычисления корня по заданным данным произошла ошибка.\n");
            resultingMessage.append("Пожалуйста, перепроверьте введенные данные");
        }
        resultTextField.setText(resultingMessage.toString());
    }

    @FXML
    private void onRuLocalisationRadioItemChanged(){
        enLocalisationItem.setSelected(!ruLocalisationItem.isSelected());
        changeLocalisation(new Locale("ru"));
    }

    @FXML
    private void onEnLocalisationRadioItemChanged(){
        ruLocalisationItem.setSelected(!enLocalisationItem.isSelected());
        changeLocalisation(new Locale("en"));
    }
}
