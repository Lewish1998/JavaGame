package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

  // Screen settings
  final int originalTileSize = 16;
  final int scale = 3;

  public final int tileSize = originalTileSize * scale;
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol;
  public final int screenHeight = tileSize * maxScreenRow;

  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldCol;
  public final int worldHeight = tileSize * maxWorldRow;
  int FPS = 60;

  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();

  Sound se = new Sound();
  Sound music = new Sound();
  public CollisionChecker cChecker = new CollisionChecker(this);
  public AssetSetter aSetter = new AssetSetter(this);
  public UI ui = new UI(this);
  Thread gameThread;

  //  Entity & object
  public Player player = new Player(this, keyH);
  public SuperObject obj[] = new SuperObject[10];

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void setupGame(){
    aSetter.setObject();
    playMusic(0);
  }


  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }


  @Override
  public void run(){
    double drawInterval = 1_000_000_000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    while (gameThread != null){
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;
      if(delta >= 1){
        update();
        repaint();
        delta--;
      }
    }
  }

  public void update(){
    player.update();
  }

  public void paintComponent(Graphics g){

    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;

    long drawStart = 0;
    if (keyH.checkDrawTime) {
      drawStart = System.nanoTime();
    }
    tileM.draw(g2);

    for (int i = 0; i <obj.length; i++){
      if (obj[i] != null){
        obj[i].draw(g2, this);
      }
    }
    player.draw(g2);

    ui.draw(g2);
    if (keyH.checkDrawTime) {
      long drawEnd = System.nanoTime();
      long passed = drawEnd - drawStart;
      g2.setColor(Color.white);
      g2.drawString("Draw Time: " + passed, 10, 400);
      System.out.println("Draw Time: " + passed);
    }
    g2.dispose();
  }


  public void playMusic(int i){
    music.setFile(i);
    music.play();
    music.loop();
  }

  public void stopMusic(){
    music.stop();
  }

  public void playSE(int i){
    se.setFile(i);
    se.play();
  }
}