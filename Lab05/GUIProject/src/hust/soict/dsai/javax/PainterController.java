package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton radioPen;

    @FXML
    private RadioButton radioEraser;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        double radius;
        Color color;

        if (radioEraser.isSelected()) {
            radius = 10;
            color = Color.WHITE;
        } else {
            radius = 4;
            color = Color.BLACK;
        }

        Circle newCircle = new Circle(event.getX(), event.getY(), radius, color);
        drawingAreaPane.getChildren().add(newCircle);
    }
}