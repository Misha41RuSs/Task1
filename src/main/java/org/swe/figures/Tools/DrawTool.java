package org.swe.figures.Tools;

import javafx.scene.input.MouseEvent;

/**
 * Интерфейс инструмента рисования.
 * Определяет методы-обработчики событий мыши для рисования на холсте.
 * Паттерн State / Strategy: контроллер делегирует обработку событий текущему инструменту.
 */
public interface DrawTool {
    /** Обрабатывает нажатие кнопки мыши. */
    void onMousePressed(MouseEvent e);
    
    /** Обрабатывает перетаскивание мыши. */
    void onMouseDragged(MouseEvent e);
    
    /** Обрабатывает отпускание кнопки мыши. */
    void onMouseReleased(MouseEvent e);
    
    /** Обрабатывает клик мыши. */
    void onMouseClicked(MouseEvent e);
    
    /** Отрисовывает вспомогательные точки (для инструментов создания фигур по кликам). */
    void drawHelperPoints();
}
