package io.arvi.office.views.purchase;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import io.arvi.office.viewmodels.purchase.SupplierDetailViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class SupplierDetailView implements FxmlView<SupplierDetailViewModel>, Initializable {

    @FXML
    public Button newButton;
    @FXML
    public Button openButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyBinding();
    }

    private void propertyBinding() {

    }

}