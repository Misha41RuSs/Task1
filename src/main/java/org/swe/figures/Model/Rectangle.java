package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Класс, представляющий фигуру "Прямоугольник".
 * Наследуется от базового класса {@link Shape}.
 */
public class Rectangle extends Shape {
    private double width;
    private double height;
    private boolean fill;

    /**
     * Создает новый прямоугольник.
     *
     * @param color     Цвет прямоугольника.
     * @param lineWidth Толщина линии.
     * @param startX    X-координата верхнего левого угла.
     * @param startY    Y-координата верхнего левого угла.
     * @param width     Ширина прямоугольника.
     * @param height    Высота прямоугольника.
     * @param fill      Флаг заливки (true - залитый, false - только контур).
     */
    public Rectangle(Color color, double lineWidth, double startX, double startY,
                     double width, double height, boolean fill) {
        super(color, lineWidth, startX, startY, "Прямоугольник");
        this.width = width;
        this.height = height;
        this.fill = fill;
    }

    /**
     * Конструктор для инициализации по имени.
     *
     * @param name Имя фигуры.
     */
    public Rectangle(String name) {
        super(name);
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.setFill(getColor());
        gc.setLineWidth(getLineWidth());
        if (fill)
            gc.fillRect(getStartX(), getStartY(), width, height);
        else
            gc.strokeRect(getStartX(), getStartY(), width, height);
    }

    // Геттеры и сеттеры
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    
    public boolean isFill() { return fill; }
    public void setFill(boolean fill) { this.fill = fill; }
}
