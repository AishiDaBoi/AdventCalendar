package htl.steyr.adventcalendar;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdventCalendarIT extends Application {

    private final String[] riddles = {
            "Tür 1: Was ist der Wert von 2^3?",
            "Tür 2: Welcher HTTP-Statuscode steht für 'Nicht gefunden'?",
            "Tür 3: Wie viele Bits hat ein Byte?",
            "Tür 4: Welches Java-Schlüsselwort wird für Vererbung verwendet?",
            "Tür 5: Was ergibt 10 in Binär?",
            "Tür 6: Nenne einen Algorithmus zur Sortierung.",
            "Tür 7: Welches Unternehmen hat Java entwickelt?",
            "Tür 8: Wofür steht die Abkürzung HTML?",
            "Tür 9: Wie viele Nullen hat 2^10 in Dezimal?",
            "Tür 10: Wofür steht die Abkürzung CSS?",
            "Tür 11: Welche Methode wird verwendet, um einen String in Java zu vergleichen?",
            "Tür 12: Wofür steht die Abkürzung SQL?",
            "Tür 13: Was ist der Port für HTTPS?",
            "Tür 14: Was ist der Wert von PI auf zwei Nachkommastellen?",
            "Tür 15: Wie heißt die wichtigste Basisklasse in Java?",
            "Tür 16: Welches Schlüsselwort in Java erzeugt ein neues Objekt?",
            "Tür 17: Was ergibt 16 in Hexadezimal?",
            "Tür 18: Wie heißt das Zeichen '!' in der Programmierung?",
            "Tür 19: Wofür steht 'IDE'?",
            "Tür 20: Welche Schleife wiederholt immer mindestens einmal?",
            "Tür 21: Welches Jahr wurde Java veröffentlicht?",
            "Tür 22: Was ist die höchste Zahl, die in einem Byte gespeichert werden kann?",
            "Tür 23: Welche Methode wird verwendet, um ein Array zu sortieren?",
            "Tür 24: Was ist die erste Programmiersprache der Welt?"
    };

    private final String[] riddleAnswers = {
            "8", "404", "8", "extends", "1010", "Merge Sort",
            "Sun Microsystems", "HyperText Markup Language", "1024",
            "Cascading Style Sheets", "equals", "Structured Query Language",
            "443", "3.14", "Object", "new", "10", "Not",
            "Integrated Development Environment", "do-while",
            "1995", "255", "Arrays.sort", "Fortran"
    };

    @Override
    public void start(Stage primaryStage) {
        // GridPane für die Buttons
        GridPane gridPane = createGridPane();

        // Buttons für die 24 Türchen hinzufügen
        for (int i = 1; i <= 24; i++) {
            Button button = createButton(i);
            int index = i - 1; // Index für die spezifische Frage
            button.setOnAction(e -> openContentWindow(index));
            gridPane.add(button, (i - 1) % 6, (i - 1) / 6); // 6 Spalten
        }

        // StackPane für zentrierte Platzierung der GridPane
        StackPane root = new StackPane(gridPane);
        root.setAlignment(Pos.CENTER);

        // Hauptszene
        Scene scene = new Scene(root, 600, 400); // Größeres Fenster
        primaryStage.setTitle("Adventskalender IT-Rätsel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Horizontaler Abstand zwischen Buttons
        gridPane.setVgap(20); // Vertikaler Abstand zwischen Buttons
        gridPane.setAlignment(Pos.CENTER); // Zentriert die Buttons in der GridPane
        return gridPane;
    }

    private Button createButton(int day) {
        Button button = new Button("Tür " + day);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px;");
        button.setFont(javafx.scene.text.Font.font("Arial", 16));
        button.setPrefWidth(120);
        button.setPrefHeight(60);

        // Hover-Effekt
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px;"));

        return button;
    }

    private void openContentWindow(int index) {
        Stage contentStage = new Stage();
        VBox contentBox = new VBox(15);
        contentBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Frage und Antwort aus den Arrays holen
        String riddle = riddles[index];
        String correctAnswer = riddleAnswers[index];

        // Anzeige der Frage
        Text riddleText = new Text(riddle);
        riddleText.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial';");

        TextField answerField = new TextField();
        answerField.setPromptText("Antwort hier eingeben");
        answerField.setStyle("-fx-padding: 10px; -fx-font-size: 16px;");

        Button submitButton = new Button("Prüfen");
        submitButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px;");
        submitButton.setOnAction(e -> {
            if (answerField.getText().equalsIgnoreCase(correctAnswer)) {
                showAlert(AlertType.INFORMATION, "Richtig!", "Das war korrekt!");
                contentStage.close();
            } else {
                showAlert(AlertType.ERROR, "Falsch!", "Die richtige Antwort war: " + correctAnswer);
                contentStage.close();
            }
        });

        contentBox.getChildren().addAll(riddleText, answerField, submitButton);

        Scene contentScene = new Scene(contentBox);
        contentStage.setTitle("Tür " + (index + 1));
        contentStage.setScene(contentScene);
        contentStage.show();
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
