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
public class TFLFigure extends TetrisFigure {

    //boolean[][] gameAr;
    int position;

    public TFLFigure(Graphics graphics, Color paintColor, int size, boolean[][] gameAr) {
        this.graphics = graphics;
        this.paintColor = paintColor;
        this.size = size;
        this.gameAr = gameAr;
        this.rotUnitNum = 1; //Номер элемента массива, вокруг которого вращается фигура

        unitsArray = new Unit[4];
        position = (int) (Math.random() * 4 + 1); // целое число из [1;4]
        //position = 1;

        System.out.println("pozition " + position);

        if (position == 1) { //1
            unitsArray[0] = new Unit(this.graphics, 6 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 5 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
        }
        if (position == 2) { //2
            unitsArray[0] = new Unit(this.graphics, 5 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 5 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 5 * size, -4 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 4 * size, -4 * size, size, this.paintColor, gameAr);
        }
        if (position == 3) { //3
            unitsArray[0] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 5 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 6 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 6 * size, -3 * size, size, this.paintColor, gameAr);
        }
        if (position == 4) { //4
            unitsArray[0] = new Unit(this.graphics, 4 * size, -4 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 5 * size, -2 * size, size, this.paintColor, gameAr);
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

        if (position == 1) {
            if (rotLine == 0) {
                rotLine = 1; //Переношу центр поворота в новое положение
                //Заполняю два вектора в которых будут храниться амплитуды поворота для 1 позиции
                vAmplitudeLines.add(rotLine); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine + 1); //2
                vAmplitudeColumns.add(rotColumn - 1);
                vAmplitudeLines.add(rotLine + 1); //3
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine + 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
            } else {
                for (int i = 0; i < 3; i++) {
                    if (gameAr[unitsArray[i].getLine() - 1][unitsArray[i].getColumn()] == true) {
                        isTopFull = true;
                    }
                }
                if (isTopFull) {
                    rotLine = rotLine + 1; //Переношу центр поворота в новое положение
                }
                //Заполняю два вектора в которых будут храниться амплитуды поворота для 1 позиции
                if (isTopFull) {
                    vAmplitudeLines.add(rotLine); //0
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //3
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn + 1);
                }
                if (!isTopFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
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

            unitsArray[0].x = rotColumn * size;
            unitsArray[0].y = rotLine * size + size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size;
            unitsArray[2].y = rotLine * size - size;
            unitsArray[3].x = rotColumn * size - size;
            unitsArray[3].y = rotLine * size - size;

            //задаю новое значение позиции
            position = 2;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
        } //конец if(position 1)

        if (position == 2) {
            if (rotColumn == gameAr[0].length - 1) {
                rotColumn = gameAr[0].length - 1 - 1; //Переношу центр поворота в новое положение
                //Заполняю два вектора в которых будут храниться амплитуды поворота для 2 позиции
                vAmplitudeLines.add(rotLine - 1); //0
                vAmplitudeColumns.add(rotColumn - 1);
                vAmplitudeLines.add(rotLine); //`1
                vAmplitudeColumns.add(rotColumn - 1);
                vAmplitudeLines.add(rotLine); //2
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine + 1); //3
                vAmplitudeColumns.add(rotColumn - 1);
                vAmplitudeLines.add(rotLine + 1); //4
                vAmplitudeColumns.add(rotColumn);
            } else {
                for (int i = 0; i < 3; i++) {
                    if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() + 1] == true) {
                        isRightFull = true;
                    }
                }
                if (isRightFull) {
                    rotColumn = rotColumn - 1; //Переношу центр поворота в новое положение
                }
                //Заполняю два вектора в которых будут храниться амплитуды поворота для 2 позиции
                if (isRightFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //`1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn);
                }
                if (!isRightFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //3
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

            unitsArray[0].x = rotColumn * size - size;
            unitsArray[0].y = rotLine * size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size + size;
            unitsArray[2].y = rotLine * size;
            unitsArray[3].x = rotColumn * size + size;
            unitsArray[3].y = rotLine * size - size;

            //задаю новое значение позиции
            position = 3;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
        } //конец if(position 2)

        if (position == 3) {
            if (rotLine == gameAr.length - 1) {
                rotLine = gameAr.length - 1 - 1; //Переношу центр поворота в новое положение
                //Заполняю два вектора в которых будут храниться амплитуды поворота для 3 позиции
                vAmplitudeLines.add(rotLine - 1); //0
                vAmplitudeColumns.add(rotColumn - 1);
                vAmplitudeLines.add(rotLine - 1); //1
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //2
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine); //3
                vAmplitudeColumns.add(rotColumn - 1);
                vAmplitudeLines.add(rotLine); //4
                vAmplitudeColumns.add(rotColumn);
            } else {
                for (int i = 0; i < 3; i++) {
                    if (gameAr[unitsArray[i].getLine() + 1][unitsArray[i].getColumn()] == true) {
                        isDownFull = true;
                    }
                }
                if (isDownFull) {
                    rotLine = rotLine - 1; //Переношу центр поворота в новое положение
                }

                //Заполняю два вектора в которых будут храниться амплитуды поворота для 3 позиции
                if (isDownFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine - 1); //2
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //3
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //4
                    vAmplitudeColumns.add(rotColumn);
                }
                if (!isDownFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine + 1); //3
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

            unitsArray[0].x = rotColumn * size;
            unitsArray[0].y = rotLine * size - size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size;
            unitsArray[2].y = rotLine * size + size;
            unitsArray[3].x = rotColumn * size + size;
            unitsArray[3].y = rotLine * size + size;

            //задаю новое значение позиции
            position = 4;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
        } //конец if(position 3)

        if (position == 4) {
            if (rotColumn == 0) {
                rotColumn = 1; //Переношу центр поворота в новое положение
                //Заполняю два вектора в которых будут храниться амплитуды поворота для 4 позиции
                vAmplitudeLines.add(rotLine - 1); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine); //2
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine); //3
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine + 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
            } else {
                for (int i = 0; i < 3; i++) {
                    if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() - 1] == true) {
                        isLeftFull = true;
                    }
                }
                if (isLeftFull) {
                    rotColumn = rotColumn + 1; //Переношу центр поворота в новое положение
                }

                //Заполняю два вектора в которых будут храниться амплитуды поворота для 4 позиции
                if (isLeftFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine - 1); //1
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn);
                    vAmplitudeLines.add(rotLine); //3
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //4
                    vAmplitudeColumns.add(rotColumn + 1);
                }
                if (!isLeftFull) {
                    vAmplitudeLines.add(rotLine - 1); //0
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine); //1
                    vAmplitudeColumns.add(rotColumn - 1);
                    vAmplitudeLines.add(rotLine); //2
                    vAmplitudeColumns.add(rotColumn + 1);
                    vAmplitudeLines.add(rotLine + 1); //3
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

            unitsArray[0].x = rotColumn * size + size;
            unitsArray[0].y = rotLine * size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size - size;
            unitsArray[2].y = rotLine * size;
            unitsArray[3].x = rotColumn * size - size;
            unitsArray[3].y = rotLine * size + size;

            //задаю новое значение позиции
            position = 1;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
        } //конец if(position 4)
    }
}
