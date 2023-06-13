package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_OldMan extends Entity{

  public NPC_OldMan(GamePanel gp){
    super(gp);

    direction = "down";
    speed = 1;

    getImage();
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

}
