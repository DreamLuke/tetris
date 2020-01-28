/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Luke
 */
public class AmbientFigure1 {

    Color[][] coloredGameArray;
    Graphics graphics;
    int size;
    boolean[][] gArray;
    Vector<Integer> razrivi = new Vector();

    public AmbientFigure1(Graphics graphics, boolean[][] gArray, int size) {
        this.gArray = gArray;
        this.graphics = graphics;
        this.size = size;
        this.coloredGameArray = new Color[gArray.length][];
        for (int i = 0; i < gArray.length; i++) {
            this.coloredGameArray[i] = new Color[gArray[0].length];
        }
    }

    public void add(TetrisFigure tf) {
        for (int i = 0; i < tf.unitsArray.length; i++) {
            coloredGameArray[tf.unitsArray[i].getLine()][tf.unitsArray[i].getColumn()] = tf.unitsArray[i].getColor();
        }
    }

    public void deleteLine(int line) {
        razrivi.clear();
        for (int j = 0; j < coloredGameArray[0].length; j++) {
            coloredGameArray[line][j] = null;

            graphics.setColor(Color.WHITE);
            graphics.drawRect(j * size, line * size, size, size);
            graphics.fillRect(j * size + 1, line * size + 1, size - 1, size - 1);
            graphics.setColor(Color.BLACK);
        }
        System.out.println(line + " is deleted");
    }

    public void moveToDeletedLine(int line) {
        System.out.println("Go to line " + line);
        //Привожу в соответствие массив цветов
        for (int i = line; i >= 0; i--) {
            for (int j = 0; j < coloredGameArray[i].length; j++) {
                if (i != 0) {
                    coloredGameArray[i][j] = coloredGameArray[i - 1][j];
                }
                if (i == 0) {
                    coloredGameArray[i][j] = null;
                }

            }
        }
        //Перерисовываю игровую зону
        for (int j = 0; j < coloredGameArray[0].length; j++) {
            for (int i = 0; i < coloredGameArray.length; i++) {
                if (coloredGameArray[i][j] != null) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(j * size, i * size, size, size);
                    graphics.setColor(coloredGameArray[i][j]);
                    graphics.fillRect(j * size + 1, i * size + 1, size - 1, size - 1);
                    graphics.setColor(Color.BLACK);
                }
                if (coloredGameArray[i][j] == null) {
                    graphics.setColor(Color.WHITE);
                    graphics.drawRect(j * size, i * size, size, size);
                    graphics.fillRect(j * size + 1, i * size + 1, size - 1, size - 1);
                    graphics.setColor(Color.BLACK);
                }
            }
        }
        //Если под удалённой линией есть "пустоты", то надо сместить в них то, что было выше
        //System.out.println("111   "+ (coloredGameArray.length - 1)); //19
        /*for (int j = 0; j < coloredGameArray[0].length; j++) {
        int kolPust = 0;
        if (line != (coloredGameArray.length - 1)) {
        if (coloredGameArray[line + 1][j] == null) {
        razrivi.add(j);
        for (int i = line + 1; i < coloredGameArray.length; i++) {
        if (coloredGameArray[i][j] == null) {
        kolPust++;
        }
        if (coloredGameArray[i][j] != null) {
        break;
        }
        }
        }
        }
        //
        //System.out.println("KOL PUST   " + kolPust);

        if (kolPust > 0) {
        //Привожу в соответствие массив цветов
        for (int i = line + kolPust; i >= kolPust; i--) {
        coloredGameArray[i][j] = coloredGameArray[i - kolPust][j];
        }
        for (int i = 0; i < kolPust; i++) {
        coloredGameArray[i][j] = null;
        }
        //Перерисовываю игровую зону
        for (int i = 0; i < coloredGameArray.length; i++) {
        if (coloredGameArray[i][j] != null) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(j * size, i * size, size, size);
        graphics.setColor(coloredGameArray[i][j]);
        graphics.fillRect(j * size + 1, i * size + 1, size - 1, size - 1);
        graphics.setColor(Color.BLACK);
        }
        if (coloredGameArray[i][j] == null) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(j * size, i * size, size, size);
        graphics.fillRect(j * size + 1, i * size + 1, size - 1, size - 1);
        graphics.setColor(Color.BLACK);
        }
        }
        }
        }*/
    }

    public void showAmbientFigure() {
        //Color tmpColor;
        for (int i = 0; i < coloredGameArray.length; i++) {
            for (int j = 0; j < coloredGameArray[0].length; j++) {
                if (coloredGameArray[i][j] != null) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(j * size, i * size, size, size);
                    //
                    graphics.setColor(coloredGameArray[i][j]);
                    graphics.fillRect(j * size + 1, i * size + 1, size - 1, size - 1);
                    graphics.setColor(Color.BLACK);

                }
                /*if (coloredGameArray[i][j] == null) {
                graphics.setColor(Color.WHITE);
                graphics.drawRect(j * size, i * size, size, size);
                graphics.fillRect(j * size + 1, i * size + 1, size - 1, size - 1);
                graphics.setColor(Color.BLACK);
                }*/
            }
        }
    }

    public int getNumberOfUnits() {
        int n = 0;
        for (int i = 0; i < coloredGameArray.length; i++) {
            for (int j = 0; j < coloredGameArray[0].length; j++) {
                if (coloredGameArray[i][j] != null) {
                    n++;
                }
            }
        }

        return n;
    }

    //public Vector flyFigureSerch(int line) {
    public Vector flyFigureSerch(boolean[][] fga) {
        //System.out.println("  SERCH FIGURE " + line);
        //System.out.println("  " + flyGameArray.length);
        //System.out.println("    " + flyGameArray[0].length);


        Vector findingFigure = new Vector(); //Этот вектор будет возвращать функция
        Vector vHabPoints = new Vector();
        //System.out.println("  SERCH FIGURE 1");

        int iEnter = -1;
        int jEnter = -1;
        for (int j = 0; j < fga[0].length; j++) {
            for (int i = fga.length - 1; i >= 0; i--) {
                if (fga[i][j] == true) { //Вход в фигуру
                    iEnter = i;
                    jEnter = j;
                    i = -1;
                    j = fga[0].length;
                }
            }
        }
        //System.out.println("ВХОД    " + iEnter + "  " + jEnter);

        //Создаю точку, которая может быть узловой
        if (iEnter != -1 && jEnter != -1) {
            vHabPoints.add(new HabPoint(iEnter, jEnter, coloredGameArray[iEnter][jEnter]));
        }
        //while (true) {
        while (vHabPoints.size() > 0 && iEnter != -1 && jEnter != -1) {
            //vHabPoints.add(new HabPoint(iEnter, jEnter, coloredGameArray[iEnter][jEnter]));
            //while (vHabPoints.size() > 0) {
            //for (int kkk = 0; kkk < 6; kkk++) {
            //System.out.println(vHabPoints.size());

            //Проверяю направления вокруг точки
            if (((HabPoint) vHabPoints.get(0)).line == fga.length - 1) { //Вниз
                ((HabPoint) vHabPoints.get(0)).goDown = false;
            } else {
                //if (((HabPoint) vHabPoints.get(0)).line != flyGameArray.length - 1) {

                if (fga[((HabPoint) vHabPoints.get(0)).line + 1][((HabPoint) vHabPoints.get(0)).column] == true) {
                    ((HabPoint) vHabPoints.get(0)).goDown = true;
                } else {
                    ((HabPoint) vHabPoints.get(0)).goDown = false;
                }
            }
            if (((HabPoint) vHabPoints.get(0)).column == 0
                    || razrivi.contains(((HabPoint) vHabPoints.get(0)).column - 1)) { //Влево
                ((HabPoint) vHabPoints.get(0)).goLeft = false;
            } else {
                if (fga[((HabPoint) vHabPoints.get(0)).line][((HabPoint) vHabPoints.get(0)).column - 1] == true) {
                    ((HabPoint) vHabPoints.get(0)).goLeft = true;
                } else {
                    ((HabPoint) vHabPoints.get(0)).goLeft = false;
                }
            }
            if (((HabPoint) vHabPoints.get(0)).line == 0) { //Вверх
                ((HabPoint) vHabPoints.get(0)).goUp = false;
            } else {
                if (fga[((HabPoint) vHabPoints.get(0)).line - 1][((HabPoint) vHabPoints.get(0)).column] == true) {
                    ((HabPoint) vHabPoints.get(0)).goUp = true;
                } else {
                    ((HabPoint) vHabPoints.get(0)).goUp = false;
                }
            }
            if (((HabPoint) vHabPoints.get(0)).column == fga[0].length - 1
                    || razrivi.contains(((HabPoint) vHabPoints.get(0)).column + 1)) { //Вправо
                ((HabPoint) vHabPoints.get(0)).goRight = false;
            } else {
                if (fga[((HabPoint) vHabPoints.get(0)).line][((HabPoint) vHabPoints.get(0)).column + 1] == true) {
                    ((HabPoint) vHabPoints.get(0)).goRight = true;
                } else {
                    ((HabPoint) vHabPoints.get(0)).goRight = false;
                }
            }
            /*System.out.println("NIZ " + ((HabPoint) vHabPoints.get(0)).goDown);
            System.out.println("LEVO " + ((HabPoint) vHabPoints.get(0)).goLeft);
            System.out.println("VERH " + ((HabPoint) vHabPoints.get(0)).goUp);
            System.out.println("PRAVO " + ((HabPoint) vHabPoints.get(0)).goRight);*/


            //ЗАПОМИНАЮ точки лежащие рядом с текущей
            //Новая точка лежит ниже текущей
            if (((HabPoint) vHabPoints.get(0)).goDown == true) {
                for (int i = 0; i < findingFigure.size(); i++) { //Проверяю есть ли такая точка в фигуре
                    if (((HabPoint) findingFigure.get(i)).line == ((HabPoint) vHabPoints.get(0)).line + 1
                            && ((HabPoint) findingFigure.get(i)).column == ((HabPoint) vHabPoints.get(0)).column) {
                        ((HabPoint) vHabPoints.get(0)).goDown = false; //Если уже есть, то добавлять её не надо
                    }
                }
                if (((HabPoint) vHabPoints.get(0)).goDown == true) { //Если фигура ещё не содержит такую точку - добаляю её в вектор узловых точек
                    vHabPoints.add(new HabPoint(((HabPoint) vHabPoints.get(0)).line + 1, ((HabPoint) vHabPoints.get(0)).column, coloredGameArray[((HabPoint) vHabPoints.get(0)).line + 1][((HabPoint) vHabPoints.get(0)).column]));
                }
            }
            //Новая точка лежит левее текущей
            if (((HabPoint) vHabPoints.get(0)).goLeft == true) {
                for (int i = 0; i < findingFigure.size(); i++) { //Проверяю есть ли такая точка в фигуре
                    if (((HabPoint) findingFigure.get(i)).line == ((HabPoint) vHabPoints.get(0)).line
                            && ((HabPoint) findingFigure.get(i)).column == ((HabPoint) vHabPoints.get(0)).column - 1) {
                        ((HabPoint) vHabPoints.get(0)).goLeft = false; //Если уже есть, то добавлять её не надо
                    }
                }
                if (((HabPoint) vHabPoints.get(0)).goLeft == true) {
                    vHabPoints.add(new HabPoint(((HabPoint) vHabPoints.get(0)).line, ((HabPoint) vHabPoints.get(0)).column - 1, coloredGameArray[((HabPoint) vHabPoints.get(0)).line][((HabPoint) vHabPoints.get(0)).column - 1]));
                }
            }
            //Новая точка лежит выше текущей
            if (((HabPoint) vHabPoints.get(0)).goUp == true) { //Новая точка лежит выше текущей
                for (int i = 0; i < findingFigure.size(); i++) { //Проверяю есть ли такая точка в фигуре
                    if (((HabPoint) findingFigure.get(i)).line == ((HabPoint) vHabPoints.get(0)).line - 1
                            && ((HabPoint) findingFigure.get(i)).column == ((HabPoint) vHabPoints.get(0)).column) {
                        ((HabPoint) vHabPoints.get(0)).goUp = false; //Если уже есть, то добавлять её не надо
                    }
                }
                if (((HabPoint) vHabPoints.get(0)).goUp == true) {
                    vHabPoints.add(new HabPoint(((HabPoint) vHabPoints.get(0)).line - 1, ((HabPoint) vHabPoints.get(0)).column, coloredGameArray[((HabPoint) vHabPoints.get(0)).line - 1][((HabPoint) vHabPoints.get(0)).column]));
                }
            }
            //Новая точка лежит правее текущей
            if (((HabPoint) vHabPoints.get(0)).goRight == true) {
                for (int i = 0; i < findingFigure.size(); i++) { //Проверяю есть ли такая точка в фигуре
                    if (((HabPoint) findingFigure.get(i)).line == ((HabPoint) vHabPoints.get(0)).line
                            && ((HabPoint) findingFigure.get(i)).column == ((HabPoint) vHabPoints.get(0)).column + 1) {
                        ((HabPoint) vHabPoints.get(0)).goRight = false; //Если уже есть, то добавлять её не надо
                    }
                }
                if (((HabPoint) vHabPoints.get(0)).goRight == true) {
                    vHabPoints.add(new HabPoint(((HabPoint) vHabPoints.get(0)).line, ((HabPoint) vHabPoints.get(0)).column + 1, coloredGameArray[((HabPoint) vHabPoints.get(0)).line][((HabPoint) vHabPoints.get(0)).column + 1]));
                }
            }
            //System.out.println(vHabPoints.size());


            //Записываю текущюю точку в вектор с координатами точек фигуры
            if (!findingFigure.contains((HabPoint) vHabPoints.get(0))) { //Если этой точки ещё нет в искомой фигуре - добовляем её
                findingFigure.add((HabPoint) vHabPoints.get(0));
                //System.out.println("findingFigure.size()  " + findingFigure.size());
                //System.out.println(findingFigure);
            }
            //Удаляю текущюю точку из вектора с узловыми точками
            vHabPoints.remove(0);
            if (vHabPoints.size() > 0) {
                //System.out.println("vHabPoints.size() " + vHabPoints.size());
                //System.out.println(((HabPoint) vHabPoints.get(vHabPoints.size() - 1)).line + "   "
                //      + ((HabPoint) vHabPoints.get(vHabPoints.size() - 1)).column);
            }

            //
            for (int i = 0; i < findingFigure.size(); i++) {
                //System.out.println("FINDING " + i + "    " + ((HabPoint) findingFigure.get(i)).line + "    " + ((HabPoint) findingFigure.get(i)).column);
            }
            //
        } //
        //Пока не подправлю алгоритм - буду подчищать лишние точки тут
        for (int i = 0; i < findingFigure.size(); i++) {
            for (int ii = 0; ii < findingFigure.size(); ii++) {
                if (((HabPoint) findingFigure.get(i)).line == ((HabPoint) findingFigure.get(ii)).line
                        && ((HabPoint) findingFigure.get(i)).column == ((HabPoint) findingFigure.get(ii)).column
                        && i != ii) {
                    findingFigure.remove(ii);
                    //System.out.println("i       " + i + " ii       " + ii);
                    i = 0;
                    ii = 0;
                }
            }
        }
        return findingFigure;
    }

    public void printFlyArray(boolean[][] fa) {
        for (int i = 0; i < fa.length; i++) {
            System.out.print("line # " + i + "  ");
            for (int j = 0; j < fa[0].length; j++) {
                if (fa[i][j] == true) {
                    System.out.print("1 ");
                }
                if (fa[i][j] != true) {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void flyFiguresMoveDownOld(int line) {
        boolean[][] flyGameArray = new boolean[line + 1][];
        //boolean[][] flyGameArray = new boolean[gArray.length-1][];
        int numFlyUnits = 0;
        for (int i = 0; i < flyGameArray.length; i++) {
            flyGameArray[i] = new boolean[gArray[i].length];
            for (int j = 0; j < gArray[i].length; j++) {
                //if (gArray[i][j] == true) {
                if (coloredGameArray[i][j] != null) {
                    flyGameArray[i][j] = true;
                    numFlyUnits++; //Считаю число квадратиков в висящей фигуре
                } else {
                    flyGameArray[i][j] = false;
                }
            }
        }

        System.out.println("FLY FIGURE 1");
        System.out.println("NUM FLY UNITS " + numFlyUnits);

        //Переменная будет хранить количество висячих фигур,
        //которые надо сместить вниз. Предпологаю, что у нас одна такая фигура
        int numFlyFigures = 1; //Ставлю 1 - чтобы зайти в цикл
        while (numFlyFigures > 0) { //Пока есть висящие фигуры - ищу и смещаю их вниз
            System.out.println("FLY FIGURE 2");
            printFlyArray(flyGameArray);

            Vector allFlyingFigures = new Vector(); //Сюда буду записывать массивы с найдеными фигурами
            System.out.println("BIGIN allFlyingFigures.size " + allFlyingFigures.size());
            //Пока есть элементы, которые мы не записали в вектор висячих фигур
            //ищу их и запоминаю
            numFlyFigures = 0; //Ставлю 0 - чтобы посчитать количество
            int numOfRemovedElements = 0;

            while (numOfRemovedElements < numFlyUnits) {
                System.out.println("FLY FIGURE 3");
                System.out.println("allFlyingFigures.size 1 = " + allFlyingFigures.size());

                allFlyingFigures.add(this.flyFigureSerch(flyGameArray)); //Надо задать число вызовов

                System.out.println("allFlyingFigures.size 2 = " + allFlyingFigures.size());
                System.out.println("((Vector) allFlyingFigures.lastElement()).size = "
                        + ((Vector) allFlyingFigures.lastElement()).size());
                //Элементы, которые внесли в вектор фигур
                //надо удалить из игрового массива
                //и подсчитать число удалённных элементов
                numOfRemovedElements = 0;
                for (int k = 0; k < allFlyingFigures.size(); k++) {
                    for (int kk = 0; kk < ((Vector) allFlyingFigures.get(k)).size(); kk++) {
                        int i = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).line;
                        int j = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).column;
                        flyGameArray[i][j] = false;
                        System.out.println("Here I deleted flying figures");
                        numOfRemovedElements++;
                    }
                }
            }
            //Когда все висящие фигры найдены - определяю те, которые надо смещать вниз
            Vector<Boolean> isFlying = new Vector();
            System.out.println("isFlying size " + isFlying.size());
            for (int k = 0; k < allFlyingFigures.size(); k++) {
                System.out.println("FLY FIGURE 4");
                System.out.println("allFlyingFigures.size " + allFlyingFigures.size());
                for (int kk = 0; kk < ((Vector) allFlyingFigures.get(k)).size(); kk++) {
                    System.out.println("FLY FIGURE 5");
                    System.out.println("((Vector) allFlyingFigures.get(k)).size "
                            + ((Vector) allFlyingFigures.get(k)).size());
                    int i = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).line;
                    int j = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).column;
                    if (i == line) {
                        isFlying.add(false);
                        kk = ((Vector) allFlyingFigures.get(k)).size();
                    }
                    if (kk == ((Vector) allFlyingFigures.get(k)).size() - 1
                            && i != line) {
                        isFlying.add(true);
                        numFlyFigures++;
                    }
                }
            }

            System.out.println(isFlying);
            System.out.println("isFlying2 size " + isFlying.size());

            //printFlyArray(flyGameArray);
            //printFlyArray(gArray);

            //Те фигуры, которые висят в воздухе - смещаем на одну линию вниз
            for (int k = 0; k < allFlyingFigures.size(); k++) {
                if (isFlying.get(k) == true) { //Если фигура висячая - смещаем её вниз
                    System.out.println("FLY FIGURE 6 " + k + "   " + isFlying.get(k));
                    for (int kk = 0; kk < ((Vector) allFlyingFigures.get(k)).size(); kk++) {
                        int i = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).line;
                        int j = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).column;
                        //тут буду смещать массив цветов
                        coloredGameArray[i + 1][j] = coloredGameArray[i][j];
                        coloredGameArray[i][j] = null;
                        //тут смещаю подмассив игровой зоны
                        //flyGameArray[i + 1][j] = true;
                        //flyGameArray[i][j] = false;
                    }
                }
            }

            for (int i = 0; i < flyGameArray.length; i++) {
                for (int j = 0; j < flyGameArray[0].length; j++) {
                    if (coloredGameArray[i][j] != null) {
                        System.out.println("ОЧЕНЬ ВАЖНОЕ УСЛОВИЕ");
                        flyGameArray[i][j] = true;
                    }
                }
            }

            printFlyArray(flyGameArray);
            //printFlyArray(gArray);

            //Выполняю перерисовку
            for (int ii = 0; ii < gArray.length; ii++) {
                System.out.println("FLY FIGURE 7");
                for (int jj = 0; jj < gArray[0].length; jj++) {
                    if (coloredGameArray[ii][jj] != null) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawRect(jj * size, ii * size, size, size);
                        graphics.setColor(coloredGameArray[ii][jj]);
                        graphics.fillRect(jj * size + 1, ii * size + 1, size - 1, size - 1);
                        graphics.setColor(Color.BLACK);
                    }
                    if (coloredGameArray[ii][jj] == null) {
                        graphics.setColor(Color.WHITE);
                        graphics.drawRect(jj * size, ii * size, size, size);
                        graphics.fillRect(jj * size + 1, ii * size + 1, size - 1, size - 1);
                        graphics.setColor(Color.BLACK);
                    }
                }
            }
            System.out.println("FLY FIGURE 8");
        }

    }

    //Функция ищет все фигуры выше и на уровне удалённой линии
    //и смещает их вниз
    public void flyFiguresMoveDown() {
        //public boolean[][] flyFiguresMoveDown() {
        this.showAmbientFigure();
        Vector allFlyingFigures = new Vector(); //Сюда буду записывать найденные фигуры
        int numFlyUnits = 0;
        for (int i = 0; i < gArray.length; i++) {
            for (int j = 0; j < gArray[i].length; j++) {
                if (gArray[i][j] == true) {
                    numFlyUnits++; //Считаю число квадратиков в висящей фигуре
                }
            }
        }
        System.out.println("NUM FLY UNITS " + numFlyUnits);
        System.out.println("BEGIN allFlyingFigures.size " + allFlyingFigures.size());

        //Пока есть элементы, которые мы не записали в вектор висячих фигур
        //ищу их и запоминаю
        int numOfRemovedElements = 0;
        while (numOfRemovedElements < numFlyUnits) {
            //System.out.println("FLY FIGURE 3");
            System.out.println("allFlyingFigures.size 1 = " + allFlyingFigures.size());

            allFlyingFigures.add(this.flyFigureSerch(gArray));

            System.out.println("allFlyingFigures.size 2 = " + allFlyingFigures.size());
            System.out.println("((Vector) allFlyingFigures.lastElement()).size = "
                    + ((Vector) allFlyingFigures.lastElement()).size());
            //Элементы, которые внесли в вектор фигур
            //надо удалить из игрового массива
            //и подсчитать число удалённных элементов
            numOfRemovedElements = 0;
            for (int k = 0; k < allFlyingFigures.size(); k++) {
                for (int kk = 0; kk < ((Vector) allFlyingFigures.get(k)).size(); kk++) {
                    int i = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).line;
                    int j = ((HabPoint) ((Vector) allFlyingFigures.get(k)).get(kk)).column;
                    gArray[i][j] = false;
                    numOfRemovedElements++;
                }
            }
        }

        for (int i = 0; i < gArray.length; i++) { //Возращаю значения в игровой массив
            for (int j = 0; j < gArray[0].length; j++) {
                if (coloredGameArray[i][j] != null) {
                    gArray[i][j] = true;
                }
            }
        }

        //Пока правильно
        boolean isFinished = false;
        while (!isFinished) { //Пока есть висящие фигуры - ищу и смещаю их вниз
            //Когда все фигры найдены - определяю те, которые надо смещать вниз
            //isFinished = true;
            boolean[] isFallingArray = new boolean[allFlyingFigures.size()];

            for (int numFlyFigures = 0; numFlyFigures < allFlyingFigures.size(); numFlyFigures++) {
                int numFigureElements = ((Vector) allFlyingFigures.get(numFlyFigures)).size();
                boolean isFalling = true;

                //Определяем размер массива для координат нижних линий квадратиков
                int minColumn = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(0)).column;
                int maxColumn = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(0)).column;
                int[][] maxCoordsLine;

                for (int i = 0; i < numFigureElements; i++) {
                    //Определяем размер массива для координат крайних квадратиков
                    if (((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column < minColumn) {
                        minColumn = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column;
                    }
                    if (((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column > maxColumn) {
                        maxColumn = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column;
                    }
                }
                maxCoordsLine = new int[maxColumn + 1 - minColumn][];
                System.out.println("numFlyFigures " + numFlyFigures + "  " + maxCoordsLine);

                for (int i = 0; i < maxCoordsLine.length; i++) { //Заполняем массив значениям столбцов
                    maxCoordsLine[i] = new int[2];
                    maxCoordsLine[i][0] = minColumn + i;  //Номер столбца записываю в 0
                    System.out.println(numFlyFigures + " = " + i + "   " + maxCoordsLine[i][0]);
                }

                for (int i = 0; i < numFigureElements; i++) { //Для каждого элемента
                    for (int j = 0; j < maxCoordsLine.length; j++) { //Перебираю все значения столбцов
                        if (((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column == maxCoordsLine[j][0]) { //Когда нашли соответствие по столбцу
                            if (((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).line > maxCoordsLine[j][1]) { //Проверяем значение строки
                                maxCoordsLine[j][1] = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).line; //И записываем большее значение строки
                                System.out.println(numFlyFigures + " = " + i + "   " + j + "   " + maxCoordsLine[j][1]);
                            }
                        }
                    }
                }

                int maxLineNumber = maxCoordsLine[0][1];
                for (int i = 0; i < maxCoordsLine.length; i++) {
                    if (maxCoordsLine[i][1] > maxLineNumber) {
                        maxLineNumber = maxCoordsLine[i][1];
                    }
                }

                //Если фигура касается дна - её не надо смещать вниз
                if (maxLineNumber == gArray.length - 1) {
                    isFalling = false;
                    System.out.println("numFlyFigures " + numFlyFigures + " DNO " + (gArray.length - 1));
                }
                if (isFalling) { //Если фигура не касается дна - надо проверить её элементы
                    for (int i = 0; i < maxCoordsLine.length; i++) { //Заполняем массив значениям столбцов
                        //if(maxCoordsLine[i][1])
                        if (gArray[maxCoordsLine[i][1] + 1][maxCoordsLine[i][0]] == true) { //Если за максимальной строкой есть элементы - смещать её нельзя
                            isFalling = false;
                            System.out.println(numFlyFigures + " упирается  " + gArray[maxCoordsLine[i][1] + 1][maxCoordsLine[i][0]]);
                        }
                    }
                }
                isFallingArray[numFlyFigures] = isFalling;
                //Если фигуру можно смещать
                System.out.println("FIGURE NUMBER " + numFlyFigures + "  " + isFalling);

                if (isFalling) {
                    System.out.println(numFlyFigures + " NADO SMESTIT' VNIZ");
                    for (int i = 0; i < numFigureElements; i++) {
                        int ln = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).line;
                        int cn = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column;
                        //тут буду смещать массив цветов
                        //coloredGameArray[ln + 1][cn] = coloredGameArray[i][cn];
                        //coloredGameArray[ln][cn] = null;
                        //тут смещаю подмассив игровой зоны
                        //gArray[ln + 1][cn] = true;
                        //gArray[ln][cn] = false;
                        //тут смещаю элементы фигуры в векторе
                        //((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).line++;

                        //тут удаляю значения в старой позиции фигуры
                        gArray[ln][cn] = false;
                        coloredGameArray[ln][cn] = null;
                        //переношу фигуру в новую позицию
                        ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).line++;
                    }
                    for (int i = 0; i < numFigureElements; i++) {
                        int ln = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).line;
                        int cn = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).column;

                        //тут заношу данные о новой позиции фигуры
                        gArray[ln][cn] = true;
                        coloredGameArray[ln][cn] = ((HabPoint) ((Vector) allFlyingFigures.get(numFlyFigures)).get(i)).habPointColor;
                    }
                    //Выполняю перерисовку
                    for (int ii = 0; ii < coloredGameArray.length; ii++) {
                        System.out.println("FLY FIGURE 7");
                        for (int jj = 0; jj < coloredGameArray[0].length; jj++) {
                            if (coloredGameArray[ii][jj] != null) {
                                graphics.setColor(Color.BLACK);
                                graphics.drawRect(jj * size, ii * size, size, size);
                                graphics.setColor(coloredGameArray[ii][jj]);
                                graphics.fillRect(jj * size + 1, ii * size + 1, size - 1, size - 1);
                                graphics.setColor(Color.BLACK);
                            }
                            if (coloredGameArray[ii][jj] == null) {
                                graphics.setColor(Color.WHITE);
                                graphics.drawRect(jj * size, ii * size, size, size);
                                graphics.fillRect(jj * size + 1, ii * size + 1, size - 1, size - 1);
                                graphics.setColor(Color.BLACK);
                            }
                        }
                    }

                }


            }
            //Проверка на выход из while
            //если нет висячих фигур - выходим
            for (int i = 0; i < isFallingArray.length; i++) {
                isFinished = true;
                if (isFallingArray[i] == true) {
                    isFinished = false;
                    i = isFallingArray.length;
                }
            }
        } //конец while()
        //return gArray;
    }
}
