package org.swe.figures;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Rectangle;
import org.swe.figures.Model.Shape;
import org.swe.figures.Model.Triangle;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    public Canvas canvas;

    @FXML
    private Button fiveangle;

    @FXML
    private Button rectangle;

    @FXML
    private Button circle;

    @FXML
    private Button clear;

    @FXML
    private Button triangle;

    private Shape shape;
    private GraphicsContext graphicsContext;

    private String currentMode = "";
    private final List<Double> points = new ArrayList<>();

    @FXML
    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if ("RECT".equals(currentMode)) {
                handleRectangleClick(e.getX(), e.getY());
            } else if ("TRIANGLE".equals(currentMode)) {
                handleTriangleClick(e.getX(), e.getY());
            }
        });
    }

    private void handleRectangleClick(double x, double y) {
        points.add(x);
        points.add(y);

        if (points.size() == 4) {
            double x1 = points.get(0);
            double y1 = points.get(1);
            double x2 = points.get(2);
            double y2 = points.get(3);

            double width = Math.abs(x2 - x1);
            double height = Math.abs(y2 - y1);

            shape = new Rectangle(Color.BLUE, width, height);
            shape.draw(graphicsContext, Math.min(x1, x2), Math.min(y1, y2));

            System.out.println(shape);

            points.clear();
            currentMode = "";
        }
    }

    private void handleTriangleClick(double x, double y) {
        points.add(x);
        points.add(y);

        if (points.size() == 6) {
            double x1 = points.get(0);
            double y1 = points.get(1);
            double x2 = points.get(2);
            double y2 = points.get(3);
            double x3 = points.get(4);
            double y3 = points.get(5);

            shape = new Triangle(Color.RED, x1, y1, x2, y2, x3, y3);
            shape.draw(graphicsContext);

            System.out.println(shape);

            points.clear();
            currentMode = "";
        }
    }

    @FXML
    void drawFiveAngle(ActionEvent event) {

    }

    @FXML
    void drawRect(ActionEvent event) {
        currentMode = "RECT";
        points.clear();
    }

    @FXML
    void drawCircle(ActionEvent event) {

    }

    @FXML
    void drawTriangle(ActionEvent event) {
        currentMode = "TRIANGLE";
        points.clear();
    }

    @FXML
    void clear(ActionEvent event) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        points.clear();
        currentMode = "";
    }
}
