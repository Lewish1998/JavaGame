package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity{

  public NPC_OldMan(GamePanel gp){
    super(gp);

    direction = "right";
    speed = 1;

    getImage();
    setDialogue();
  }

  public void getImage() {
    up1 = setup("npc/man1_up_1");
    up2 = setup("npc/man1_up_2");

    down1 = setup("npc/man1_down_1");
    down2 = setup("npc/man1_down_2");

    left1 = setup("npc/man1_left_1");
    left2 = setup("npc/man1_left_2");

    right1 = setup("npc/man1_right_1");
    right2 = setup("npc/man1_right_2");

  }

  public void setDialogue(){
    dialogues[0] = "Hello, Son.";
    dialogues[1] = "You've come to this island \nto find the treasure?";
    dialogues[2] = "Yer a wizard, Harry";
    dialogues[3] = "K bye";
  }

  public void setAction(){

    actionLockCounter += 1;

    if (actionLockCounter == 90){

      Random random = new Random();
      int i = random.nextInt(100) + 1;
      if (i <= 25){
        direction = "up";}
      if (i > 25 && i <= 50){
        direction = "down";}
      if (i >50 && i <= 75){
        direction = "left";}
      if (i > 75 && i <=100) {
        direction = "right";
      }
      actionLockCounter = 0;
    }

  }

  public void speak(){

    // Do this character specific stuff

    super.speak();
  }

}
