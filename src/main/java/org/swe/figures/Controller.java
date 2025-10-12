package org.swe.figures;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox fillCheckBox;

    @FXML
    private Slider lineWidthSlider;

    @FXML
    private Button clear;

    @FXML
    private ComboBox<String> modeComboBox;

    private GraphicsContext gc;
    private final List<Double> points = new ArrayList<>();

    private String currentMode = "Свободное рисование";

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        colorPicker.setValue(Color.BLACK);

        // Настройка ComboBox
        modeComboBox.getItems().addAll("Прямоугольник", "Треугольник", "Свободное рисование");
        modeComboBox.getSelectionModel().select("Свободное рисование");

        modeComboBox.setOnAction(e -> {
            currentMode = modeComboBox.getValue();
            points.clear();
        });

        // Настройка событий мыши
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleMouseClicked);
    }

    /** Начало пути (для свободного рисования) **/
    private void handleMousePressed(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) {
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
            gc.setLineWidth(lineWidthSlider.getValue());
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
        }
    }

    /** Рисование свободной линии **/
    private void handleMouseDragged(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        }
    }

    /** Завершение пути **/
    private void handleMouseReleased(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) {
            if (fillCheckBox.isSelected()) {
                gc.fill();
            }
            gc.closePath();
        }
    }

    /** Рисование фигур по кликам **/
    private void handleMouseClicked(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) return; // свободное рисование уже обрабатывается

        points.add(e.getX());
        points.add(e.getY());

        gc.setStroke(colorPicker.getValue());
        gc.setFill(colorPicker.getValue());
        gc.setLineWidth(lineWidthSlider.getValue());

        if (currentMode.equals("Прямоугольник") && points.size() == 4) {
            double x1 = points.get(0);
            double y1 = points.get(1);
            double x2 = points.get(2);
            double y2 = points.get(3);

            double minX = Math.min(x1, x2);
            double minY = Math.min(y1, y2);
            double width = Math.abs(x2 - x1);
            double height = Math.abs(y2 - y1);

            if (fillCheckBox.isSelected()) {
                gc.fillRect(minX, minY, width, height);
            } else {
                gc.strokeRect(minX, minY, width, height);
            }

            points.clear();
        }

        if (currentMode.equals("Треугольник") && points.size() == 6) {
            double[] xPoints = { points.get(0), points.get(2), points.get(4) };
            double[] yPoints = { points.get(1), points.get(3), points.get(5) };

            if (fillCheckBox.isSelected()) {
                gc.fillPolygon(xPoints, yPoints, 3);
            } else {
                gc.strokePolygon(xPoints, yPoints, 3);
            }

            points.clear();
        }
    }

    @FXML
    void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        points.clear();
    }
}
