package io.arvi.office.viewmodels.inventory;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemBrandViewModel implements ViewModel {
    
    private StringProperty parent = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();

    public void initialize() {
        
    }

    public StringProperty parentProperty() {
        return parent;
    }

    public StringProperty nameProperty() {
        return name;
    }
}