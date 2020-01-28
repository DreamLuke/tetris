package tetris;

import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luke
 */
public class Unit {

    int x;
    int y;
    int size;
    Graphics graphics;
    Color paintColor;
    boolean[][] gameAr;

    public Unit(Graphics graphics, int xStart, int yStart, int size, Color paintColor, boolean[][] gameAr) {
        this.graphics = graphics;
        this.x = xStart;
        this.y = yStart;
        this.size = size;
        this.paintColor = paintColor;
        this.gameAr = gameAr;
    }

    public void show() {
        Color oldC = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, size, size);
        graphics.setColor(paintColor);
        graphics.fillRect(x + 1, y + 1, size - 1, size - 1);
        //
        //graphics.setColor(Color.BLACK);
        //graphics.drawOval(x+size/4, y+size/4, size/2, size/2);
        //
        graphics.setColor(oldC);

        if (this.y / this.size >= 0 && this.x / this.size >= 0) {
            gameAr[this.y / this.size][this.x / this.size] = true;
        }
    }

    public void hide() {
        //System.out.println("Unit, hide, x= " + x + ", y= " + y + ", size= " + size);
        //Color oldC = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawRect(x, y, size, size);
        graphics.fillRect(x + 1, y + 1, size - 1, size - 1);
        graphics.setColor(Color.BLACK);

        if (this.y / this.size >= 0 && this.x / this.size >= 0) {
            gameAr[this.y / this.size][this.x / this.size] = false;
        }
    }

    int getLine() {
        return this.y / this.size;
    }

    int getColumn() {
        return this.x / this.size;
    }

    public void moveDown(int height) {
        //if (this.y < height - this.size && (height - this.y) / size > 1) {
        if ((height - this.y) / size > 1) {
            hide();
            //System.out.println("hdx= " + getX());
            //System.out.println("hdy= " + getY());
            this.y += this.size;
            show();
            //System.out.println("sdx= " + getX());
            //System.out.println("sdy= " + getY());
        }
    }

    public void moveLeft() {
        if (this.x >= this.size) {
            hide();
            //System.out.println("hlx= " + getX());
            //System.out.println("hly= " + getY());
            this.x -= this.size;
            show();
            //System.out.println("slx= " + getX());
            //System.out.println("sly= " + getY());
        }
    }

    public void moveRight(int width) {
        //if (this.x < width - this.size && (width - this.x) / size > 1) {
        if ((width - this.x) / size > 1) {
            hide();
            //System.out.println("hrx= " + getX());
            //System.out.println("hry= " + getY());
            this.x += this.size;
            show();
            //System.out.println("srx= " + getX());
            //System.out.println("sry= " + getY());
        }
    }

    public Color getColor() {
        //System.out.println(this.paintColor);
        return this.paintColor;
    }
}
