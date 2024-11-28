module htl.steyr.adventcalendar {
    requires javafx.controls;
    requires javafx.fxml;


    opens htl.steyr.adventcalendar to javafx.fxml;
    exports htl.steyr.adventcalendar;
}