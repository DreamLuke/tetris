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
public class FlyingTetrisFigure extends TetrisFigure {

    //boolean[][] gameAr;
    public FlyingTetrisFigure(Graphics graphics, Color paintColor, int size, boolean[][] gameAr, int[][] tmpA) {
        this.graphics = graphics;
        this.paintColor = paintColor;
        this.size = size;
        this.gameAr = gameAr;

        unitsArray = new Unit[tmpA.length];
        for (int i = 0; i < tmpA.length; i++) {
            unitsArray[i] = new Unit(this.graphics, tmpA[i][0] * size, tmpA[i][1] * size, size, this.paintColor, gameAr);
        }

        //unitsArray[0] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
    }

    @Override
    public void rotateTetrisFigure(int width, int height) {
        System.out.println("Вращение квадрата не поддерживается");
    }
}
