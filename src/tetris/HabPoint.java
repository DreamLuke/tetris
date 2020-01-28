/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;

/**
 *
 * @author Luke
 */
public class HabPoint {
    int line;
    int column;
    Color habPointColor;
    boolean isHabPoint;
    boolean goDown;
    boolean goLeft;
    boolean goUp;
    boolean goRight;

    public HabPoint(int line, int column, Color habPointColor) {
        this.line = line;
        this.column = column;
        this.habPointColor = habPointColor;
    }


}
