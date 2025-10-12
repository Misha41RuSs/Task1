package org.swe.figures;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;
import org.swe.figures.Model.Rectangle;
import org.swe.figures.Model.Triangle;
import org.swe.figures.Model.Circle;


import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final List<Shape> shapes = new ArrayList<>();

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

        modeComboBox.getItems().addAll("Прямоугольник", "Треугольник", "Окружность/круг", "Свободное рисование");
        modeComboBox.getSelectionModel().select("Свободное рисование");

        modeComboBox.setOnAction(e -> {
            currentMode = modeComboBox.getValue();
            points.clear();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleMouseClicked);
    }

    private void handleMousePressed(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) {
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
            gc.setLineWidth(lineWidthSlider.getValue());
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        if (currentMode.equals("Свободное рисование")) {
            if (fillCheckBox.isSelected()) {
                gc.fill();
            }
            gc.closePath();
        }
    }

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

            Shape rect = new Rectangle(colorPicker.getValue(), lineWidthSlider.getValue(), minX, minY, width, height, fillCheckBox.isSelected());
            shapes.add(rect);
            rect.draw(gc);

            points.clear();
        }

        if (currentMode.equals("Треугольник") && points.size() == 6) {
            double[] xPoints = { points.get(0), points.get(2), points.get(4) };
            double[] yPoints = { points.get(1), points.get(3), points.get(5) };

            Shape triangle = new Triangle(colorPicker.getValue(), lineWidthSlider.getValue(), xPoints, yPoints, fillCheckBox.isSelected());
            shapes.add(triangle);
            triangle.draw(gc);

            points.clear();
        }

        if (currentMode.equals("Окружность/круг") && points.size() == 4) {
            double centerX = points.get(0);
            double centerY = points.get(1);
            double edgeX = points.get(2);
            double edgeY = points.get(3);

            double radius = Math.hypot(edgeX - centerX, edgeY - centerY);

            Shape circle = new Circle(colorPicker.getValue(), lineWidthSlider.getValue(), centerX, centerY, radius, fillCheckBox.isSelected());
            shapes.add(circle);
            circle.draw(gc);

            points.clear();
        }


    }

    @FXML
    void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
        points.clear();
    }

    @FXML
    void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            redrawCanvas();
        }
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }


}
