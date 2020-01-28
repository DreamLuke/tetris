/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

/**
 *
 * @author Luke
 */
public class TFZFigure extends TetrisFigure {

    //boolean[][] gameAr;
    int position;

    public TFZFigure(Graphics graphics, Color paintColor, int size, boolean[][] gameAr) {
        this.graphics = graphics;
        this.paintColor = paintColor;
        this.size = size;
        this.gameAr = gameAr;
        this.rotUnitNum = 1; //Номер элемента массива, вокруг которого вращается фигура

        unitsArray = new Unit[4];
        position = (int) (Math.random() * 2 + 1); // целое число из [1;4]
        //position = 1;

        System.out.println("pozition " + position);

        if (position == 1) { //1
            unitsArray[0] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 5 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 5 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 6 * size, -2 * size, size, this.paintColor, gameAr);
        }
        if (position == 2) { //2
            unitsArray[0] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 5 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 5 * size, -4 * size, size, this.paintColor, gameAr);
        }
    }

    @Override
    public void rotateTetrisFigure(int width, int height) {
        if (isStoped) {
            return;
        }
        for (int i = 0; i < unitsArray.length; i++) {
            if (unitsArray[i].getLine() < 0) {
                return;
            }
        }

        //В этом массиве будут храниться координаты амплитуды поворота
        Vector vAmplitudeLines = new Vector();
        Vector vAmplitudeColumns = new Vector();
        int[][] rotationAmplitude;
        boolean isLeftFull = false;
        boolean isRightFull = false;
        boolean isTopFull = false;
        boolean isDownFull = false;

        //Координаты центра поворота
        int rotLine = unitsArray[rotUnitNum].getLine();
        int rotColumn = unitsArray[rotUnitNum].getColumn();

        //Определяю позицию фигуры
        if (unitsArray[0].x != unitsArray[1].x) {
            position = 1;
        }
        if (unitsArray[0].x == unitsArray[1].x) {
            position = 2;
        }

        if (position == 1) {
            if (rotLine == 0) {
                if (gameAr[unitsArray[3].getLine() - 1][unitsArray[3].getColumn()] != true) {
                    rotLine = 1; //Переношу центр поворота в новое положение
                    //System.out.println("LINE 1");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 1 позиции
                    vAmplitudeLines.add(rotLine); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                }
                if (gameAr[unitsArray[3].getLine() - 1][unitsArray[3].getColumn()] == true) {
                    rotLine = 2; //Переношу центр поворота в новое положение
                    //System.out.println("LINE 2");
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //5
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //6
                    vAmplitudeColumns.add(rotColumn + 1);
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (gameAr[unitsArray[i].getLine() - 1][unitsArray[i].getColumn()] == true) {
                        isTopFull = true;
                    }
                }
                if (gameAr[unitsArray[3].getLine() - 1][unitsArray[3].getColumn()] == true) {
                    isTopFull = true;
                }
                if (gameAr[unitsArray[3].getLine() - 2][unitsArray[3].getColumn()] == true) {
                    isTopFull = true;
                }
                if (isTopFull && gameAr[unitsArray[3].getLine() - 1][unitsArray[3].getColumn()] != true) {
                    rotLine = rotLine + 1; //Переношу центр поворота в новое положение
                    //System.out.println("LINE 3");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 1 позиции
                    vAmplitudeLines.add(rotLine); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
                    vAmplitudeColumns.add(rotColumn + 1);

                }
                if (isTopFull && gameAr[unitsArray[3].getLine() - 1][unitsArray[3].getColumn()] == true) {
                    rotLine = rotLine + 2; //Переношу центр поворота в новое положение                    
                    //System.out.println("LINE 4");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 1 позиции
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //5
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //6
                    vAmplitudeColumns.add(rotColumn + 1);
                }
                if (!isTopFull) {
                    //System.out.println("LINE 5 " + position);

                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn - 1);
                }
            }
            //Проверяю нет ли полей, которые выходят за пределы и игровой зоны и есть ли занятые поля в амплитуде поворота
            for (int i = 0; i < vAmplitudeLines.size(); i++) {
                for (int j = 0; j < unitsArray.length; j++) {
                    if (Integer.parseInt("" + vAmplitudeLines.get(i)) < 0 || Integer.parseInt("" + vAmplitudeColumns.get(i)) < 0
                            || Integer.parseInt("" + vAmplitudeLines.get(i)) > gameAr.length - 1 || Integer.parseInt("" + vAmplitudeColumns.get(i)) > gameAr[0].length - 1) {
                        return;
                    }
                    if (gameAr[Integer.parseInt("" + vAmplitudeLines.get(i))][Integer.parseInt("" + vAmplitudeColumns.get(i))] == true
                            && unitsArray[j].getLine() != Integer.parseInt("" + vAmplitudeLines.get(i))
                            && unitsArray[j].getColumn() != Integer.parseInt("" + vAmplitudeColumns.get(i))) {
                        return;
                    }
                }
            }
            //Выполняю поворот фигуры
            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].hide();
            }

            //System.out.println("LINE 55 " + position);
            unitsArray[0].x = rotColumn * size;
            unitsArray[0].y = rotLine * size + size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size + size;
            unitsArray[2].y = rotLine * size;
            unitsArray[3].x = rotColumn * size + size;
            unitsArray[3].y = rotLine * size - size;

            //задаю новое значение позиции
            //position = 2;
            vAmplitudeLines.clear();
            vAmplitudeColumns.clear();
            //System.out.println("LINE 555 " + position);
            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
        } //конец if(position 1)

        if (position == 2) {
            if (rotColumn == 0) {
                if (gameAr[unitsArray[3].getLine()][unitsArray[3].getColumn() - 1] != true) {
                    rotColumn = 1; //Переношу центр поворота в новое положение
                    //System.out.println("COLUMN 1");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 2 позиции
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                }
                if (gameAr[unitsArray[3].getLine()][unitsArray[3].getColumn() - 1] == true) {
                    rotColumn = 2; //Переношу центр поворота в новое положение
                    //System.out.println("COLUMN 2");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 2 позиции
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //5
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //6
                    vAmplitudeColumns.add(rotColumn + 1);
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() - 1] == true) {
                        isLeftFull = true;
                    }
                }
                if (gameAr[unitsArray[3].getLine()][unitsArray[3].getColumn() - 1] == true) {
                    isLeftFull = true;
                }
                if (gameAr[unitsArray[3].getLine()][unitsArray[3].getColumn() - 2] == true) {
                    isLeftFull = true;
                }
                if (isLeftFull && gameAr[unitsArray[3].getLine()][unitsArray[3].getColumn() - 1] != true) {
                    rotColumn = rotColumn + 1; //Переношу центр поворота в новое положение
                    //System.out.println("COLUMN 3");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 2 позиции
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                }
                if (isLeftFull && gameAr[unitsArray[3].getLine()][unitsArray[3].getColumn() - 1] == true) {
                    rotColumn = rotColumn + 2; //Переношу центр поворота в новое положение
                    //System.out.println("COLUMN 4");
                    //Заполняю два вектора в которых будут храниться амплитуды поворота для 2 позиции
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //5
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //6
                    vAmplitudeColumns.add(rotColumn + 1);
                }

                if (!isLeftFull) {
                    //System.out.println("COLUMN 5 " + position);
                    vAmplitudeLines.add(rotLine); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn + 1);
                }
            }
            //Проверяю нет ли полей, которые выходят за пределы и игровой зоны и есть ли занятые поля в амплитуде поворота
            for (int i = 0; i < vAmplitudeLines.size(); i++) {
                for (int j = 0; j < unitsArray.length; j++) {
                    if (Integer.parseInt("" + vAmplitudeLines.get(i)) < 0 || Integer.parseInt("" + vAmplitudeColumns.get(i)) < 0
                            || Integer.parseInt("" + vAmplitudeLines.get(i)) > gameAr.length - 1 || Integer.parseInt("" + vAmplitudeColumns.get(i)) > gameAr[0].length - 1) {
                        return;
                    }
                    if (gameAr[Integer.parseInt("" + vAmplitudeLines.get(i))][Integer.parseInt("" + vAmplitudeColumns.get(i))] == true
                            && unitsArray[j].getLine() != Integer.parseInt("" + vAmplitudeLines.get(i))
                            && unitsArray[j].getColumn() != Integer.parseInt("" + vAmplitudeColumns.get(i))) {
                        return;
                    }
                }
            }
            //Выполняю поворот фигуры
            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].hide();
            }

            unitsArray[0].x = rotColumn * size - size;
            unitsArray[0].y = rotLine * size;

            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;

            unitsArray[2].x = rotColumn * size;
            unitsArray[2].y = rotLine * size + size;

            unitsArray[3].x = rotColumn * size + size;
            unitsArray[3].y = rotLine * size + size;

            //задаю новое значение позиции
            //position = 1;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
        } //конец if(position 2)

    }
}
