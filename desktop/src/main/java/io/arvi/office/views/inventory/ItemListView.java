package io.arvi.office.views.inventory;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import io.arvi.office.viewmodels.inventory.ItemListViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemListView implements FxmlView<ItemListViewModel>, Initializable {

    @FXML
    public Button newButton;
    @FXML
    public Button editButton;
    @FXML
    public Button refreshButton;
    @FXML
    public TextField barcodeTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public ComboBox<Object> brandBox;
    @FXML
    public ComboBox<Object> groupBox;
    @FXML
    public CheckBox activeCheckBox;

    @FXML
    public TableView<Object> table;
    @FXML
    public TableColumn<Object, String> itemIdColumn;
    @FXML
    public TableColumn<Object, String> barcodeColumn;
    @FXML
    public TableColumn<Object, String> nameColumn;
    @FXML
    public TableColumn<Object, String> unitColumn;

    @InjectViewModel
    private ItemListViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyBinding();
    }

    private void propertyBinding() {
        barcodeTextField.textProperty().bindBidirectional(viewModel.barcodeProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        activeCheckBox.selectedProperty().bindBidirectional(viewModel.activeProperty());
    }
}