module org.example.poolista5javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.poolista5javafx to javafx.fxml;
    exports org.example.poolista5javafx;
}