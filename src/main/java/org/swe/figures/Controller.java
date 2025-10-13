package org.swe.figures;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;
import org.swe.figures.Model.ShapeFactory;
import org.swe.figures.Tools.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private CheckBox fillCheckBox;
    @FXML private Slider lineWidthSlider;
    @FXML private ComboBox<String> modeComboBox;

    private GraphicsContext gc;
    private ToolFactory toolFactory;
    private ShapeFactory sf;
    private DrawTool currentTool;
    private final List<Shape> shapes = new ArrayList<>();
    private Shape[] shapesForInit;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        toolFactory = new ToolFactory(gc);
        sf = new ShapeFactory();
        shapesForInit = sf.createAllShapes();

        modeComboBox.getItems().add("Свободное рисование");
        for (Shape s : shapesForInit) {
            modeComboBox.getItems().add(s.getName());
        }
        modeComboBox.getSelectionModel().select("Свободное рисование");

        updateTool();

        modeComboBox.setOnAction(e -> updateTool());
        colorPicker.setOnAction(e -> updateTool());
        fillCheckBox.setOnAction(e -> updateTool());
        lineWidthSlider.valueProperty().addListener((obs, o, n) -> updateTool());

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> currentTool.onMousePressed(e));
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> currentTool.onMouseDragged(e));
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> currentTool.onMouseReleased(e));
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> currentTool.onMouseClicked(e));
    }

    @FXML
    private void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
    }


    private void updateTool() {
        String mode = modeComboBox.getValue();
        currentTool = toolFactory.createTool(
                mode,
                colorPicker.getValue(),
                lineWidthSlider.getValue(),
                fillCheckBox.isSelected(),
                shape -> {
                    shapes.add(shape);
                    shape.draw(gc);
                }
        );
    }

    public void undo(ActionEvent event) {
    }
}
