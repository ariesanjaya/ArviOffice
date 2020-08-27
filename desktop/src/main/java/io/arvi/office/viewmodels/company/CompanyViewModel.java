package io.arvi.office.viewmodels.company;

import java.time.LocalDate;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanyViewModel implements ViewModel {

    private StringProperty companyName = new SimpleStringProperty();
    private StringProperty companyDescription = new SimpleStringProperty();
    private ObjectProperty<Integer> taxYear = new SimpleObjectProperty<Integer>();
    private StringProperty taxId = new SimpleStringProperty();
    private StringProperty taxResponsibleId = new SimpleStringProperty();
    private ObjectProperty<LocalDate> taxResponsibleDate = new SimpleObjectProperty<LocalDate>();

    private StringProperty branchId = new SimpleStringProperty();
    private StringProperty branchName = new SimpleStringProperty();
    private StringProperty branchAddress = new SimpleStringProperty();
    private StringProperty branchDistrict = new SimpleStringProperty();
    private StringProperty branchCity = new SimpleStringProperty();
    private StringProperty branchPhone = new SimpleStringProperty();
    private BooleanProperty branchActive = new SimpleBooleanProperty(true);

    public void initialize() {
        companyName.set("Arvi Mandiri Grosir");
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public StringProperty companyDescriptionProperty() {
        return companyDescription;
    }

    public ObjectProperty<Integer> taxYearProperty() {
        return taxYear;
    }

    public StringProperty taxIdProperty() {
        return taxId;
    }

    public StringProperty taxResponsibleIdProperty() {
        return taxResponsibleId;
    }

    public ObjectProperty<LocalDate> taxResponsibleDateProperty() {
        return taxResponsibleDate;
    }

    public StringProperty branchIdProperty() {
        return branchId;
    }

    public StringProperty branchNameProperty() {
        return branchName;
    }

    public StringProperty branchAddressProperty() {
        return branchAddress;
    }

    public StringProperty branchDistrictProperty() {
        return branchDistrict;
    }

    public StringProperty branchCityProperty() {
        return branchCity;
    }

    public StringProperty branchPhoneProperty() {
        return branchPhone;
    }

    public BooleanProperty branchActiveProperty() {
        return branchActive;
    }

}