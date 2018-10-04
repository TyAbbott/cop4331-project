
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import javafx.scene.layout.Border;
import static javax.swing.JFrame.EXIT_ON_CLOSE;



public class AFewInARow extends JFrame
{
    public static final Color LIGHT_BROWN = new Color(153,102,0);
    public static final Color BROWN = new Color(102,51,0);
    public static final Color GROOVES = new Color(102,51,0,175);
    int screenHeight;
    int playerTurn=1;
    int canvasSize;
    int screenWidth;
    float boardSize=15;
    int winCondition=5;
    JMenuBar menuBar;
    JMenu game;
    Random rand;
    Image woodTex;
    Image whitePieceOg;
    Image blackPieceOg;
    Image whitePiece;
    Image blackPiece;
    ArrayList<BufferedImage> woodTile;
    JMenuItem newGame, load, save;
    JFrame newGameFrame, saveGameFrame, loadGameFrame;
    Canvas canvas;
    BoardPane board;
    double mainFrameHeight;
    double mainFrameWidth;
    static AFewInARow gameBoard;

    Game_Logic gameLogic=new Game_Logic((int)(boardSize),winCondition);
    //int x=0;

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        gameBoard= new AFewInARow();
        gameBoard.setVisible(true);
    }
    public static BufferedImage toBufferedImage(Image img){
      final float FACTOR  =1f;
      int scaleX = (int) (img.getWidth(null) * FACTOR);
      int scaleY = (int) (img.getHeight(null) * FACTOR);
      Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
      BufferedImage buffered = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_RGB);
      buffered.getGraphics().drawImage(image, 0, 0 , null);
      return buffered; 
    }
    
        //Aleks -> reads files from a folder and returns an array with the names of the files 
    public ArrayList listFilesForFolder(final File folder, ArrayList array) 
    {
        for (final File fileEntry : folder.listFiles()) 
        {
            if (fileEntry.isDirectory()) 
            {
                listFilesForFolder(fileEntry, array);
            } 
            else 
            {
                array.add(fileEntry.getName());
                //System.out.println(fileEntry.getName());
            }
        }
        return array;
    }
    
    private AFewInARow() throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = (int) screenSize.getHeight();
        setSize((int)(0.80f*(float)screenHeight), (int)(0.94f*(float)screenHeight)); // Size of UI window
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

        board = new BoardPane();
        canvasSize=(int)(0.85f*(float)screenHeight);
        board.setPreferredSize(new Dimension((int)((float)canvasSize),(int)((float)canvasSize)));

        add(board);
        pack();
        setLocationRelativeTo(null);

        System.out.println(board.getSize().width + " by "+board.getSize().height);
        System.out.println(getSize().width + " by "+getSize().height);
        //File file = new File("woodtex.jpg");
        //Recognize file as image
        woodTex = ImageIO.read(AFewInARow.class.getResource("woodtexfinal.jpg"));
        //file=new File("white.png");
        whitePieceOg=ImageIO.read(AFewInARow.class.getResource("white.png"));
        //file=new File("black.png");
        blackPieceOg=ImageIO.read(AFewInARow.class.getResource("black.png"));
        whitePiece=whitePieceOg.getScaledInstance((int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)canvasSize/boardSize),Image.SCALE_DEFAULT);
        blackPiece=blackPieceOg.getScaledInstance((int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)canvasSize/boardSize),Image.SCALE_DEFAULT);
        woodTex=woodTex.getScaledInstance(canvasSize,canvasSize,Image.SCALE_DEFAULT);
        //woodTile=new ArrayList<BufferedImage>();
        //rand=new Random();

//        for(int i=0;i<10;++i){
//          Image tempImg=
//          woodTex.getSubimage(
//            rand.nextInt(3000-(int)(15f*((float)canvasSize)/((float)boardSize))),
//            rand.nextInt(2200-(int)(15f*((float)canvasSize)/((float)boardSize))),
//            (int)(15f*((float)canvasSize)/((float)boardSize)),(int)(15f*((float)canvasSize)/((float)boardSize)))
//            .getScaledInstance(
//              (int)(0.9*(float)canvasSize/boardSize),
//              (int)(0.9*(float)(canvasSize)/boardSize),
//              Image.SCALE_DEFAULT
//            );
//          int w=tempImg.getWidth(null);
//          int h=tempImg.getHeight(null);
//          BufferedImage newImage = new BufferedImage(w, h,
//          BufferedImage.TYPE_INT_ARGB);
//          Graphics g2 = newImage.getGraphics();
//          g2.drawImage(tempImg, 0, 0, null);
//          g2.dispose();
//          woodTile.add(newImage);
//          mainFrameHeight = getSize().getHeight();
//          mainFrameWidth = getSize().getWidth();
//        }
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
              JOptionPane.showMessageDialog(this,"White Wins!");
              board.removeMouseListener(board);
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
                JOptionPane.showMessageDialog(this,"Black Wins!");
                board.removeMouseListener(board);
              }
            }else{
              System.out.println("failed to add at "+ (int)(((((float)(e.getX()))/((float)(canvasSize))))*boardSize)+" and "+(int)(((((float)(e.getY()))/((float)(canvasSize))))*boardSize));
            }
          }
        }
      }
      protected void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2;
        g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(woodTex,0,0,null);
        for(int i=0;i<boardSize;++i){
          g2.setColor(GROOVES);
          g2.fillRect((int)((float)canvasSize/(boardSize)/2.f),(int)((float)i*(float)canvasSize/(float)boardSize)+(int)(0.5f*(float)canvasSize/boardSize)-(int)(0.025f*(float)canvasSize/boardSize),canvasSize-(int)((float)canvasSize/(boardSize)),(int)(0.05*(float)canvasSize/boardSize));
          g2.fillRect((int)((float)i*(float)canvasSize/(float)boardSize)+(int)(0.5f*(float)canvasSize/boardSize)-(int)(0.025f*(float)canvasSize/boardSize),(int)((float)canvasSize/(boardSize)/2.f),(int)(0.05*(float)canvasSize/boardSize),canvasSize-(int)((float)canvasSize/(boardSize)));
        }
        for(int i=0;i<boardSize;++i){
          for(int j=0;j<boardSize;++j){
            /*g2.setColor(BROWN);
            g2.fillRect(i*(int)((float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize),(int)((float)canvasSize/boardSize),(int)((float)(canvasSize)/boardSize));
            g2.setColor(LIGHT_BROWN);
            //g.fillRect(i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
            //    (int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)(canvasSize)/boardSize));
            rand=new Random(i*j);
            g2.drawImage(woodTile.get(rand.nextInt(10)),
            //g2.drawImage(woodTex.getSubimage(i*(int)(3f*((float)canvasSize)/((float)boardSize)),j*(int)(3f*((float)canvasSize)/((float)boardSize)),(int)(3f*((float)canvasSize)/((float)boardSize)),(int)(3f*((float)canvasSize)/((float)boardSize))).getScaledInstance(
            //      (int)(0.9*(float)canvasSize/boardSize),
            //      (int)(0.9*(float)(canvasSize)/boardSize),
            //      Image.SCALE_DEFAULT
            //    ),
                i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
                j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize), null);
                */
            if(gameLogic.getMapValueAt(i,j)==1){
              g2.setColor(Color.black);
              //g.fillOval(i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
              //    (int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)(canvasSize)/boardSize));
              g2.drawImage(blackPiece,
                (int)((float)i*((float)canvasSize/boardSize))+(int)(0.05f*(float)canvasSize/boardSize),
                (int)((float)j*((float)canvasSize/boardSize))+(int)(0.05f*(float)canvasSize/boardSize),null);
            }else{
              if(gameLogic.getMapValueAt(i,j)==2){
                g2.setColor(Color.white);
                //g.fillOval(i*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),j*(int)((float)canvasSize/boardSize)+(int)(0.05*(float)canvasSize/boardSize),
                //    (int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)(canvasSize)/boardSize));
                g2.drawImage(whitePiece,
                  (int)((float)i*((float)canvasSize/boardSize))+(int)(0.05f*(float)canvasSize/boardSize),
                  (int)((float)j*((float)canvasSize/boardSize))+(int)(0.05f*(float)canvasSize/boardSize),null);
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
            board.removeMouseListener(board);
            game.setEnabled(false);

            FlowLayout experimentLayout = new FlowLayout();
            newGameFrame = new JFrame();
            newGameFrame.setLayout(experimentLayout);
            newGameFrame.setUndecorated(true);
            newGameFrame.setAlwaysOnTop (true);
            newGameFrame.setTitle("Settings");
            newGameFrame.setSize((int)(0.5f*(0.95f*(float)screenHeight)),(int)(0.5f*(0.95f*(float)screenHeight)));
            newGameFrame.setResizable(false);
            String[] colorOptions = {"White", "Black"};
            String[] goalOptions = {"3","4","5","6","7","8"};
            String[] sizeOptions = {"3 x 3","5 x 5","7 x 7 ","9 x 9","11 x 11","13 x 13","15 x 15"};
            JButton playbutton = new JButton("Play");//0=3, 1=5, 2=7, 3=9, 4=11, 5=13 -> 2*(i+1)+1
            //playbutton.setPreferredSize(new Dimension((int)mainFrameWidth/5,(int)mainFrameWidth/5));
            JComboBox player1combo = new JComboBox(colorOptions);
            JComboBox player2combo = new JComboBox(colorOptions);
            JComboBox goalcombo = new JComboBox(goalOptions);
            JComboBox sizecombo = new JComboBox(sizeOptions);
            player2combo.setSelectedIndex(1);
            goalcombo.setSelectedIndex(2);
            sizecombo.setSelectedIndex(6);
            JLabel player1label = new JLabel("Player 1:");
            JLabel player2label = new JLabel("Player 2:");
            JLabel goallabel = new JLabel("# in a row:");
            JLabel sizelabel = new JLabel("Board size:");
            playbutton.setEnabled(true);
            playbutton.addActionListener(new ActionListener(){

              public void actionPerformed(ActionEvent e)
              {
                //if(playbutton.getModel().isPressed()){
                  gameLogic.clearBoard();
                  board.repaint();
                  if(player2combo.getSelectedIndex()==1){
                    playerTurn=1;
                  }else{
                    playerTurn=-1;
                  }
                  winCondition=goalcombo.getSelectedIndex()+3;
                  boardSize=2*(sizecombo.getSelectedIndex()+1)+1;
                  gameLogic.setGoal(winCondition);
                  whitePiece=whitePieceOg.getScaledInstance((int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)canvasSize/boardSize),Image.SCALE_DEFAULT);
                  blackPiece=blackPieceOg.getScaledInstance((int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)canvasSize/boardSize),Image.SCALE_DEFAULT);
                  System.out.println("new game!");
                  newGameFrame.dispatchEvent(new WindowEvent(newGameFrame, WindowEvent.WINDOW_CLOSING));
                //}
              }
            });

            newGameFrame.add(player1label);
            newGameFrame.add(player1combo);
            newGameFrame.add(player2label);
            newGameFrame.add(player2combo);
            newGameFrame.add(sizelabel);
            newGameFrame.add(sizecombo);
            newGameFrame.add(goallabel);
            newGameFrame.add(goalcombo);
            newGameFrame.add(playbutton);

            newGameFrame.pack();
            newGameFrame.setLocationRelativeTo(null);

            newGameFrame.setVisible(true);


            player1combo.addActionListener (new ActionListener ()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(player1combo.getSelectedItem().toString() == "White")
                    {
                        player2combo.setSelectedIndex(1);
                    }
                    else
                    {
                        player2combo.setSelectedIndex(0);
                    }
                }
            });
            player2combo.addActionListener (new ActionListener ()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(player2combo.getSelectedItem().toString() == "White")
                    {
                        player1combo.setSelectedIndex(1);
                    }
                    else
                    {
                        player1combo.setSelectedIndex(0);
                    }
                }
            });


            newGameFrame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    board.addMouseListener(board);
                    game.setEnabled(true);
                }
            });
        }
    }
    
       private class Load implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            board.removeMouseListener(board);
            game.setEnabled(false);
            
            // Creating an instance of Load_Save_Game.java class
            Load_Save_Game loading = new Load_Save_Game(gameLogic.getMap(), 0, 0, 0);
            
            FlowLayout experimentLayout = new FlowLayout();
            loadGameFrame = new JFrame();
            loadGameFrame.setLayout(experimentLayout);
            loadGameFrame.setAlwaysOnTop (true);
            loadGameFrame.setTitle("Load Game");
            loadGameFrame.setResizable(false);
            
            // Read files from the save directory and put them in the savedFiles array
            ArrayList<String> savedFiles = new ArrayList<>();
                                                            // Directory of where saves are pulled out of and put into a Jlist to be selected
            final File folder = new File(System.getProperty("user.home") + "/Desktop");
            listFilesForFolder(folder, savedFiles);
            JList savesList = new JList(savedFiles.toArray());
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(savesList);
            loadGameFrame.add(scrollPane);
            JButton selectButton = new JButton("Select");
            loadGameFrame.add(selectButton);
            
            loadGameFrame.pack();
            loadGameFrame.setLocationRelativeTo(null);
            loadGameFrame.setVisible(true);
            selectButton.addActionListener(new ActionListener(){

              public void actionPerformed(ActionEvent e)
              {
                  Object selected = savesList.getSelectedValue();
                  loading.load(System.getProperty("user.home") + "/Desktop/" + selected.toString());
                  gameLogic.loadGame(loading.getBoard(), loading.getGoal());
                  playerTurn = loading.getPlayer();
                  boardSize = loading.getSize();
                  winCondition = loading.getGoal();
                  
                  whitePiece=whitePieceOg.getScaledInstance((int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)canvasSize/boardSize),Image.SCALE_DEFAULT);
                  blackPiece=blackPieceOg.getScaledInstance((int)(0.9*(float)canvasSize/boardSize),(int)(0.9*(float)canvasSize/boardSize),Image.SCALE_DEFAULT);
                  board.repaint();
                  loadGameFrame.dispatchEvent(new WindowEvent(loadGameFrame, WindowEvent.WINDOW_CLOSING));
              }
            });
            
            loadGameFrame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    board.addMouseListener(board);
                    game.setEnabled(true);
                }
            });
        }
    }

    private class Save implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
             // Creating an instance of Load_Save_Game.java class
            Load_Save_Game saving = new Load_Save_Game(gameLogic.getMap(), playerTurn, winCondition, (int) boardSize);
            
            saveGameFrame = new JFrame();
            FlowLayout experimentLayout = new FlowLayout();
            saveGameFrame.setLayout(experimentLayout);
            saveGameFrame.setAlwaysOnTop (true);
            saveGameFrame.setTitle("Save Game");
            saveGameFrame.setResizable(false);
            
            JTextField nameLabel = new JTextField(" File Name: "){                
                public void setBorder(Border border) 
                {
                    // Removes Borded around "File Name: "
                }
            };
            nameLabel.setEditable(false);
            saveGameFrame.add(nameLabel);
            JTextField saveName = new JTextField(20);
            saveGameFrame.add(saveName);
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener(){

              public void actionPerformed(ActionEvent e)
              {
                  saving.save(saveName.getText());
                  if(saveName.getText().length() == 0)
                  {
                      JOptionPane.showMessageDialog(saveGameFrame, "Name Field Required");
                  }
                  else
                  saveGameFrame.dispatchEvent(new WindowEvent(saveGameFrame, WindowEvent.WINDOW_CLOSING));
              }
            });
            saveGameFrame.add(saveButton);
            
            saveGameFrame.pack();
            saveGameFrame.setLocationRelativeTo(null);
            saveGameFrame.setVisible(true);
            
        }
    }
}
