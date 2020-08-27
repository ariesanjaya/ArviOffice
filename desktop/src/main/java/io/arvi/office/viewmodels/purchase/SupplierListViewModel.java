package io.arvi.office.viewmodels.purchase;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierListViewModel implements ViewModel {
    
    private Command newCommand;
    private Command editCommand;
    private Command refreshCommand;

    private StringProperty name = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty district = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private BooleanProperty active = new SimpleBooleanProperty(true);

    public void initialize() {
        newCommand  = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            }
        });

        editCommand = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            }
        });

        refreshCommand = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            }
        });
    }   

    public Command getNewCommand() {
        return newCommand;
    }

    public Command getEditCommand() {
        return editCommand;
    }

    public Command getRefreshCommand() {
        return refreshCommand;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty districtProperty() {
        return district;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public BooleanProperty activeProperty() {
        return active;
    }
}