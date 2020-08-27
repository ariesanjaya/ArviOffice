package io.arvi.office;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import io.arvi.office.views.purchase.SupplierListView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends MvvmfxCdiApplication {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void startMvvmfx(Stage stage) throws Exception {
        
        Parent root = FluentViewLoader.fxmlView(SupplierListView.class).load().getView();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("styles/default.css");
        stage.setScene(scene);
        stage.show();
    }    
}
