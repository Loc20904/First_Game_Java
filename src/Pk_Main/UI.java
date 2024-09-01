package Pk_Main;

import Object.OBJ_Blue_Shield;
import Object.OBJ_Heart;
import Object.OBJ_Mana;
import Object.OBJ_Potion_red;
import Object.OBJ_axe;
import Object.OBJ_coin;
import entity.entity;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class UI{

    Jpanel pn;
    Graphics2D g2d;
    public int count=0;
    
    Font arial_20, arial_40, titleFont;
    BufferedImage keyImage;
    public ArrayList<String> message=new ArrayList<>();
    public ArrayList<Integer> messageCounter=new ArrayList<>();
    boolean messOn=false;
    public boolean finish=false;
    double playTime;
    public String dialogMess="";
    
    //INVENTORY
    public int colnum,rownum;
    public int rowinven=5;
    public int colinven=6;
    
    public int numCommand=0;
    
    public BufferedImage heart_blank,heart_half,heart_full;
    public BufferedImage mana_blank,mana_half,mana_full;
    public BufferedImage imageItemDrop;
    
    public UI(Jpanel pn)
    {
        try {
            this.pn=pn;
            arial_20=new Font("Arial",Font.PLAIN, 20);
            arial_40=new Font("Arial",Font.BOLD, 40);
            
            keyImage=ImageIO.read(new File("key.png"));
        } catch (IOException ex) {
            
        }
        entity heart=new OBJ_Heart(pn);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;
        entity mana=new OBJ_Mana(pn);
        mana_full=mana.image;
        mana_half=mana.image2;
        mana_blank=mana.image3;
        
    }
    
    public void draw(Graphics2D g2d)
    {
        this.g2d=g2d;
        if (pn.state == pn.gamePlay) {
            drawHeart();
            drawMana();
            drawMessageOnScreen();
        } else if (pn.state == pn.gamePause) {
            drawPause();
        }
        else if(pn.state==pn.gameDinalogMess)
        {      
            drawDinalogMess();
        }
        else if(pn.state==pn.gameTitle)
        {
            drawTitleGame();
        }
        else if(pn.state==pn.playerStatus)
        {
            drawCharacterStatus();
            drawInventory();    
        }
        else if(pn.state==pn.gameOver)
        {
            drawGameOver();
        }
        else if(pn.state==pn.finish)
        {
            drawFinishScreen();
        }
        else if(pn.state==pn.guide)
        {
            drawGuide();
        }
    }
    
    public void drawGuide()
    {
        g2d.setColor(Color.GRAY);
        g2d.setFont(arial_20);
        g2d.fillRect(0, 0, pn.screenWith,pn.screenHeight);
        g2d.setColor(Color.blue);
        g2d.drawString("<>Press esc to back",5,pn.screenHeight-10);
        g2d.setColor(Color.black);
        g2d.drawString("<>SeOrED<>",pn.screenWith-pn.tilesize*4,pn.screenHeight-10);
        g2d.setColor(Color.white);
        int x=pn.tilesize;
        int y=pn.tilesize;
        g2d.drawString(">>Use W,S,D,A to move character.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Press Enter to interact with NPC and attack monster.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Press I to open status of character.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Press P to Pause game.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Press F to use Magic.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Hint:Please read message form NPC and noitification.", x,y);
        y+=pn.tilesize+15;
        g2d.drawString(">>This is my first game, so that not good like game AAA in the world.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Please don't use it for unother purpose Thank you.", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>Special thank RyiSnow(Youtuber).", x,y);
        y+=pn.tilesize;
        g2d.drawString(">>That all for tutorial, let enjoy the game. Have fun :}", x,y);
        
    }
    
    public void drawFinishScreen()
    {
        
        int x=pn.tilesize*5;
        int y=pn.tilesize*2;
        int width=8*pn.tilesize;
        int height=6*pn.tilesize;
        drawSubWindown(x, y, width, height);
        
        g2d.setColor(Color.YELLOW);
        g2d.setFont(arial_20);
        String mes="YOU ARE WINNER";
        g2d.drawString(mes,x+width/2-findCenter(mes)/3, y+2*pn.tilesize+5);
        
        g2d.setFont(arial_20);
        g2d.setColor(Color.gray);
        if(numCommand==1)
        {
            g2d.fillRect(x+pn.tilesize-5, y+3*pn.tilesize+10, findCenter("retry")/5, pn.tilesize+5);
        }
        else
        {
            g2d.fillRect(x+pn.tilesize*6-5, y+3*pn.tilesize+10, findCenter("Exit")/6, pn.tilesize+5);
        }
        g2d.setColor(Color.white);
        g2d.drawString("Score:"+String.format("%.1f",pn.playTime)+"S", x+pn.tilesize, y+4*pn.tilesize);
        g2d.drawString("Exit", x+6*pn.tilesize, y+4*pn.tilesize); 
    }
    
    public void drawMessageOnScreen()
    {
        int valueX=pn.tilesize/2;
        int valueY=pn.tilesize*3;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 15F));
        
        for(int i=0;i<message.size();i++)
        {
            if(message.get(i)!=null)
            {
                g2d.setColor(Color.WHITE);
                g2d.drawString(message.get(i), valueX, valueY);
                valueY+=30;
                
                int couter=messageCounter.get(i)+1;
                messageCounter.set(i, couter);
                if(messageCounter.get(i)>120)
                {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
        
    }
    
    public void drawHeart()
    {
        int x=pn.tilesize/2;
        int y=pn.tilesize/2;
        int i=0;
        int numberHeart=(pn.player.maxlife%2!=0)?pn.player.maxlife/2+1:pn.player.maxlife/2;
        while(i<numberHeart)
        {
            g2d.drawImage(heart_blank, x,y,null);
            i++;
            x+=pn.tilesize;
        }
        x=pn.tilesize/2;
        y=pn.tilesize/2;
        i=0;
        while(i<pn.player.life)
        {
            g2d.drawImage(heart_half, x,y,null);
            i++;
            if(i<pn.player.life)
                g2d.drawImage(heart_full, x,y,null);
            i++;
            x+=pn.tilesize;       
        }
        
    }
    
    public void drawMana()
    {
        int x=pn.tilesize/2;
        int y=pn.tilesize*2;
        int i=0;
        int numMana=(pn.player.maxMana%2!=0)?pn.player.maxMana/2+1:pn.player.maxMana/2;
        while(i<numMana)
        {
            g2d.drawImage(mana_blank, x,y,null);
            i++;
            x+=pn.tilesize;
        }
        x=pn.tilesize/2;
        y=pn.tilesize*2;
        i=0;
        while(i<pn.player.mana)
        {
            g2d.drawImage(mana_half, x,y,null);
            i++;
            if(i<pn.player.mana)
                g2d.drawImage(mana_full, x,y,null);
            i++;
            x+=pn.tilesize;
        }
    }
    
    public void drawPause()
    {
        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);
        String pau="PAUSE";
        int le=findCenter(pau);
        g2d.drawString(pau, pn.screenWith/2-le/2, pn.screenHeight/2-2*pn.tilesize);
        
    }
    
    public void drawDinalogMess()
    {
        //DRAW WINDOWN
        int x=pn.tilesize;
        int y=pn.tilesize/2;
        int weith=pn.screenWith-(2*pn.tilesize);
        int height=4*pn.tilesize;
        
        drawSubWindown(x, y, weith, height);
        x+=pn.tilesize;
        y+=pn.tilesize;
        for(String line:dialogMess.split("\n"))
        {
            g2d.drawString(line, x, y);
            y+=36;
        } 
    }
    
    public void drawSubWindown(int x,int y, int w, int h){
        
        Color c=new Color(0,0,0,200);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, w, h, 20, 20);
        
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, w-10, h-10, 10, 10);
    }
    
    public void drawTitleGame()
    {
        try {
            g2d.drawImage(ImageIO.read(getClass().getResourceAsStream("/Object/titleBackground.jpg")),0,0,pn.screenWith,pn.screenHeight,null);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
//        g2d.setColor(new Color(204, 204, 255));
//        g2d.fillRect(0, 0, pn.screenWith, pn.screenHeight);
        g2d.setFont(arial_40);
        String text="LO VOI ADVENTURE";
        int x=findCenter(text);
        int y=pn.tilesize*3;
        g2d.setColor(Color.BLACK);
        g2d.drawString(text,x+3,y+3);
        g2d.setColor(new Color(255, 153, 51));
        g2d.drawString(text,x,y);
        
        //DRAW MENU GAME
        text="START";
        x=findCenter(text);
        y+=pn.tilesize*5;
        g2d.setColor(new Color(179, 217, 255));
        g2d.fillRect(x-5,y-pn.tilesize,(int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth()+12,pn.tilesize+5);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text,x,y);
        if(numCommand==0)
        {
            g2d.setColor(new Color(0, 255, 153));
            g2d.drawString(">", x-pn.tilesize, y);
        }
        
        text="GUIDE";
        x=findCenter(text);
        y+=pn.tilesize*2;
        g2d.setColor(new Color(179, 217, 255));
        g2d.fillRect(x-5,y-pn.tilesize,(int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth()+12,pn.tilesize+10);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text,x,y);
        if(numCommand==1)
        {
            g2d.setColor(new Color(0, 255, 153));
            g2d.drawString(">", x-pn.tilesize, y);
        }
        
        text="Quit";
        x=findCenter(text);
        y+=pn.tilesize*2;
        g2d.setColor(new Color(179, 217, 255));
        g2d.fillRect(x-5,y-pn.tilesize,(int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth()+12,pn.tilesize+5);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text,x,y);
        if(numCommand==2)
        {
            g2d.setColor(new Color(0, 255, 153));
            g2d.drawString(">", x-pn.tilesize, y);
        }
    }
    
    public void drawGameOver()
    {
        pn.stopMusic();
        int x=pn.tilesize*5;
        int y=pn.tilesize*2;
        int width=8*pn.tilesize;
        int height=6*pn.tilesize;
        drawSubWindown(x, y, width, height);
        
        g2d.setColor(Color.white);
        g2d.setFont(arial_40);
        String mes="Game Over";
        g2d.drawString(mes,x+width/2-findCenter(mes)/2, y+2*pn.tilesize+5);
        
        g2d.setFont(arial_20);
        g2d.setColor(Color.gray);
        if(numCommand==1)
        {
            g2d.fillRect(x+pn.tilesize-5, y+3*pn.tilesize+10, findCenter("retry")/5, pn.tilesize+5);
        }
        else
        {
            g2d.fillRect(x+pn.tilesize*6-5, y+3*pn.tilesize+10, findCenter("Exit")/6, pn.tilesize+5);
        }
        g2d.setColor(Color.white);
        g2d.drawString("Retry", x+pn.tilesize, y+4*pn.tilesize);
        g2d.drawString("Exit", x+6*pn.tilesize, y+4*pn.tilesize); 
      
    }
    
    public void drawCharacterStatus()
    {
        int x=pn.tilesize;
        int y=pn.tilesize/2;
        int width=pn.tilesize*6;
        int height=pn.tilesize*13;
        
        drawSubWindown(x, y, width, height);
        
        //DRAW INFORMATION
        g2d.setFont(g2d.getFont().deriveFont(20F));
        int valueX=x+10;
        int valueY=y+pn.tilesize;
        int lineY=41;
        g2d.drawString("Level", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Strenth", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Attack", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Dexterity", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Defend", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Coin", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Next Level", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Sword", valueX, valueY);
        valueY+=lineY;
        g2d.drawString("Shield", valueX, valueY);

        
        //DRAW VALUE IN STATUS
        valueX=(x+width)-30;
        valueY=y+pn.tilesize;
        String value;
        value= String.valueOf(pn.player.level);
        g2d.drawString(value, valueX, valueY);
        valueY+=lineY;
        value= String.valueOf(pn.player.streng);
        g2d.drawString(value, valueX, valueY);
        valueY+=lineY;
        value= String.valueOf(pn.player.attack);
        g2d.drawString(value, valueX, valueY);
        valueY+=lineY;
        value= String.valueOf(pn.player.dexterity);
        g2d.drawString(value, valueX, valueY);
        valueY+=lineY;
        value= String.valueOf(pn.player.defen);
        g2d.drawString(value, valueX, valueY);
        valueY+=lineY;
        value= String.valueOf(pn.player.coin);
        g2d.drawString(value, valueX, valueY);
        valueY+=lineY;
        value= String.valueOf(pn.player.nextCurrentLevel);
        int valueXtemp=valueX-20;
        g2d.drawString(pn.player.exp+"/"+value, valueXtemp, valueY);
        valueY+=lineY/3;
        valueX-=15;
        g2d.drawImage(pn.player.currentSword.down1, valueX, valueY,pn.tilesize,pn.tilesize,null);
        valueY+=lineY;
        g2d.drawImage(pn.player.currentSheild.down1, valueX, valueY,pn.tilesize,pn.tilesize,null);
        
    }
    
    public void drawInventory()
    {
        int FrameX=pn.tilesize*10;
        int FrameY=pn.tilesize/2;
        int width=pn.tilesize*colinven;
        int height=pn.tilesize*rowinven;
        
        drawSubWindown(FrameX, FrameY, width, height);
        
        int cellX=FrameX+15;
        int cellY=FrameY+15;
        int slotX=cellX;
        int slotY=cellY;
        
        for(entity item:pn.player.inventory)
        {
            if (item == pn.player.currentSword
                    || item == pn.player.currentSheild) {
                g2d.setColor(Color.orange);
                g2d.setStroke(new BasicStroke(3));
                g2d.fillRoundRect(slotX, slotY, pn.tilesize-1, pn.tilesize, 10, 10);
            }

            g2d.drawImage(item.down1, slotX, slotY,null);
            slotX+=pn.tilesize;
            if(slotX>FrameX+width-pn.tilesize)
            {
                slotX=cellX;
                slotY+=pn.tilesize;
            }
        }
        
        int cursorX=cellX+(pn.tilesize*colnum);
        int cursorY=cellY+(pn.tilesize*rownum);
        int cursorWidth=pn.tilesize;
        int cursorHeight=pn.tilesize;
       
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10,10);

        //DRAW DECRIPTION
        int dFrameX=pn.tilesize*10;
        int dFrameY=pn.tilesize*(rowinven)+pn.tilesize/2;
        int dwidth=pn.tilesize*colinven;
        int dheight=pn.tilesize*(rowinven-2);

        int x=dFrameX+10;
        int y=dFrameY+pn.tilesize;
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(12F));
        int itemIndex=getIndexItem();
        if(itemIndex<pn.player.inventory.size())
        {
            drawSubWindown(dFrameX, dFrameY, dwidth, dheight);
            for (String line : pn.player.inventory.get(itemIndex).description.split("\n")) {
                g2d.drawString(line, x, y);
                y += pn.tilesize;
            } 
        }

    }
    
    public int getIndexItem()
    {
        return colnum+(rownum*5);
    }
    
    public void drawMess(String text)
    {
        message.add(text);
        messageCounter.add(0);
    }
    
    public int findCenter(String a)
    {
        int le=(int)g2d.getFontMetrics().getStringBounds(a, g2d).getWidth();
        le=pn.screenWith/2-le/2;
        return le;
    }
    
    
}
