/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Luke
 */
public class TFSquare extends TetrisFigure {

    //boolean[][] gameAr;
    public TFSquare(Graphics graphics, Color paintColor, int size, boolean[][] gameAr) {
        this.graphics = graphics;
        this.paintColor = paintColor;
        this.size = size;
        this.gameAr = gameAr;

        unitsArray = new Unit[4];
        /*unitsArray[0] = new Unit(this.graphics, 0*size, 0*size, size, this.paintColor, gameAr);
        unitsArray[1] = new Unit(this.graphics, 0*size, 1*size, size, this.paintColor, gameAr);
        unitsArray[2] = new Unit(this.graphics, 1*size, 0*size, size, this.paintColor, gameAr);
        unitsArray[3] = new Unit(this.graphics, 1*size, 1*size, size, this.paintColor, gameAr);*/

        unitsArray[0] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
        unitsArray[1] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
        unitsArray[2] = new Unit(this.graphics, 5 * size, -3 * size, size, this.paintColor, gameAr);
        unitsArray[3] = new Unit(this.graphics, 5 * size, -2 * size, size, this.paintColor, gameAr);
    }

    @Override
    public void rotateTetrisFigure(int width, int height) {
        System.out.println("Вращение квадрата не поддерживается");
    }
}
