package io.arvi.office.views.company;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import io.arvi.office.viewmodels.company.CompanyViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CompanyView implements FxmlView<CompanyViewModel>, Initializable {

    @FXML
    public TextField companyNameTextField;
    @FXML
    public TextArea companyDescriptionTextArea;
    @FXML
    public ComboBox<Integer> taxYearComboBox;
    @FXML
    public TextField taxIdTextField;
    @FXML
    public TextField taxResponsibleIdTextField;
    @FXML
    public DatePicker taxResponsibleDatePicker;
    @FXML
    public Button companySaveButton;
    @FXML
    public Button branchNewButton;
    @FXML
    public Button branchEditButton;
    @FXML
    public Button branchRefreshButton;
    @FXML
    public Button branchSaveButton;
    @FXML
    public Button branchCancelButton;

    @FXML
    public TextField branchIdTextField;;
    @FXML
    public TextField branchNameTextField;
    @FXML
    public TextField branchAddressTextField;;
    @FXML
    public TextField branchDistrictTextField;
    @FXML
    public TextField branchCityTextField;;
    @FXML
    public TextField branchPhoneTextField;
    @FXML
    public CheckBox branchActiveCheckBox;
    @FXML
    public TableView<Object> branchTable;
    @FXML
    public TableColumn<Object, String> branchIdColumn;
    @FXML
    public TableColumn<Object, String> branchNameColumn;
    @FXML
    public TableColumn<Object, Boolean> branchActiveColumn;
    @FXML
    public TableColumn<Object, String> branchAddressColumn;
    @FXML
    public TableColumn<Object, String> branchPhoneColumn;

    @InjectViewModel
    private CompanyViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyBinding();
    }

    private void propertyBinding() {
        companyNameTextField.textProperty().bindBidirectional(viewModel.companyNameProperty());
        companyDescriptionTextArea.textProperty().bindBidirectional(viewModel.companyDescriptionProperty());
        taxYearComboBox.valueProperty().bindBidirectional(viewModel.taxYearProperty());
        taxIdTextField.textProperty().bindBidirectional(viewModel.taxIdProperty());
        taxResponsibleIdTextField.textProperty().bindBidirectional(viewModel.taxResponsibleIdProperty());
        taxResponsibleDatePicker.valueProperty().bindBidirectional(viewModel.taxResponsibleDateProperty());

        branchIdTextField.textProperty().bindBidirectional(viewModel.branchIdProperty());
        branchNameTextField.textProperty().bindBidirectional(viewModel.branchNameProperty());
        branchAddressTextField.textProperty().bindBidirectional(viewModel.branchAddressProperty());
        branchDistrictTextField.textProperty().bindBidirectional(viewModel.branchDistrictProperty());
        branchCityTextField.textProperty().bindBidirectional(viewModel.branchCityProperty());
        branchPhoneTextField.textProperty().bindBidirectional(viewModel.branchPhoneProperty());
        branchActiveCheckBox.selectedProperty().bindBidirectional(viewModel.branchActiveProperty());
    }

}