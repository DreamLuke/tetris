/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameTetris.java
 *
 * Created on Jul 6, 2015, 3:01:05 PM
 */
package tetris;

//import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Luke
 */
public class JFrameTetris extends javax.swing.JFrame implements KeyListener {

    AmbientFigure af;
    TetrisFigure tf;
    TetrisFigure tfNext;
    TetrisFigure tfPaint;
    int panelWidth, panelHeight; //Размеры игрового поля
    int size; //Размер квадратика
    boolean gameNet[][]; //Масив для хранения игровой сетки
    Color[] arrayWithColors = new Color[7];
    int colorNumber;
    int colorNumberNext;
    int figureNumber;
    int figureNumberNext;
    ActionListener listener = new TimerAct();
    Timer t = new Timer(1000, listener);
    boolean isTimerExit = false;
    boolean canMoveSide = true;
    //Timer t = new Timer(100, listener);

    /** Creates new form JFrameTetris */
    public JFrameTetris() {
        initComponents();
        this.setFocusable(true);

        

        panelWidth = jPanel1.getWidth();
        panelHeight = jPanel1.getHeight();
        size = (panelWidth - 1) / 10;

        gameNet = new boolean[20][];
        for (int i = 0; i < gameNet.length; i++) {
            gameNet[i] = new boolean[10];
        }


        af = new AmbientFigure(jPanel1.getGraphics(), gameNet, size);

        //Цвета, которые будут использоваться для расскарски деталей
        arrayWithColors[0] = Color.GREEN;
        arrayWithColors[1] = new Color(0, 192, 192);
        arrayWithColors[2] = Color.CYAN;
        arrayWithColors[3] = Color.LIGHT_GRAY;
        //arrayWithColors[4] = new Color(192, 192, 0);
        arrayWithColors[4] = Color.YELLOW;
        arrayWithColors[5] = Color.PINK;
        arrayWithColors[6] = Color.ORANGE;

        colorNumber = (int) (Math.random() * 7); //Выбор номера цвета от 0 до 6
        colorNumberNext = (int) (Math.random() * 7); //Выбор номера цвета от 0 до 6
        figureNumber = (int) (Math.random() * 7); //Выбор номера фигуры от 0 до 6
        figureNumberNext = (int) (Math.random() * 7); //Выбор номера фигуры от 0 до 6

        //Текущая фигура
        if (figureNumber == 0) {
            tf = new TFSquare(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
        }
        if (figureNumber == 1) {
            tf = new TFStick(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
        }
        if (figureNumber == 2) {
            tf = new TFFirTree(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
        }
        if (figureNumber == 3) {
            tf = new TFHookFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
        }
        if (figureNumber == 4) {
            tf = new TFLFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
        }
        if (figureNumber == 5) {
            tf = new TFSFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);

        }
        if (figureNumber == 6) {
            tf = new TFZFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
        }

        //Следующая фигура
        if (figureNumberNext == 0) {
            tfNext = new TFSquare(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        if (figureNumberNext == 1) {
            tfNext = new TFStick(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        if (figureNumberNext == 2) {
            tfNext = new TFFirTree(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        if (figureNumberNext == 3) {
            tfNext = new TFHookFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        if (figureNumberNext == 4) {
            tfNext = new TFLFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        if (figureNumberNext == 5) {
            tfNext = new TFSFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        if (figureNumberNext == 6) {
            tfNext = new TFZFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
        }
        //Отрисовка следующей фигуры        
        tfPaint = tf;
        paintNext(jPanel2.getGraphics(), tfPaint, size);

        t.setDelay(1000);
        //t.setDelay(100);
        t.start();

        //Создание слушателей кнопок
        this.addKeyListener(this);
        jPanel1.addKeyListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame"); // NOI18N
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(-1,true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(-1,true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        jMenuBar1.setToolTipText("");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Отобразить
    //Скрыть
    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        //tfs.showTetrisFigure();
        af.showAmbientFigure();
        this.repaint();
        jPanel1.repaint();
        jPanel2.repaint();

        this.update(this.getGraphics());
        jPanel1.update(jPanel1.getGraphics());
        jPanel2.update(jPanel2.getGraphics());
    }//GEN-LAST:event_formWindowStateChanged

    //Поиск заполненной строки
    public boolean findFullLine(boolean[][] ar, int line) {
        for (int j = 0; j < ar[line].length; j++) {
            //System.out.println(j +"BEGIN");
            if (ar[line][j] == false) {
                //System.out.println(j +" "+ar[line][j]);
                return false;


            }
            if (j == ar[0].length - 1 && ar[line][ar[0].length - 1] == true) {
                //System.out.println(line +" "+ j +" "+ar[line][j]);
                return true;


            }
        }
        return false;


    }

    //Эта функция рисует фигуру, которая будет следующей
    public void paintNext(Graphics gr, TetrisFigure tfp, int sizeP) {
        Color pColor = tfp.unitsArray[0].getColor();
        int minLine = tfp.unitsArray[0].getLine();
        int minColumn = tfp.unitsArray[0].getColumn();
        int deltaLine, deltaColumn;
        //System.out.println();       
        for (int i = 0; i < tfp.unitsArray.length; i++) {
            if (tfp.unitsArray[i].getLine() < minLine) {
                minLine = tfp.unitsArray[i].getLine();


            }
            if (tfp.unitsArray[i].getColumn() < minColumn) {
                minColumn = tfp.unitsArray[i].getColumn();


            }
        }

        deltaLine = Math.abs(minLine);
        deltaColumn = Math.abs(minColumn);
        

        //Выполняю очистку поля для следующей фигуры
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, 81, 81);
        gr.setColor(Color.BLACK);
        //Выполняю отрисовку фигуры
        for (int i = 0; i < tfp.unitsArray.length; i++) {
            int ii = tfp.unitsArray[i].getLine() + deltaLine;


            int jj = tfp.unitsArray[i].getColumn() - deltaColumn;

            gr.setColor(Color.BLACK);
            gr.drawRect(jj * sizeP, ii * sizeP, sizeP, sizeP);
            gr.setColor(pColor);
            gr.fillRect(jj * sizeP + 1, ii * sizeP + 1, sizeP - 1, sizeP - 1);
            gr.setColor(Color.BLACK);


        }
        //gr.setColor(Color.BLACK);
        gr.setColor(pColor);
    }

    //ПЭЙНТ
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        af.showAmbientFigure();
        tf.showTetrisFigure();
        this.paintNext(jPanel2.getGraphics(), tfPaint, size);        
    }

    //ТАЙМЕР
    class TimerAct implements ActionListener {

        public void actionPerformed(ActionEvent event) {            
            downFunction();
        }
    }

    public void downFunction() {
                //Пока текущая фигура не вылезла - рисую её как следеющую
            for (int i = 0; i < tf.unitsArray.length; i++) {                
                if (tf.unitsArray[0].getLine() == -1) {                    
                    tfPaint = tfNext;                    
                    paintNext(jPanel2.getGraphics(), tfPaint, size);                    
                }
            }

            tf.moveDownTetrisFigure(panelHeight);
            af.showAmbientFigure();
            boolean isGameOver = false;


            //Если фигура остановилась - добовляем её к статической фигуре
            if (tf.isStoped) {
                af.showAmbientFigure();
                //Проверка на проигрыш
                for (int i = 0; i < tf.unitsArray.length; i++) {                    
                    if (tf.unitsArray[i].getLine() < 0) {                        
                        isGameOver = true;
                        i = tf.unitsArray.length;
                    }
                }

                if (isGameOver) {
                    t.stop();
                    boolean goDown = true;
                    //Сюда надо добавить толковый код смещения фигуры при проигрыше
                    int maxLine = tf.unitsArray[0].getLine();
                    for (int k = 0; k < tf.unitsArray.length; k++) {
                        if (tf.unitsArray[k].getLine() > maxLine) {
                            maxLine = tf.unitsArray[k].getLine();
                        }
                    }
                    if (maxLine >= 0) {
                        goDown = false;                        
                    }
                    
                    if (maxLine == -2) {
                    
                        tf.unitsArray[0].y = tf.unitsArray[0].y + size;
                        tf.unitsArray[1].y = tf.unitsArray[1].y + size;
                        tf.unitsArray[2].y = tf.unitsArray[2].y + size;
                        tf.unitsArray[3].y = tf.unitsArray[3].y + size;
                    }
                    //
                    for (int k = 0; k < tf.unitsArray.length; k++) {
                        if (tf.unitsArray[k].getLine() > maxLine) {
                            maxLine = tf.unitsArray[k].getLine();
                        }
                    }
                    
                    
                    while (goDown) {                        
                        for (int k = 0; k < tf.unitsArray.length; k++) {
                            if (tf.unitsArray[k].getLine() + 1 >= 0) {
                                if (af.gArray[tf.unitsArray[k].getLine() + 1][tf.unitsArray[k].getColumn()] == true) {
                                    goDown = false;                                    
                                }
                            }
                        }
                        if (goDown) {
                            for (int k = 0; k < tf.unitsArray.length; k++) {
                                tf.unitsArray[k].y = tf.unitsArray[k].y + size;
                            }
                            for (int k = 0; k < tf.unitsArray.length; k++) {
                                if (tf.unitsArray[k].getLine() >= 0) {
                                    tf.unitsArray[k].show();
                                }
                            }
                        }
                    }
                    //
                    int response = JOptionPane.showConfirmDialog(null, "ПРОИГРЫШ. ПРОДОЛЖИТЬ?", "title", JOptionPane.YES_NO_OPTION);
                    switch (response) {
                        case JOptionPane.YES_OPTION:
                            System.out.println("Ды!");
                            initComponents();
                            for (int ii = 0; ii < gameNet.length; ii++) {
                                for (int jj = 0; jj < gameNet[0].length; jj++) {
                                    gameNet[ii][jj] = false;
                                    af.coloredGameArray[ii][jj] = null;
                                    tf = null;
                                }
                            }
                            //
                            colorNumber = (int) (Math.random() * 7); //Выбор номера цвета от 0 до 6
                            colorNumberNext = (int) (Math.random() * 7); //Выбор номера цвета от 0 до 6
                            figureNumber = (int) (Math.random() * 7); //Выбор номера фигуры от 0 до 6
                            figureNumberNext = (int) (Math.random() * 7); //Выбор номера фигуры от 0 до 6

                            //Текущая фигура
                            if (figureNumber == 0) {
                                tf = new TFSquare(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
                            }
                            if (figureNumber == 1) {
                                tf = new TFStick(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
                            }
                            if (figureNumber == 2) {
                                tf = new TFFirTree(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
                            }
                            if (figureNumber == 3) {
                                tf = new TFHookFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
                            }
                            if (figureNumber == 4) {
                                tf = new TFLFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
                            }
                            if (figureNumber == 5) {
                                tf = new TFSFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);

                            }
                            if (figureNumber == 6) {
                                tf = new TFZFigure(jPanel1.getGraphics(), arrayWithColors[colorNumber], size, gameNet);
                            }

                            //Следующая фигура
                            if (figureNumberNext == 0) {
                                tfNext = new TFSquare(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                            }
                            if (figureNumberNext == 1) {
                                tfNext = new TFStick(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                            }
                            if (figureNumberNext == 2) {
                                tfNext = new TFFirTree(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                            }
                            if (figureNumberNext == 3) {
                                tfNext = new TFHookFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                            }
                            if (figureNumberNext == 4) {
                                tfNext = new TFLFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                            }
                            if (figureNumberNext == 5) {
                                tfNext = new TFSFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);

                            }
                            if (figureNumberNext == 6) {
                                tfNext = new TFZFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                            }
                            //Отрисовка следующей фигуры
                            tfPaint = tf;
                            paintNext(jPanel2.getGraphics(), tfPaint, size);
                            repaint();                            

                            
                            t.setDelay(1000);
                            t.start();
                            break;
                        case JOptionPane.NO_OPTION:                            
                            System.exit(0);
                            break;
                        case JOptionPane.CLOSED_OPTION:                            
                            System.exit(0);
                            break;
                    }
                }

                if (!isGameOver) {
                    af.add(tf);
                    af.showAmbientFigure();
                }

                //Проверка наличия заполненных строк
                for (int i = gameNet.length - 1; i > 0; i--) { //Не знаю как правильно >=0 или >0
                    if (findFullLine(gameNet, i)) {
                        af.deleteLine(i);
                        af.showAmbientFigure();
                        //af.moveToDeletedLine(i);
                        for (int ii = 0; ii < gameNet.length; ii++) {
                            for (int jj = 0; jj < gameNet[0].length; jj++) {
                                if (af.coloredGameArray[ii][jj] == null) {
                                    gameNet[ii][jj] = false;
                                }
                                if (af.coloredGameArray[ii][jj] != null) {
                                    gameNet[ii][jj] = true;
                                }
                            }
                        }
                        //
                        af.moveToDeletedLine(i);
                        af.showAmbientFigure();
                        for (int ii = 0; ii < gameNet.length; ii++) {
                            for (int jj = 0; jj < gameNet[0].length; jj++) {
                                if (af.coloredGameArray[ii][jj] == null) {
                                    gameNet[ii][jj] = false;
                                }
                                if (af.coloredGameArray[ii][jj] != null) {
                                    gameNet[ii][jj] = true;
                                }
                            }
                        }
                        af.flyFiguresMoveDown();
                        af.showAmbientFigure();
                        for (int ii = 0; ii < gameNet.length; ii++) {
                            for (int jj = 0; jj < gameNet[0].length; jj++) {
                                if (af.coloredGameArray[ii][jj] == null) {
                                    gameNet[ii][jj] = false;
                                }
                                if (af.coloredGameArray[ii][jj] != null) {
                                    gameNet[ii][jj] = true;
                                }
                            }
                        }
                        i = gameNet.length;
                    }
                }
               

                //Вызов новой фигуры
                colorNumberNext = (int) (Math.random() * 7); //Выбор номера цвета от 0 до 6
                figureNumberNext = (int) (Math.random() * 7); //Выбор номера фигуры от 0 до 6

                tf = tfNext;
                tfPaint = tf;
                //t.setDelay(1000);
                //Отрисовка следующей фигуры
                paintNext(jPanel2.getGraphics(), tfPaint, size);
                //jPanel2.repaint();
                t.start();

                if (figureNumberNext == 0) {
                    tfNext = new TFSquare(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                }
                if (figureNumberNext == 1) {
                    tfNext = new TFStick(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                }
                if (figureNumberNext == 2) {
                    tfNext = new TFFirTree(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                }
                if (figureNumberNext == 3) {
                    tfNext = new TFHookFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                }
                if (figureNumberNext == 4) {
                    tfNext = new TFLFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                }
                if (figureNumberNext == 5) {
                    tfNext = new TFSFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);

                }
                if (figureNumberNext == 6) {
                    tfNext = new TFZFigure(jPanel1.getGraphics(), arrayWithColors[colorNumberNext], size, gameNet);
                }                
            }
            tf.showTetrisFigure();
    }

    //Описание действий по нажатию кнопок клавиатуры
    public void keyPressed(KeyEvent ke) {
        //Выход
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        //Пауза
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            if (t.isRunning()) {
                t.stop();
                return;
            }
            if (!t.isRunning()) {
                t.start();
                return;
            }
        }

        //Поворот фигуры
        //if (ke.getKeyCode() != KeyEvent.VK_DOWN) {
        if (ke.getKeyCode() == KeyEvent.VK_UP) {            
            tf.rotateTetrisFigure(panelWidth, panelHeight);
            af.showAmbientFigure();
        }
        //}
        //Движение вниз
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {            
            t.stop();
            downFunction();


        }
        //Движение влево
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (canMoveSide) {                
                tf.moveLeftTetrisFigure(panelHeight);
                af.showAmbientFigure();
                
            }
        }
        //Движение вправо
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (canMoveSide) {                
                tf.moveRightTetrisFigure(panelHeight, panelWidth);
                af.showAmbientFigure();
            }
        }


    }

    public void keyReleased(KeyEvent ke) {        
        t.start();
    }

    public void keyTyped(KeyEvent ke) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrameTetris jft3 = new JFrameTetris();
                jft3.setVisible(true);
            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
