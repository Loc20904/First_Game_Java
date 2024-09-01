
package entity;

import Monster.Slime;
import Object.OBJ_Blue_Shield;
import Object.OBJ_FireBall;
import Object.OBJ_Potion_red;
import Object.OBJ_Shiled_normal;
import Object.OBJ_Sword_normal;
import Object.OBJ_axe;
import Object.OBJ_coin;
import Object.OBJ_key;
import Pk_Main.Jpanel;
import Pk_Main.keyHandler;
import Pk_Main.utilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Player extends entity {

    keyHandler keyh;
    
    public final int screenX;
    public final int screenY;  
    boolean win=false;   
    int count=0;
    public boolean attackCancel=false;
    
    public ArrayList<entity> inventory=new ArrayList<>();
    public int inventorySize=20;
    
    public Player(Jpanel a, keyHandler b)
    {
        super(a);
        this.keyh=b;
        name="Player";
  
        screenX=(pn.screenWith/2)-(pn.tilesize/2);
        screenY=(pn.screenHeight/2)-(pn.tilesize/2);
        
        soliArea.x=8;
        soliArea.y=16;
        soliAreaDefaultX=soliArea.x;
        soliAreaDefaultY=soliArea.y;
        soliArea.width=20;
        soliArea.height=20;
        
        setDefaultValue();
        getPlayerImage();
        getImageAttack();
        setIventory();
    }
    
    public void setIventory()
    {
        inventory.clear();
        inventory.add(currentSword);
        inventory.add(currentSheild);      
    }
    
    public int getAttack()
    {
        attackArea=currentSword.attackArea;
        return streng+currentSword.streng;
    }
    
    public int getDefend()
    {
        return dexterity+currentSheild.defen;
    }
    
    public void setDefaultValue()
    {
        worldX=33*pn.tilesize;
        worldY=40*pn.tilesize;
        speed=4;
        diredtion="up";
        maxlife=6;
        maxMana=6;
        mana=maxMana;
        life=maxlife;
        streng=1;
        dexterity=1;
        exp=0;
        nextCurrentLevel=5;
        level=1;
        coin=0;
        currentSword=new OBJ_Sword_normal(pn);
        currentSheild=new OBJ_Shiled_normal(pn);
        
        projectile=new OBJ_FireBall(pn);
        
        attack=getAttack();
        defen=getDefend();
    }
    
    public void getImageAttack()
    {
        if(currentSword.type==type_Sword)
        {
            attackup1 = setup("/player/boy_attack_up_1", pn.tilesize, pn.tilesize * 2);
            attackdown1 = setup("/player/boy_attack_down_1", pn.tilesize, pn.tilesize * 2);
            attackleft1 = setup("/player/boy_attack_left_1", pn.tilesize * 2, pn.tilesize);
            attackright1 = setup("/player/boy_attack_right_1", pn.tilesize * 2, pn.tilesize);
            attackup2 = setup("/player/boy_attack_up_2", pn.tilesize, pn.tilesize * 2);
            attackdown2 = setup("/player/boy_attack_down_2", pn.tilesize, pn.tilesize * 2);
            attackleft2 = setup("/player/boy_attack_left_2", pn.tilesize * 2, pn.tilesize);
            attackright2 = setup("/player/boy_attack_right_2", pn.tilesize * 2, pn.tilesize);
        }
        else if(currentSword.type==type_axe)
        {
            attackup1 = setup("/player/boy_axe_up_1", pn.tilesize, pn.tilesize * 2);
            attackdown1 = setup("/player/boy_axe_down_1", pn.tilesize, pn.tilesize * 2);
            attackleft1 = setup("/player/boy_axe_left_1", pn.tilesize * 2, pn.tilesize);
            attackright1 = setup("/player/boy_axe_right_1", pn.tilesize * 2, pn.tilesize);
            attackup2 = setup("/player/boy_axe_up_2", pn.tilesize, pn.tilesize * 2);
            attackdown2 = setup("/player/boy_axe_down_2", pn.tilesize, pn.tilesize * 2);
            attackleft2 = setup("/player/boy_axe_left_2", pn.tilesize * 2, pn.tilesize);
            attackright2 = setup("/player/boy_axe_right_2", pn.tilesize * 2, pn.tilesize);
        }
        
    }
    
    public void getPlayerImage()
    {  
        up1 = setup("/player/boy_up_1", pn.tilesize,pn.tilesize);
        down1 = setup("/player/boy_down_1", pn.tilesize,pn.tilesize);
        left1 = setup("/player/boy_left_1", pn.tilesize,pn.tilesize);
        right1 = setup("/player/boy_right_1", pn.tilesize,pn.tilesize);
        up2 = setup("/player/boy_up_2", pn.tilesize,pn.tilesize);
        down2 = setup("/player/boy_down_2", pn.tilesize,pn.tilesize);
        left2 = setup("/player/boy_left_2", pn.tilesize,pn.tilesize);
        right2 = setup("/player/boy_right_2", pn.tilesize,pn.tilesize);

    }
    
    @Override
    public void update()
    {
        if(attacking==true)
        {
            attacking();
        }
        else if (keyh.keyDown || keyh.keyLeft || keyh.keyUp || keyh.keyright || keyh.keyenter || keyh.keyShoot) {
            if (keyh.keyUp) {
                diredtion = "up";
            } else if (keyh.keyDown) {
                diredtion = "down";
            } else if (keyh.keyLeft) {
                diredtion = "left";
            } else if (keyh.keyright) {
                diredtion = "right";
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
            
            //UPDATE SHOOT FIRE BALL
            if(keyh.keyShoot==true && projectile.alive==false && shootCounter==30 && mana>0)
            {
                projectile.set(worldX,worldY,diredtion,true,this);
                pn.projecttileList.add(projectile);
                pn.playSE(9);
                shootCounter=0;
                mana--;
                if(mana<0)
                {
                    mana=0;
                }
            }

        collisionOn=false;
        //CHECK TILE COLLISION
        pn.cCheck.checkTile(this);
        
        //CHECK OBJECT COLLISION
        int indexObject=pn.cCheck.checkObject(this, true);
        checkPickup(indexObject);
        
        //CHECK NPC COLLISION
        int indexNPC=pn.cCheck.checkEntity(this,pn.npc);
        interacNPC(indexNPC);
        
        //CHECK MONSTER COLLISION
        int indexMonster=pn.cCheck.checkEntity(this, pn.monster);
        contactMonster(indexMonster);
        
        //CHECK EVENT
        pn.eventh.checkEvent();
        
        if(keyh.keyenter==true && attackCancel==false)
        {
            pn.playSE(5);
            attacking=true;
            strikeCount=0;
        }
        attackCancel=false;
        
        pn.keyh.keyenter=false;
        if(collisionOn==false && (keyh.keyUp||keyh.keyDown||keyh.keyLeft||keyh.keyright))
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
        }
        }
        //RECOVER MANA
        manaCounter++;
        if (manaCounter == 360) {
            mana++;
            if(mana>maxMana)
                mana=maxMana;
            manaCounter = 0;
        }

        if (shootCounter < 30) {
            shootCounter++;
        }

        if(invisible==true)
        {
            invisibleCounter++;
            if(invisibleCounter>40)
            {
                invisible=false;
                invisibleCounter=0;
            }
        }
        
 
    }
    
    public void attacking()
    {
        strikeCount++;
        if(strikeCount<=5)
        {
            strikeNum=1;
        }
        if(strikeCount<=25)
        {
            strikeNum=2;
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int soliAreaWidth=soliArea.width;
            int soliAreaHeight=soliArea.height;
            
            switch(diredtion)
            {
                case "up":worldY-=attackArea.height;break;
                case "down":worldY +=attackArea.height;break;
                case "left":worldX -=attackArea.width;break;
                case "right":worldX +=attackArea.width;break;
            }
            
            soliArea.width=attackArea.width;
            soliArea.height=attackArea.height;
            
            int index=pn.cCheck.checkEntity(this, pn.monster);
            dameMonster(index,attack,false);
            
            worldX=currentWorldX;
            worldY=currentWorldY;
            soliArea.width=soliAreaWidth;
            soliArea.height=soliAreaHeight;
            
        }
        if(strikeCount>25)
        {
            strikeNum=1;
            strikeCount=0;
            attacking=false;
        }
    }
    
    public void dameMonster(int index, int attack,boolean checkFireBall)
    {
        if (index != 999) {
            if (pn.monster[pn.currentMap][index].invisible == false) {
                pn.playSE(6);
                int damage=0;
                if(checkFireBall && pn.monster[pn.currentMap][index].name.equalsIgnoreCase("boss"))
                    damage=attack;
                else
                    damage=attack-pn.monster[pn.currentMap][index].defen;
                if(damage<0)
                    damage=0;
                pn.monster[pn.currentMap][index].life-=damage;  
                pn.ui.drawMess(damage+" Damage!");
                if(pn.monster[pn.currentMap][index] instanceof Slime)
                    pn.monster[pn.currentMap][index].diredtion=diredtion;
                else
                {
                    switch (pn.player.diredtion) {
                        case "up":
                            pn.monster[pn.currentMap][index].diredtion = "down";
                            break;
                        case "down":
                            pn.monster[pn.currentMap][index].diredtion = "up";
                            break;
                        case "left":
                            pn.monster[pn.currentMap][index].diredtion = "right";
                            break;
                        case "right":
                            pn.monster[pn.currentMap][index].diredtion = "left";
                            break;
                    }
                }
                pn.monster[pn.currentMap][index].invisible=true;
                if (pn.monster[pn.currentMap][index].life<1) {
                    pn.monster[pn.currentMap][index].alive=false;
                    ItemDrop(pn.monster[pn.currentMap][index].worldX, pn.monster[pn.currentMap][index].worldY);
                    pn.ui.drawMess("Killed "+pn.monster[pn.currentMap][index].name+"!");
                    if(pn.monster[pn.currentMap][index].name.equalsIgnoreCase("Boss"))
                    {
                        pn.state=pn.finish;
                        pn.stopMusic();
                        pn.playSE(3);
                    }
                    exp+=pn.monster[pn.currentMap][index].exp;
                    pn.ui.drawMess("+"+pn.monster[pn.currentMap][index].exp+" EXP!");
                    checkLeverUp();
                }
                
            }
        }  
    }
    
    public void ItemDrop(int WorldX,int worldY)
    {
        int itemNumber=getLevelDrop();
        entity itemDrop=null;
        switch(itemNumber)
        {
            case 1:
            {
                itemDrop=new OBJ_Potion_red(pn);
                break;
            }
            case 2:
            {
                itemDrop=new OBJ_coin(pn);
                break;
            }
            case 3:
            {
                itemDrop=new OBJ_Blue_Shield(pn);
                break;
            }
            case 4:
            {
                itemDrop=new OBJ_axe(pn);
            }
        }
        itemDrop.worldX=WorldX;
        itemDrop.worldY=worldY;   
        addToOBJ(itemDrop);
    }
    
    public int getLevelDrop()
    {
        //RANDOM ITEM DROP
        Random rd=new Random();
        if((1+Math.abs(rd.nextInt())%5)==2)//CHANGE TO LEVEL 2
        {
            if((1+Math.abs(rd.nextInt())%100)==77)//CHANGE TO LEVEL 3
            {
                return (1+Math.abs(rd.nextInt())%2==1)?3:4;
            }
            else//DROP Red Poition
            {
                return 1;
            }
        }
        else//DROP COIN
        {
            return 2;
        }
    }
    
    public void addToOBJ(entity a)
    {
        if(getNumElement(pn.obj)<pn.obj.length)
        {
            for (int i = 0; i < pn.obj[pn.currentMap].length; i++) {
                if (pn.obj[pn.currentMap][i] == null) {
                    pn.obj[pn.currentMap][i] =a;
                    break;
                }
            }
        }
    }
    
    public int getNumElement(entity a[][])
    {
        int count=0;
        for(int i=0;i<a[pn.currentMap].length;i++)
        {
            if(a[pn.currentMap][i]!=null)
                count++;
        }
        return count;
    }
    
    public void checkLeverUp()
    {
        if (exp>nextCurrentLevel) {
            exp-=nextCurrentLevel;
            level++;
            pn.playSE(8);
            streng++;
            attack=getAttack();
            dexterity++;
            defen=getDefend();
            pn.keyh.messCheckFrom=true;
            if(level==5)
            {
                pn.stage=2;
                pn.ast.setObject();
                pn.ui.dialogMess="Find Old man to report";
            }
            else
                pn.ui.dialogMess="You are Level Up\nLevel: "+level+"\n Now you are more Stroger";
            pn.state=pn.gameDinalogMess;           
            nextCurrentLevel = nextCurrentLevel+2;
            if(level%2==0)
            {
                maxlife++;
                life++;
            }
        }
    }
    
    public void interacNPC(int i)
    {
        if (pn.keyh.keyenter == true) {
            if (i != 999) {
                //count = i;
                attackCancel=true;
                if(pn.stage==2 && messIndex==0)
                    pn.npc[pn.currentMap][i].setMess();
                pn.state = pn.gameDinalogMess;
                pn.keyh.messCheckFrom=false;
                pn.npc[pn.currentMap][i].speek();
            }
        }
           
    }
    
    public void contactMonster(int i)
    {
        if(i!=999)
        {
            if(invisible==false && pn.monster[pn.currentMap][i].alive==true)
            {
                pn.playSE(7);
                int damege=pn.monster[pn.currentMap][i].attack-defen;
                if(damege<0)
                    damege=0;
                life-=damege;
                invisible=true;
                if(life<=0)
                {
                    pn.state=pn.gameOver;
                }
            }
        }
    }
    
    public void checkPickup(int i)
    {
        if(i!=999)
        {
            if(inventory.size()!=inventorySize)
            {
                if (pn.obj[pn.currentMap][i].name.equalsIgnoreCase(" 1 coin")) {
                    pn.player.coin++;
                } 
                else if(pn.obj[pn.currentMap][i].name.equalsIgnoreCase("gate") || pn.obj[pn.currentMap][i].name.equalsIgnoreCase("fight"))
                {
                    //DON'T PICK UP THIS OBJECT
                    return;
                }
                else {
                    inventory.add(pn.obj[pn.currentMap][i]);
                }
                pn.playSE(1);
                pn.ui.drawMess("You had get the "+pn.obj[pn.currentMap][i].name+"!");
                pn.obj[pn.currentMap][i]=null;
            }
        }
    }
    
    public void selectItem()
    {
        int i=pn.ui.getIndexItem();
        if(i<=inventory.size()-1)
        {
            entity selectItem=pn.player.inventory.get(i);
            if(selectItem.type==type_Sword || selectItem.type==type_axe)
            {
                currentSword=selectItem;
                attack=getAttack();
                getImageAttack();
            }
            else if(selectItem.type==type_Sheild)
            {
                currentSheild=selectItem;
                defen=getDefend();
            }
            else if(selectItem.type==type_comsumable)
            {
                selectItem.useItem(this);
                inventory.remove(i);
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g2d)
    {
        BufferedImage image=null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;
        switch(diredtion)
        {
            case "up":
            {
                if (attacking == true) {
                    tempScreenY-=pn.tilesize;
                    if (strikeNum == 1) {
                        image = attackup1;
                    } else {
                        image = attackup2;
                    }
                } else {
                    if (strikeNum == 1) {
                        image = up1;
                    } else {
                        image = up2;
                    }
                }
                break;
            }
            case "down":
            {
                if (attacking == true) {
                    if (strikeNum == 1) {
                        image = attackdown1;
                    } else {
                        image = attackdown2;
                    }
                } else {
                    if (strikeNum == 1) {
                        image = down1;
                    } else {
                        image = down2;
                    }
                }
                break;
            }
            case "left":
            {
                if (attacking == true) {
                    tempScreenX-=pn.tilesize;
                    if (strikeNum == 1) {
                        image = attackleft1;
                    } else {
                        image = attackleft2;
                    }
                } else {
                    if (strikeNum == 1) {
                        image = left1;
                    } else {
                        image = left2;
                    }
                }
                break;
            }
            case "right":
            {
              if (attacking == true) {
                    
                    if (strikeNum == 1) {
                        image = attackright1;
                    } else {
                        image = attackright2;
                    }
                } else {
                    if (strikeNum == 1) {
                        image = right1;
                    } else {
                        image = right2;
                    }
                }
                break;
            }
        }
        
        if(invisible==true)
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2d.drawImage(image,tempScreenX, tempScreenY,null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
}
