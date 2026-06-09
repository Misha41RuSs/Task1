package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Базовый абстрактный класс для всех графических фигур.
 * Соответствует требованиям задания: содержит абстрактный метод draw, 
 * метод descriptor для вывода названия, а также приватные поля (color и другие).
 */
public abstract class Shape {
    private Color color;
    private double lineWidth;
    private String name;

    private double startX;
    private double startY;

    /**
     * Конструктор для создания фигуры с заданными параметрами.
     *
     * @param color     Цвет фигуры.
     * @param lineWidth Толщина линии фигуры.
     * @param startX    Начальная координата X.
     * @param startY    Начальная координата Y.
     * @param name      Название фигуры.
     */
    public Shape(Color color, double lineWidth, double startX, double startY, String name) {
        this.color = color;
        this.lineWidth = lineWidth;
        this.startX = startX;
        this.startY = startY;
        this.name = name;
    }

    /**
     * Конструктор для создания фигуры только по имени (часто используется для инициализации UI).
     *
     * @param name Название фигуры.
     */
    public Shape(String name) {
        this.name = name;
    }

    /**
     * Вычисляет площадь фигуры.
     *
     * @return Площадь фигуры.
     */
    public abstract double area();

    /**
     * Отрисовывает фигуру на заданном графическом контексте.
     *
     * @param gc Графический контекст (холст) для рисования.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * Выводит название (описание) фигуры в консоль.
     * Метод реализован согласно требованиям задания (discriptor()).
     */
    public void descriptor() {
        System.out.println("Фигура: " + getName());
    }

    // Геттеры и сеттеры для приватных полей
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getStartX() { return startX; }
    public void setStartX(double startX) { this.startX = startX; }
    
    public double getStartY() { return startY; }
    public void setStartY(double startY) { this.startY = startY; }
    
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    
    public double getLineWidth() { return lineWidth; }
    public void setLineWidth(double lineWidth) { this.lineWidth = lineWidth; }
}
