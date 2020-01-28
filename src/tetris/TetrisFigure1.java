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
public abstract class TetrisFigure1 {

    Graphics graphics;
    Color paintColor;
    Unit[] unitsArray;
    int size;
    boolean[][] gameAr;
    //int isStoped = 0;
    boolean isStoped = false;
    int rotUnitNum;

    public void showTetrisFigure() {
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].show();
        }
    }

    public void hideTetrisFigure() {
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].hide();
        }
    }

    public boolean canMoveDown(int height) {
        for (int i = 0; i < unitsArray.length; i++) {
            if ((height - unitsArray[i].y) / size <= 1) { //Не достигнут ли низ игрового поля                
                return false;
            }
        }
        //Определяем размер массива для координат крайних квадратиков        
        int minColumn = unitsArray[0].getColumn();
        int maxColumn = unitsArray[0].getColumn();

        for (int i = 1; i < unitsArray.length; i++) {
            if (unitsArray[i].getColumn() < minColumn) {
                minColumn = unitsArray[i].getColumn();
            }
            if (unitsArray[i].getColumn() > maxColumn) {
                maxColumn = unitsArray[i].getColumn();
            }
        }
        int[][] maxCoordsY = new int[maxColumn + 1 - minColumn][];
        for (int i = 0; i < maxCoordsY.length; i++) {
            maxCoordsY[i] = new int[2];
        }

        for (int i = 0; i < maxCoordsY.length; i++) { //Заполняем массив значениям X
            maxCoordsY[i][0] = minColumn + i;
        }

        for (int i = 0; i < unitsArray.length; i++) { //Для каждого квадратика
            for (int j = 0; j < maxCoordsY.length; j++) { //Перебираем все значения X
                if (unitsArray[i].getColumn() == maxCoordsY[j][0]) { //Когда нашли соответствие
                    if (unitsArray[i].getLine() > maxCoordsY[j][1]) { //Проверяем значение Y
                        maxCoordsY[j][1] = unitsArray[i].getLine(); //И записываем большее
                    }
                }
            }
        }
        //
        /*for (int i = 0; i < maxCoordsY.length; i++) {
        System.out.println(maxCoordsY[i][0] + " " + maxCoordsY[i][1]);
        }*/
        //
        //Проверяем есть ли занятые квадратики за полеми с максимальным Y
        for (int i = 0; i < maxCoordsY.length; i++) {
            if (gameAr[maxCoordsY[i][1] + 1][maxCoordsY[i][0]] == true) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveLeft() {
        for (int i = 0; i < unitsArray.length; i++) {
            if (unitsArray[i].x == 0) {
                return false;
            }
            /*if (unitsArray[i].y < 0) {
            return false;
            }*/
        }

        //Определяем размер массива для координат крайних квадратиков
        int minLine = unitsArray[0].getLine();
        int maxLine = unitsArray[0].getLine();
        //System.out.println(minLine);
        //System.out.println(maxLine);

        for (int i = 1; i < unitsArray.length; i++) {
            if (unitsArray[i].getLine() < minLine) {
                minLine = unitsArray[i].getLine();
            }
            if (unitsArray[i].getLine() > maxLine) {
                maxLine = unitsArray[i].getLine();
            }
        }
        //System.out.println(minLine);
        //System.out.println(maxLine);
        //System.out.println();

        int[][] minCoordsX = new int[maxLine + 1 - minLine][];
        for (int i = 0; i < minCoordsX.length; i++) {
            minCoordsX[i] = new int[2];
        }
        for (int i = 0; i < minCoordsX.length; i++) { //Заполняем массив значениями Y и X (X - фиктивное)
            minCoordsX[i][0] = minLine + i; //Y
            minCoordsX[i][1] = gameAr[0].length; //X
        }

        for (int i = 0; i < unitsArray.length; i++) { //Для каждого квадратика
            for (int j = 0; j < minCoordsX.length; j++) { //Перебираем все значения Y
                if (unitsArray[i].getLine() == minCoordsX[j][0]) { //Когда нашли соответствие
                    //System.out.println("== "+ unitsArray[i].getLine() +" "+ unitsArray[i].getColumn());
                    //System.out.println("j= "+ minCoordsX[j][0] +" "+ minCoordsX[j][1]);
                    if (unitsArray[i].getColumn() < minCoordsX[j][1]) { //Проверяем значение X
                        minCoordsX[j][1] = unitsArray[i].getColumn(); //И записываем меньшее
                    }
                }
            }
        }

        //      
        /*for (int i = 0; i < minCoordsX.length; i++) {
        System.out.println(minCoordsX[i][0] + " " + minCoordsX[i][1]);
        }*/
        //

        //Проверяем есть ли занятые квадратики за полеми с минимальным X
        for (int i = 0; i < minCoordsX.length; i++) {
            if (minCoordsX[i][0] >= 0 && gameAr[minCoordsX[i][0]][minCoordsX[i][1] - 1] == true) {
                return false;
            }
        }

        return true;
    }

    public boolean canMoveRight(int width) {
        for (int i = 0; i < unitsArray.length; i++) {
            if ((width - unitsArray[i].x) / size <= 1) {
                return false;
            }
            /*if (unitsArray[i].y < 0) {
                return false;
            }*/
        }

        //Определяем размер массива для координат крайних квадратиков
        int minLine = unitsArray[0].getLine();
        int maxLine = unitsArray[0].getLine();
        //System.out.println(minLine);
        //System.out.println(maxLine);

        for (int i = 1; i < unitsArray.length; i++) {
            if (unitsArray[i].getLine() < minLine) {
                minLine = unitsArray[i].getLine();
            }
            if (unitsArray[i].getLine() > maxLine) {
                maxLine = unitsArray[i].getLine();
            }
        }
        //System.out.println(minLine);
        //System.out.println(maxLine);
        //System.out.println();

        int[][] maxCoordsX = new int[maxLine + 1 - minLine][];
        for (int i = 0; i < maxCoordsX.length; i++) {
            maxCoordsX[i] = new int[2];
        }
        for (int i = 0; i < maxCoordsX.length; i++) { //Заполняем массив значениями Y
            maxCoordsX[i][0] = minLine + i; //Y
        }

        for (int i = 0; i < unitsArray.length; i++) { //Для каждого квадратика
            for (int j = 0; j < maxCoordsX.length; j++) { //Перебираем все значения Y
                if (unitsArray[i].getLine() == maxCoordsX[j][0]) { //Когда нашли соответствие
                    if (unitsArray[i].getColumn() > maxCoordsX[j][1]) { //Проверяем значение X
                        maxCoordsX[j][1] = unitsArray[i].getColumn(); //И записываем большее
                    }
                }
            }
        }

        //
        /*for (int i = 0; i < maxCoordsX.length; i++) {
        System.out.println(maxCoordsX[i][0] + " " + maxCoordsX[i][1]);
        }*/
        //

        //Проверяем есть ли занятые квадратики за полеми с максимальным X
        for (int i = 0; i < maxCoordsX.length; i++) {
            if (maxCoordsX[i][0] >= 0 && gameAr[maxCoordsX[i][0]][maxCoordsX[i][1] + 1] == true) {
                System.out.println("ПРАВО");
                return false;
            }
        }

        return true;
    }

    public void moveDownTetrisFigure(int height) {
        //System.out.println(this.isMoving(height));
        //System.out.println("d " +isStoped);

        if (!canMoveDown(height)) {
            isStoped = true;
            return;
        }
        if (canMoveDown(height)) {
            isStoped = false;
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].hide();
            //System.out.println("hi " + unitsArray[i].y / size);
        }
        for (int i = 0; i < unitsArray.length; i++) {
            //
            unitsArray[i].moveDown(height);
            //System.out.println("+= " + unitsArray[i].y / size);
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].show();
            //System.out.println("sh " + unitsArray[i].y / size);
        }
    }

    public void moveLeftTetrisFigure(int height) {
        if (isStoped) {
            return;
        }
        if (!canMoveLeft()) {
            return;
        }

        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].hide();
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].moveLeft();
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].show();
        }
    }

    public void moveRightTetrisFigure(int height, int width) {
        //System.out.println("r " +isStoped);

        if (isStoped) {
            return;
        }
        if (!canMoveRight(width)) {
            return;
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].hide();
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].moveRight(width);
        }
        for (int i = 0; i < unitsArray.length; i++) {
            unitsArray[i].show();
        }
    }

    /*public boolean isMoving(int height) {
    if (this.canMoveDown(height, gameAr)) {
    return true;
    }
    return false;
    }*/
    public abstract void rotateTetrisFigure(int width, int height);
}
