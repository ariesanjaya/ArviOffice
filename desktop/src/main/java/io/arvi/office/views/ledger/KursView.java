package io.arvi.office.views.ledger;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import io.arvi.office.viewmodels.ledger.KursViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class KursView implements FxmlView<KursViewModel>, Initializable {

    @FXML
    private HBox newBox;
    @FXML
    private HBox saveBox;
    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    @FXML
    private TextField kursIdTextField;
    @FXML
    private TextField kursDescriptionTextField;
    @FXML
    private TextField kursSymbolTextField;
    @FXML
    private TextField kursValueTextField;
    @FXML
    private TextField kursExchangeTextField;
    @FXML
    private CheckBox kursActiveCheckBox;

    @FXML
    private TableView<Object> table;
    @FXML
    private TableColumn<Object, String> kursIdColumn;
    @FXML
    private TableColumn<Object, String> kursDescriptionColumn;
    @FXML
    private TableColumn<Object, String> kursSymbolColumn;
    @FXML
    private TableColumn<Object, BigDecimal> kursValueColumn;
    @FXML
    private TableColumn<Object, BigDecimal> kursExchangeColumn;
    @FXML
    private TableColumn<Object, Boolean> kursActiveColumn;

    @InjectViewModel
    private KursViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyBinding();
    }

    private void propertyBinding() {
        kursIdTextField.textProperty().bindBidirectional(viewModel.kursIdProperty());
        kursDescriptionTextField.textProperty().bindBidirectional(viewModel.kursDescriptionProperty());
        kursSymbolTextField.textProperty().bindBidirectional(viewModel.kursSymbolProperty());
        kursValueTextField.textProperty().bindBidirectional(viewModel.kursValueProperty());
        kursExchangeTextField.textProperty().bindBidirectional(viewModel.kursExchangeProperty());
        kursActiveCheckBox.selectedProperty().bindBidirectional(viewModel.kursActiveProperty());
    }

    @FXML
    private void newAction() {

    }

    @FXML
    private void editAction() {

    }

    @FXML
    private void deleteAction() {

    }

    @FXML
    private void refreshAction() {

    }

    @FXML
    private void saveAction() {

    }

    @FXML
    private void cancelAction() {

    }
}