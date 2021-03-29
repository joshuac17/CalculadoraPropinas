package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final NumberFormat moneda = NumberFormat.getCurrencyInstance();
    private static final NumberFormat porcentaje = NumberFormat.getPercentInstance();

    private BigDecimal porcentajePropina = new BigDecimal(0.15);

    @FXML
    Label etiquetaPorcentaje;

    @FXML
    TextField campoCantidad;

    @FXML
    TextField campoPropina;

    @FXML
    TextField campoTotal;

    @FXML
    Slider slider;

    @FXML
    Button btnCalcular;

    @FXML
    private void calcularPropina(ActionEvent event){

        try{
            BigDecimal cantidad = new BigDecimal(campoCantidad.getText());
            BigDecimal propina = cantidad.multiply(porcentajePropina);
            BigDecimal total =  cantidad.add(propina);

            campoPropina.setText(moneda.format(propina));
            campoTotal.setText(moneda.format(total));
        } catch (NumberFormatException ex){
            campoCantidad.setText("Ingrese una cantidad numérica");
            campoCantidad.selectAll();
            campoCantidad.requestFocus();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneda.setRoundingMode(RoundingMode.HALF_UP);

        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        porcentajePropina = BigDecimal.valueOf(newValue.intValue()/100.0);

                        etiquetaPorcentaje.setText(porcentaje.format(porcentajePropina));
                    }
                }
        );
    }
}
