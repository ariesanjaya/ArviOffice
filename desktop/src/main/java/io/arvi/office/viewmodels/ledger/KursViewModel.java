package io.arvi.office.viewmodels.ledger;

import java.math.BigDecimal;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;

public class KursViewModel implements ViewModel {
    
    private Command newCommand;
    private Command editCommand;
    private Command deleteCommand;
    private Command refreshCommand;
    private Command saveCommand;
    private Command cancelCommand;

    private StringProperty kursId = new SimpleStringProperty();
    private StringProperty kursDescription = new SimpleStringProperty();
    private StringProperty kursSymbol = new SimpleStringProperty();
    private StringProperty kursValueString = new SimpleStringProperty();
    private StringProperty kursExchangeString = new SimpleStringProperty();
    private ObjectProperty<BigDecimal> kursValue = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> kursExchange = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
    private BooleanProperty kursActive = new SimpleBooleanProperty(true);

    public void initialize() {

        StringConverter<BigDecimal> kursValueConverter = new BigDecimalStringConverter();
        Bindings.bindBidirectional(kursValueString, kursValue, kursValueConverter);
        StringConverter<BigDecimal> kursExchangeConverter = new BigDecimalStringConverter();
        Bindings.bindBidirectional(kursExchangeString, kursExchange, kursExchangeConverter);

        newCommand = new DelegateCommand(() -> new Action(){
           @Override
           protected void action() throws Exception {
               
           } 
        });

        editCommand = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            } 
        });

        deleteCommand = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            } 
        });

        refreshCommand = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            } 
        });

        saveCommand = new DelegateCommand(() -> new Action(){
            @Override
            protected void action() throws Exception {
                
            } 
        });

        cancelCommand = new DelegateCommand(() -> new Action(){
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

    public Command getDeleteCommand() {
        return deleteCommand;
    }

    public Command getRefreshCommand() {
        return refreshCommand;
    }

    public Command getSaveCommand() {
        return saveCommand;
    }

    public Command getCancelCommand() {
        return cancelCommand;
    }

    public StringProperty kursIdProperty() {
        return kursId;
    }

    public StringProperty kursDescriptionProperty() {
        return kursDescription;
    }

    public StringProperty kursSymbolProperty() {
        return kursSymbol;
    }

    public StringProperty kursValueProperty() {
        return kursValueString;
    }

    public StringProperty kursExchangeProperty() {
        return kursExchangeString;
    }

    public BooleanProperty kursActiveProperty() {
        return kursActive;
    }

}