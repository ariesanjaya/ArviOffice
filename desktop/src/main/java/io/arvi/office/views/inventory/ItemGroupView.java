package io.arvi.office.views.inventory;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import io.arvi.office.viewmodels.inventory.ItemGroupViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class ItemGroupView implements FxmlView<ItemGroupViewModel>, Initializable {

    @FXML
    public HBox newBox;
    @FXML
    public HBox saveBox;
    @FXML
    public Button newButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button refreshButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button cancelButton;
    @FXML
    public TextField parentTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TreeView<Object> treeView;

    @InjectViewModel
    private ItemGroupViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyBinding();
    }

    private void propertyBinding() {
        parentTextField.textProperty().bind(viewModel.parentProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
    }

}