import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;



public class AFewInARow extends JFrame
{
    public static final Color LIGHT_BROWN = new Color(153,102,0);
    public static final Color BROWN = new Color(102,51,0);
    int screenHeight;
    int playerTurn=1;
    int canvasSize;
    int screenWidth;
    float boardSize=19;
    JMenuBar menuBar;
    JMenu game;
    JMenuItem newGame, load, save;
    JFrame newGameFrame;
    Canvas canvas;
    Game_Logic gameLogic=new Game_Logic((int)(boardSize),5);
    //int x=0;

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        new AFewInARow().setVisible(true);
    }

    private AFewInARow() throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = (int) screenSize.getHeight();
        setSize((int)(0.80f*(float)screenHeight), (int)(0.94f*(float)screenHeight)); // Size of UI window

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        game = new JMenu("Game");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new newGame());

        load = new JMenuItem("Load");
        load.addActionListener(new Load());
        save = new JMenuItem("Save");
        save.addActionListener(new Save());
        game.add(newGame);
        game.add(load);
        game.add(save);
        menuBar.add(game);


        setJMenuBar(menuBar);
        //canvas = new Canvas();
        //canvas.setSize(200, 100);
        //canvas.setBackground(BROWN);
        //add(canvas);
        BoardPane board = new BoardPane();
        canvasSize=(int)(0.85f*(float)screenHeight);
        board.setPreferredSize(new Dimension((int)((float)canvasSize*0.99f),(int)((float)canvasSize*0.99f)));

        add(board);
        pack();

        System.out.println(board.getSize().width + " by "+board.getSize().height);
        System.out.println(getSize().width + " by "+getSize().height);

        //game = new Game_Logic(19,5);
        /*gameLogic.updateBoard(10,10,2);
        gameLogic.updateBoard(11,11,2);
        gameLogic.updateBoard(11,10,2);
        gameLogic.updateBoard(12,10,1);
        gameLogic.updateBoard(11,12,1);
        gameLogic.updateBoard(10,12,1);
        gameLogic.updateBoard(12,9,1);
        gameLogic.updateBoard(10,8,1);
        gameLogic.updateBoard(11,9,2);
        gameLogic.updateBoard(9,11,1);
        gameLogic.updateBoard(8,9,1);
        gameLogic.updateBoard(9,10,2);
        */

    }
    public class BoardPane extends JPanel implements MouseListener{
      BoardPane(){
        addMouseListener(this);
      }
      @Override
      public void mousePressed(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
      public void mouseReleased(MouseEvent e){}
      public void mouseClicked(MouseEvent e) {
        System.out.println("mouse pressed at "+e.getX()+" and "+e.getY());
        int mouseX=(int)(((((float)(e.getX()))/((float)(canvasSize))))*boardSize);
        int mouseY=(int)(((((float)((float)e.getY()))/((float)(canvasSize))))*boardSize);
        if(playerTurn>0){
          if(gameLogic.updateBoard(mouseX,mouseY,2)){
            playerTurn*=-1;
            System.out.println("added at "+ (int)(((((float)(e.getX()))/((float)(canvasSize))))*boardSize)+" and "+(int)(((((float)(e.getY()))/((float)(canvasSize))))*boardSize));
            super.repaint();
            if(gameLogic.checkForWin(mouseX,mouseY)){
              System.out.println("player 1 wins!");
              System.exit(0);
              //win screen here
            }
          }else{
            System.out.println("failed to add at "+ (int)(((((float)(e.getX()))/((float)(canvasSize))))*boardSize)+" and "+(int)(((((float)(e.getY()))/((float)(canvasSize))))*boardSize));
          }
        }else{
          if(playerTurn<0){
            if(gameLogic.updateBoard(mouseX,mouseY,1)){
              playerTurn*=-1;
              System.out.println("added at "+ (int)(((((float)(e.getX()))/((float)(canvasSize))))*boardSize)+" and "+(int)(((((float)(e.getY()))/((float)(canvasSize))))*boardSize));
              super.repaint();
              if(gameLogic.checkForWin(mouseX,mouseY)){
                System.out.println("player 2 wins!");
                System.exit(0);
                //win screen here
              }
            }else{
              System.out.println("failed to add at "+ (int)(((((float)(e.getX()))/((float)(canvasSize))))*boardSize)+" and "+(int)(((((float)(e.getY()))/((float)(canvasSize))))*boardSize));
            }
          }
        }
      }
      protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i=0;i<boardSize;++i){
          for(int j=0;j<boardSize;++j){
            g.setColor(BROWN);
            g.fillRect(i*(int)((float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize),(int)((float)canvasSize/boardSize),(int)((float)(canvasSize)/boardSize));
            g.setColor(LIGHT_BROWN);
            g.fillRect(i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
                (int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)(canvasSize)/boardSize));
            if(gameLogic.getMapValueAt(i,j)==1){
              g.setColor(Color.black);
              g.fillOval(i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
                  (int)(0.8*(float)canvasSize/boardSize),(int)(0.8*(float)(canvasSize)/boardSize));
            }else{
              if(gameLogic.getMapValueAt(i,j)==2){
                g.setColor(Color.white);
                g.fillOval(i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
                    (int)(0.8*(float)canvasSize/boardSize),(int)(0.8*(float)(canvasSize)/boardSize));
              }
            }
          }
        }



        //super.repaint();
      }
    }
    //protected void paintComponent(Graphics g){
		//    g.fillRect(10, 10, 100, 100);
	  //}

    private class newGame implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            newGameFrame = new JFrame();
            newGameFrame.setVisible(true);
            newGameFrame.setSize((int)(0.25f*(0.95f*(float)screenHeight)),(int)(0.25f*(0.95f*(float)screenHeight)));
            newGameFrame.setLocationRelativeTo(null);
            newGameFrame.setResizable(false);

        }
    }

    private class Load implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

        }
    }

    private class Save implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

        }
    }
}
