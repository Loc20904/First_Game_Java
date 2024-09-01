
package entity;

import Pk_Main.Jpanel;
import Pk_Main.utilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;


public class entity {

    public Jpanel pn;
    
    public int worldX;
    public int worldY;
    public int speed;
    
    public BufferedImage image,image2,image3;   
    public boolean collision=false;
    public String name;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackup1,attackup2,attackdown1,attackdown2,attackleft1,attackleft2,attackright1,attackright2;
    public String diredtion="down";
    public int strikeCount;
    public int strikeNum=1;
    
    public Rectangle soliArea=new Rectangle(0,0,36,36);
    public int soliAreaDefaultX=0;
    public int soliAreaDefaultY=0;
    public Rectangle attackArea=new Rectangle(0,0,23,23);
    
    
    public boolean collisionOn=false;
    public int counterLockOn;
    
    public String dialogMes[]=new String[10];
    public int messIndex=0;
    
    //CHARECTER STATUS
    public int maxlife;
    public int life;
    public int maxMana;
    public int mana;
    public int manaCounter;
    public int level;
    public int exp;
    public int attack;
    public int streng;
    public int defen;
    public int dexterity;
    public int nextCurrentLevel;
    public int coin;
    public entity currentSword;
    public entity currentSheild;
    
    public boolean attacking=false;
    //TYPE
    public int type;  //0=player, 1=NPC, 2=Monster
    public int type_player=0;
    public int type_NPC=1;
    public int type_Monster=2;
    public int type_Sword=3;
    public int type_axe=4;
    public int type_Sheild=5;
    public int type_comsumable=6;
    
    public int useCost; //How many mana need to use
    public projecttile projectile;
    public int shootCounter;
            
    public boolean invisible=false;
    int invisibleCounter=0;
    
    boolean hpBarOn=false;
    int hpBarCount=0;
    
    public boolean alive=true;
    public int dyingCounter=0;
    
    public String description="";
    
    public entity(Jpanel pn)
    {
        this.pn=pn;
    }
    
    public void speek(){}
    public void setMess(){}
    public void setAction()
    {
        
    }
    
    public void update()
    {
        setAction();
        collisionOn=false;
        pn.cCheck.checkTile(this);
        pn.cCheck.checkObject(this, false);
        pn.cCheck.checkEntity(this, pn.npc);
        pn.cCheck.checkEntity(this, pn.monster);
        boolean contactPlayer=pn.cCheck.checkPlayer(this);
        if(type==type_Monster && contactPlayer==true)
        {
            if(pn.player.invisible==false && alive==true)
            {
                pn.playSE(7);
                int damege=attack-pn.player.defen;
                if(damege<0)
                    damege=0;
                life-=damege;
                pn.player.life-=damege;
                pn.player.invisible=true;   
                if(pn.player.life<=0)
                {
                    pn.state=pn.gameOver;
                }
            }    
        }     
        
        if(collisionOn==false)
        {
            switch(diredtion)
            {
                case "up":
                {
                    worldY-=speed;
                    break;
                }
                case "down":
                {
                    worldY+=speed;
                    break;
                }
                case "left":
                {
                    worldX-=speed;
                    break;
                }
                case "right":
                {
                    worldX+=speed;
                    break;
                }
            }
             strikeCount++;
            if (strikeCount > 10) {
                if (strikeNum == 1) {
                    strikeNum = 2;
                } else {
                    strikeNum = 1;
                }
                strikeCount = 0;
            }
        }
        
        if(invisible==true)
        {
            hpBarOn=true;
            hpBarCount=0;
            invisibleCounter++;
            if(invisibleCounter>40)
            {
                invisible=false;
                invisibleCounter=0;
            }
        }
        counterLockOn++; 
    }
    
    public BufferedImage setup(String name,int width, int height)
    {
        utilityTool utool=new utilityTool();
        BufferedImage image=pn.tileMn.type[12].image;
        try {
            InputStream is=entity.class.getResourceAsStream(name+".png");
            if(is==null)
                System.out.println("aaaaaaaaaaaaaa");
            image=ImageIO.read(is);
            image=utool.scaleImage(image, width , height);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    } 
    
    public void draw(Graphics2D g2d)
    {        
        int screenX = worldX - pn.player.worldX + pn.player.screenX;
        int screenY = worldY - pn.player.worldY + pn.player.screenY;

        if (worldX + pn.tilesize > pn.player.worldX - pn.player.screenX
                && worldX - pn.tilesize < pn.player.worldX + pn.player.screenX
                && worldY + pn.tilesize > pn.player.worldY - pn.player.screenY
                && worldY - pn.tilesize < pn.player.worldY + pn.player.screenY) 
        {
            BufferedImage image = null;
            switch (diredtion) {
                case "up": {
                    if (strikeNum == 1) {
                        image = up1;
                        
                    } else {
                        image = up2;
                    }
                    break;
                }
                case "down": {
                    if (strikeNum == 1) {
                        image = down1;
                    } else {
                        image = down2;
                    }
                    break;
                }
                case "left": {
                    if (strikeNum == 1) {
                        image = left1;
                    } else {
                        image = left2;
                    }
                    break;
                }
                case "right": {
                    if (strikeNum == 1) {
                        image = right1;
                    } else {
                        image = right2;
                    }
                    break;
                }
            }
            if (invisible == true) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            
            if(alive == false)
            {
                dieAnimation(g2d);
            }
            
            if(type==type_Monster && hpBarOn==true)
            {
                int temp=(maxlife-life)*(pn.tilesize/maxlife);
                g2d.setColor(Color.gray);
                g2d.fillRect(screenX, screenY-5, pn.tilesize,5);
                g2d.setColor(Color.red);
                g2d.fillRect(screenX, screenY-5, pn.tilesize-temp, 5);
                hpBarCount++;
                if (hpBarCount>600) {
                    hpBarCount=0;
                    hpBarOn=false;
                }
            }
            
            g2d.drawImage(image, screenX, screenY, pn.tilesize, pn.tilesize, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
    public void dieAnimation(Graphics2D g2d)
    {
        dyingCounter++;
        if(dyingCounter<10)
        {
            changeAlpha(g2d, 0.4F);
        }
        else if(dyingCounter<20)
        {
            changeAlpha(g2d, 0.8F);
        }
        else if(dyingCounter<30)
        {
            changeAlpha(g2d, 0.4F);
        }
        else if(dyingCounter<40)
        {
            changeAlpha(g2d, 0.8F);
        }
        else
            life=-999;
    }
    public void useItem(entity entity)
    {
        
    }
    
    public void changeAlpha(Graphics2D g2d, float x)
    {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, x));
    }
    
    public void save()
    {
        try {
            BufferedWriter wr=new BufferedWriter(new FileWriter(new File((pn.gameData.fileData)),true));
            
            wr.newLine();
            wr.write("worldX:"+worldX);
            wr.newLine();
            wr.write("worldY:"+worldY);
            wr.newLine();
            wr.write("speed:"+speed);
            wr.newLine();
            wr.write("strikeCount:"+strikeCount);
            wr.newLine();
            wr.write("strikeNum:"+strikeNum);
            wr.newLine();
            wr.write("soliAreaDefaultX:"+soliAreaDefaultX);
            wr.newLine();
            wr.write("soliAreaDefaultY:"+soliAreaDefaultY);
            wr.newLine();
            wr.write("counterLockon:"+counterLockOn);
            wr.newLine();
            wr.write("messIndex:"+messIndex);
            wr.newLine();
            wr.write("life:"+life);
            wr.newLine();
            wr.write("mana:"+mana);
            wr.newLine();
            wr.write("mana counter:"+manaCounter);
            wr.newLine();
            wr.write("level:"+level);
            wr.newLine();
            wr.write("exp:"+exp);
            wr.newLine();
            wr.write("attack:"+attack);
            wr.newLine();
            wr.write("streng:"+streng);
            wr.newLine();
            wr.write("defen:"+defen);
            wr.newLine();
            wr.write("dexterity:"+dexterity);
            wr.newLine();
            wr.write("next current level:"+nextCurrentLevel);
            wr.newLine();
            wr.write("coin:"+coin);
            wr.newLine();
//            wr.write("currentSword:"+currentSword);
//            wr.newLine();
//            wr.write("current shield:"+currentSheild);
//            wr.newLine();
            wr.write("shoot counter:"+shootCounter);
            wr.newLine();
            wr.write("alive:"+alive);
            wr.newLine();
            wr.write("dying counter:"+dyingCounter);
            
            wr.close();
            
        } catch (IOException ex) {
            System.out.println("Save data fail");
        }
    }
}
