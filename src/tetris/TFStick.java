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
public class TFStick extends TetrisFigure {

    //boolean[][] gameAr;
    public TFStick(Graphics graphics, Color paintColor, int size, boolean[][] gameAr) {
        this.graphics = graphics;
        this.paintColor = paintColor;
        this.size = size;
        this.gameAr = gameAr;
        this.rotUnitNum = 1; //Номер элемента массива, вокруг которого вращается фигура

        unitsArray = new Unit[4];

        if (((int) (Math.random() * 2)) == 0) {
            unitsArray[0] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 4 * size, -3 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 4 * size, -4 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 4 * size, -5 * size, size, this.paintColor, gameAr);
        } else {
            unitsArray[0] = new Unit(this.graphics, 4 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[1] = new Unit(this.graphics, 5 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[2] = new Unit(this.graphics, 6 * size, -2 * size, size, this.paintColor, gameAr);
            unitsArray[3] = new Unit(this.graphics, 7 * size, -2 * size, size, this.paintColor, gameAr);
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

        //System.out.println(rotLine);
        //System.out.println(rotColumn);

        //Определяю положение (вертикальное или горизонтальное)
        boolean isVert = true;
        if (unitsArray[0].getLine() == unitsArray[1].getLine()) {
            isVert = false;
        }
        //System.out.println(isVert);
        //Если положение фигуры ВЕРТИКАЛЬНОЕ
        if (isVert) {
            //Для случая, когда слева стенка или есть занятые поля           
            if (rotColumn == 0) {
                rotColumn = 1; //Переношу центр поворота в новое положение
                //Заполняю два вектора в которых будут храниться амплитуды поворота
                vAmplitudeLines.add(rotLine - 2); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 2); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 2); //2
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine - 1); //3
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 1); //5
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine); //6
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine); //7
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine); //8
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine + 1); //9
                vAmplitudeColumns.add(rotColumn);
            } else if (rotColumn != (width - size) / size && rotColumn != (width - 2 * size) / size) { //Фигура не находится в одном из двух крайних правых столбцов
                //System.out.println("GLUK");
                for (int i = 0; i < unitsArray.length; i++) {
                    if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() - 1] == true) {
                        isLeftFull = true;
                        //System.out.println("GLUK 1");
                    }
                    if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() + 1] == true) {
                        isRightFull = true;
                        //System.out.println("GLUK 2");
                    }
                }

                if (isRightFull && isLeftFull) { //Справа и слева занятые поля - нельзя повернуть фигуру
                    //System.out.println("GLUK 3");
                    return;
                }
                if (isRightFull && rotColumn <= 2) { //Справа есть занятые поля и некуда сместиться влево
                    //System.out.println("GLUK 4");
                    return;
                }
                if (isLeftFull && rotColumn == (width - 3 * size) / size) { //Слева есть занятые поля и некуда сместиться вправо
                    //System.out.println("GLUK 5");
                    return;
                }
                if (isLeftFull) {
                    rotColumn += 1; ////Переношу центр поворота в новое положение
                    //System.out.println("GLUK 6");
                }
                if (isRightFull) {
                    rotColumn = rotColumn - 2; ////Переношу центр поворота в новое положение
                    //System.out.println("GLUK 7");
                }
                //System.out.println("GLUK 8");
                //Заполняю два вектора в которых будут храниться амплитуды поворота
                vAmplitudeLines.add(rotLine - 2); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 2); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 2); //2
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine - 1); //3
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 1); //5
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine); //6
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine); //7
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine); //8
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine + 1); //9
                vAmplitudeColumns.add(rotColumn);
                //Пока всё
            } else { //Фигура находится в одном из двух правых столбцов
                //System.out.println("GLUK GLUK GLUK");
                if (rotColumn == (width - size) / size) { //Фигура находится в крайнем правом столбце
                    //System.out.println("GLUK GLUK GLUK 11");
                    for (int i = 0; i < unitsArray.length; i++) {
                        if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() - 1] == true) {
                            isLeftFull = true;
                        }
                    }
                    if (isLeftFull) { //Cлева занятые поля, смещаться вправо некуда - нельзя повернуть фигуру
                        return;
                    }
                    rotColumn = rotColumn - 2; ////Переношу центр поворота в новое положение
                }
                if (rotColumn == (width - 2 * size) / size) { //Фигура находится в предпоследнем столбце
                    //System.out.println("GLUK GLUK GLUK 111");
                    for (int i = 0; i < unitsArray.length; i++) {
                        if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() - 1] == true) {
                            isLeftFull = true;
                        }
                        if (gameAr[unitsArray[i].getLine()][unitsArray[i].getColumn() + 1] == true
                                && rotColumn != (width - size) / size) {
                            isRightFull = true;
                        }
                    }

                    if (isRightFull && isLeftFull) { //Справа и слева занятые поля - нельзя повернуть фигуру
                        return;
                    }
                    if (isLeftFull) { //Cлева занятые поля, смещаться вправо некуда - нельзя повернуть фигуру
                        return;
                    }
                    if (isRightFull) {
                        rotColumn = rotColumn - 2; ////Переношу центр поворота в новое положение
                    }
                    if (!isRightFull) {
                        rotColumn = rotColumn - 1; ////Переношу центр поворота в новое положение
                    }
                }

                //System.out.println("GLUK GLUK GLUK 22");
                //Заполняю два вектора в которых будут храниться амплитуды поворота
                vAmplitudeLines.add(rotLine - 2); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 2); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 2); //2
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine - 1); //3
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 1); //5
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine); //6
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine); //7
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine); //8
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine + 1); //9
                vAmplitudeColumns.add(rotColumn);
            }

            //Проверяю есть ли занятые поля в амплитуде поворота
            for (int i = 0; i < vAmplitudeLines.size(); i++) {
                //System.out.println("GLUK END 33 ");
                for (int j = 0; j < unitsArray.length; j++) {
                    if (gameAr[Integer.parseInt("" + vAmplitudeLines.get(i))][Integer.parseInt("" + vAmplitudeColumns.get(i))] == true
                            && unitsArray[j].getLine() != Integer.parseInt("" + vAmplitudeLines.get(i))
                            && unitsArray[j].getColumn() != Integer.parseInt("" + vAmplitudeColumns.get(i))) {
                        //System.out.println("GLUK END " + i);
                        //System.out.println("" + Integer.parseInt("" + vAmplitudeLines.get(i)));
                        //System.out.println("" + Integer.parseInt("" + vAmplitudeColumns.get(i)));
                        return;
                    }
                }
            }
            //Выполняю поворот фигуры
            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].hide();
            }
            //System.out.println("GLUK END 2");
            unitsArray[0].x = rotColumn * size - size;
            unitsArray[0].y = rotLine * size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size + size;
            unitsArray[2].y = rotLine * size;
            unitsArray[3].x = rotColumn * size + 2 * size;
            unitsArray[3].y = rotLine * size;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
            //System.out.println("GLUK END 3");
        }
        //Если положение фигуры ГОРИЗОНТАЛЬНОЕ
        if (!isVert) {
            //System.out.println("   "+(height - size) / size);
            //System.out.println("   "+(height - 3 * size) / size);

            if (rotLine != (height - size) / size) { //Фигура НЕ находится в нижней строке
                for (int i = 0; i < unitsArray.length; i++) {
                    //System.out.println("GOR GOR");
                    if (rotLine != 0) {
                        if (gameAr[unitsArray[i].getLine() - 1][unitsArray[i].getColumn()] == true) {
                            isTopFull = true;
                            //System.out.println("GOR 1");
                        }
                    }
                    if (gameAr[unitsArray[i].getLine() + 1][unitsArray[i].getColumn()] == true) {
                        isDownFull = true;
                        //System.out.println("GOR 2");
                    }
                }
                if (isTopFull && isDownFull) { //Сверху и снизу занятые поля - нельзя повернуть фигуру
                    //System.out.println("GOR 3");
                    return;
                }
                if (isTopFull && rotLine >= (height - 3 * size) / size) { //Сверху есть занятые поля и некуда сместиться вниз 17
                    //System.out.println("GOR 4");
                    return;
                }
                if (isTopFull && rotLine < (height - 3 * size) / size) { //Сверху есть занятые поля и ЕСТЬ куда сместиться вниз
                    rotLine = rotLine + 2;
                    //System.out.println("GOR 5");
                }
                if (isDownFull) { //Снизу есть занятые поля, но можно сместиться вверх
                    rotLine = rotLine - 1;
                    //System.out.println("GOR 6");
                }
                //Заполняю два вектора в которых будут храниться амплитуды поворота
                vAmplitudeLines.add(rotLine - 2); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 2); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 2); //2
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine - 1); //3
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 1); //5
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine + 1); //6
                vAmplitudeColumns.add(rotColumn);
            }
            if (rotLine == (height - size) / size) { //Фигура находится в нижней строке
                //System.out.println("GOR NIZ");
                //rotLine = (height - size) / size - 1; //Переношу центр поворота в новое положение
                rotLine = rotLine - 1; //Переношу центр поворота в новое положение
                //Заполняю два вектора в которых будут храниться амплитуды поворота
                vAmplitudeLines.add(rotLine - 2); //0
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 2); //1
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 2); //2
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine - 1); //3
                vAmplitudeColumns.add(rotColumn);
                vAmplitudeLines.add(rotLine - 1); //4
                vAmplitudeColumns.add(rotColumn + 1);
                vAmplitudeLines.add(rotLine - 1); //5
                vAmplitudeColumns.add(rotColumn + 2);
                vAmplitudeLines.add(rotLine + 1); //6
                vAmplitudeColumns.add(rotColumn);
            }
            //Проверяю есть ли занятые поля в амплитуде поворота
            for (int i = 0; i < vAmplitudeLines.size(); i++) {
                //System.out.println("GLUK END 33 ");
                for (int j = 0; j < unitsArray.length; j++) {
                    if (Integer.parseInt("" + vAmplitudeLines.get(i)) >= 0) {
                        if (gameAr[Integer.parseInt("" + vAmplitudeLines.get(i))][Integer.parseInt("" + vAmplitudeColumns.get(i))] == true
                                && unitsArray[j].getLine() != Integer.parseInt("" + vAmplitudeLines.get(i))
                                && unitsArray[j].getColumn() != Integer.parseInt("" + vAmplitudeColumns.get(i))) {
                            //System.out.println("GOR END " + i);
                            //System.out.println("" + Integer.parseInt("" + vAmplitudeLines.get(i)));
                            //System.out.println("" + Integer.parseInt("" + vAmplitudeColumns.get(i)));
                            return;
                        }
                    }
                }
            }
            //Выполняю поворот фигуры
            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].hide();
            }
            //System.out.println("GOR END 2");
            unitsArray[0].x = rotColumn * size;
            unitsArray[0].y = rotLine * size + size;
            unitsArray[1].x = rotColumn * size;
            unitsArray[1].y = rotLine * size;
            unitsArray[2].x = rotColumn * size;
            unitsArray[2].y = rotLine * size - size;
            unitsArray[3].x = rotColumn * size;
            unitsArray[3].y = rotLine * size - 2 * size;

            for (int i = 0; i < unitsArray.length; i++) {
                unitsArray[i].show();
            }
            //System.out.println("GOR END 3");
        }




    }
}
