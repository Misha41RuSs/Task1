package org.swe.figures.Model;

import javafx.scene.paint.Color;

public class ShapeFactory {

    public Shape[] createAllShapes() {
        return new Shape[]{
                new Circle("Окружность/круг"),
                new Rectangle("Прямоугольник"),
                new Triangle("Треугольник")
        };
    }

    // Создание готовой фигуры по координатам
    public Shape createShape(String shapeType, Color color, double lineWidth, boolean fill, double[] array) {
        return switch (shapeType) {
            case "Окружность/круг" -> {
                double cx = array[0];
                double cy = array[1];
                double ex = array[2];
                double ey = array[3];
                double radius = Math.hypot(ex - cx, ey - cy);
                yield new Circle(color, lineWidth, cx, cy, radius, fill);
            }
            case "Прямоугольник" -> {
                double x1 = array[0];
                double y1 = array[1];
                double x2 = array[2];
                double y2 = array[3];
                double minX = Math.min(x1, x2);
                double minY = Math.min(y1, y2);
                double width = Math.abs(x2 - x1);
                double height = Math.abs(y2 - y1);
                yield new Rectangle(color, lineWidth, minX, minY, width, height, fill);
            }
            case "Треугольник" -> {
                double[] xPoints = {array[0], array[2], array[4]};
                double[] yPoints = {array[1], array[3], array[5]};
                yield new Triangle(color, lineWidth, xPoints, yPoints, fill);
            }
            default -> null;
        };
    }
}
