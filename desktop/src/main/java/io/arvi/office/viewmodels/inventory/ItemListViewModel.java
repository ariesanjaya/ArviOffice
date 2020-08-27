package io.arvi.office.viewmodels.inventory;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemListViewModel implements ViewModel {
    
    private StringProperty barcode = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private BooleanProperty active = new SimpleBooleanProperty(true);

    public void initialize() {

    }

    public StringProperty barcodeProperty() {
        return barcode;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public BooleanProperty activeProperty() {
        return active;
    }
}