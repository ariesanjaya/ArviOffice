package io.arvi.office.views.purchase;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import io.arvi.office.viewmodels.purchase.SupplierListViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SupplierListView implements FxmlView<SupplierListViewModel>, Initializable {

    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button refreshButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField districtTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private CheckBox activeCheckBox;
    
    @FXML
    private TableView<Object> table;
    @FXML
    private TableColumn<Object, String> supplierIdColumn;
    @FXML
    private TableColumn<Object, String> supplierNameColumn;
    @FXML
    private TableColumn<Object, String> supplierAddressColumn;
    @FXML
    private TableColumn<Object, String> supplierDistrictColumn;
    @FXML
    private TableColumn<Object, String> supplierCityColumn;
    @FXML
    private TableColumn<Object, Boolean> supplierActiveColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyBinding();
    }

    private void propertyBinding() {

    }
}