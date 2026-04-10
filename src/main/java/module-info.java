module com.mycompany.restaurantee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.restaurantee to javafx.fxml;
    exports com.mycompany.restaurantee;
}
