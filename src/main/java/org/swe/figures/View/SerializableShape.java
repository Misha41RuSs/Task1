package org.swe.figures.View;

import javafx.scene.paint.Color;
import org.swe.figures.Model.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс, используемый для сериализации графических фигур.
 * Паттерн Memento: сохраняет состояние (свойства) фигуры для сохранения в файл.
 */
public class SerializableShape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private final String shapeType;
    private final String colorString;
    private final double lineWidth;
    private final boolean fill;
    private final double[] parameters;
    
    /**
     * Конструктор для создания сериализуемой формы фигуры.
     *
     * @param shapeType   Тип фигуры.
     * @param color       Цвет фигуры.
     * @param lineWidth   Толщина линии.
     * @param fill        Флаг заливки.
     * @param parameters  Дополнительные параметры (координаты, радиус, ширина и т.д.).
     */
    public SerializableShape(String shapeType, Color color, double lineWidth, boolean fill, double[] parameters) {
        this.shapeType = shapeType;
        this.colorString = color.toString();
        this.lineWidth = lineWidth;
        this.fill = fill;
        this.parameters = parameters;
    }
    
    /**
     * Фабричный метод для создания сериализуемой фигуры из объекта Shape.
     *
     * @param shape Объект Shape, состояние которого нужно сохранить.
     * @return Сериализуемый объект, представляющий данную фигуру.
     */
    public static SerializableShape fromShape(Shape shape) {
        double[] params;
        Color color = shape.getColor();
        double lineWidth = shape.getLineWidth();
        boolean fill = false;
        
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            params = new double[]{circle.getStartX(), circle.getStartY(), circle.getRadius(), 0};
            fill = circle.isFill();
        } else if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            params = new double[]{rect.getStartX(), rect.getStartY(), rect.getWidth(), rect.getHeight()};
            fill = rect.isFill();
        } else if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            params = new double[6];
            System.arraycopy(triangle.getXPoints(), 0, params, 0, 3);
            System.arraycopy(triangle.getYPoints(), 0, params, 3, 3);
            fill = triangle.isFill();
        } else if (shape instanceof NPolygon) {
            NPolygon polygon = (NPolygon) shape;
            params = new double[]{polygon.getStartX(), polygon.getStartY(), polygon.getEndX(), polygon.getEndY(), polygon.getSides()};
            fill = polygon.isFill();
        } else {
            throw new IllegalArgumentException("Unknown shape type: " + shape.getClass());
        }

        
        return new SerializableShape(shape.getName(), color, lineWidth, fill, params);
    }
    
    /**
     * Восстанавливает объект Shape из сериализованных данных с использованием фабрики.
     *
     * @param shapeFactory Фабрика фигур.
     * @return Восстановленный объект Shape.
     */
    public Shape toShape(IShapeFactory shapeFactory) {
        Color color;
        if (colorString.startsWith("0x")) {
            color = Color.web(colorString.replace("0x", "#"));
        } else {
            color = Color.web(colorString);
        }

        double[] coords;
        switch (shapeType) {
            case "Окружность/круг" -> {
                double cx = parameters[0];
                double cy = parameters[1];
                double radius = parameters[2];
                coords = new double[]{cx, cy, cx + radius, cy};
            }
            case "Треугольник" -> {
                coords = new double[]{
                    parameters[0], parameters[3],
                    parameters[1], parameters[4],
                    parameters[2], parameters[5]
                };
            }
            case "Многоугольник" -> {
                // coords: [centerX, centerY, endX, endY, sides]
                coords = new double[]{
                    parameters[0], parameters[1],
                    parameters[2], parameters[3],
                    parameters[4]
                };
            }
            default -> {
                double x = parameters[0];
                double y = parameters[1];
                double width = parameters[2];
                double height = parameters[3];
                coords = new double[]{x, y, x + width, y + height};
            }
        }
        
        return shapeFactory.createShape(shapeType, color, lineWidth, fill, coords);
    }
    
    public String getShapeType() {
        return shapeType;
    }
}
