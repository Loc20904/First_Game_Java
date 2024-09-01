

package Pk_Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


public class keyHandler implements KeyListener{
    public boolean keyUp, keyDown, keyLeft, keyright, keyenter, keyShoot;
    public boolean checkTime=false;
    public boolean messCheckFrom;
    int count=0;
    Jpanel pn;
    
    public keyHandler(Jpanel pn)
    {
        this.pn=pn;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code =e.getKeyCode();
        //GAME TITLE
        if(pn.state==pn.gameTitle)
        {
            if(code == KeyEvent.VK_W)
            {
                if(pn.ui.numCommand>0)
                    pn.ui.numCommand--;
            }
            else if(code == KeyEvent.VK_S)
            {
                if(pn.ui.numCommand<2)
                    pn.ui.numCommand++;
            }
            if(code == KeyEvent.VK_ENTER)
            {
                if(pn.ui.numCommand==0)
                {
                    
                    pn.currentMap=0;
                    pn.restart();
                    pn.state=pn.gamePlay;
                    pn.stopMusic();
                    pn.playMusic(0);
                    
                }
                else if(pn.ui.numCommand==1)
                {
                    pn.state=pn.guide;
                }
                else if(pn.ui.numCommand==2)
                {
                    System.exit(0);
                }
            }    
        }
        //GUIDE
        else if(pn.state==pn.guide)
        {
            if(code == KeyEvent.VK_ESCAPE)            
            {
                pn.state=pn.gameTitle;
            }
        }
        //GAME PLAY
        else if(pn.state==pn.gamePlay)
        {
            if(code == KeyEvent.VK_W)
            {
                keyUp=true;
            }
            else if(code == KeyEvent.VK_S)
            {
                keyDown=true;
            }
            else if(code == KeyEvent.VK_A)
            {
                keyLeft=true;
            }
            else if(code == KeyEvent.VK_D)
            {
                keyright=true;
            }
            //PLAYER STATUS
            else if(code == KeyEvent.VK_I)
            {
                pn.state=pn.playerStatus;  
            }
            //CHECK EFFECTIVE
            else if(code == KeyEvent.VK_E)
            {
                if(count==0)
                {
                    checkTime=true;
                    count++;
                }
                else
                {
                    checkTime=false;
                    count--;
                }
            }
            else if(code == KeyEvent.VK_F)
            {
                keyShoot=true;
            }
            else if(code == KeyEvent.VK_M)
            {
                pn.stage=2;
            }
            //GAME PAUSE
            else if(code == KeyEvent.VK_P)
            {
                pn.state=pn.gamePause;
            }
            if(code == KeyEvent.VK_ENTER)
            {
                keyenter=true;
            }
            if(code == KeyEvent.VK_L)
            {
                pn.stage=2;
                pn.ast.setObject();
            }
        }
        else if(pn.state==pn.playerStatus)
        {
            if(code == KeyEvent.VK_W)
            {
                pn.ui.rownum--;
                if(pn.ui.rownum<0)
                    pn.ui.rownum=0;
            }
            else if(code == KeyEvent.VK_S)
            {
                if(pn.ui.rownum<pn.ui.rowinven-2)
                {
                    pn.ui.rownum++;
                }
            }
            else if(code == KeyEvent.VK_A)
            {
                pn.ui.colnum--;
                if(pn.ui.colnum<0)
                    pn.ui.colnum=0;
            }
            else if(code == KeyEvent.VK_D)
            {
                if(pn.ui.colnum<pn.ui.colinven-2)
                {
                    pn.ui.colnum++;
                }
            }
            else if(code == KeyEvent.VK_I)
            {
                pn.state=pn.gamePlay;
            }
            else if(code == KeyEvent.VK_ENTER)
            {
                pn.player.selectItem();
            }
        }
        //PAUSE GAME
        else if(pn.state==pn.gamePause)
        {
            if(pn.state==pn.gamePause && code == KeyEvent.VK_P)
            {
                pn.state=pn.gamePlay;
            }
            
        }
        //GAME DINALOG
        else if(pn.state==pn.gameDinalogMess)
        {
            if(code == KeyEvent.VK_ENTER)
            {   
                if(messCheckFrom)
                    pn.state=pn.gamePlay;
                else
                    keyenter=true;
            }
        }
        //GAME OVER
        else if(pn.state==pn.gameOver)
        {
            if(code == KeyEvent.VK_A)
            {
                pn.ui.numCommand=1;
            }
            else if(code == KeyEvent.VK_D)
            {
                pn.ui.numCommand=2;
            }
            else if(code==KeyEvent.VK_ENTER)
            {
                if(pn.ui.numCommand==1)
                {
                    pn.currentMap=0;
                    pn.restart();
                    pn.state=pn.gamePlay;
                    pn.stopMusic();
                    pn.playMusic(0);
                }
                else
                {
                    pn.state=pn.gameTitle;
                    pn.restart();
                    pn.stopMusic();
                    pn.playMusic(4);
                }
            }
        }
        else if(pn.state==pn.finish)
        {
            if(code==KeyEvent.VK_ENTER)
            {
                pn.state = pn.gameTitle;
                pn.restart();
                pn.stopMusic();
                pn.playMusic(4);
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code =e.getKeyCode();
        if(code == KeyEvent.VK_W)
        {
            keyUp=false;
        }
        else if(code == KeyEvent.VK_S)
        {
            keyDown=false;
        }
        else if(code == KeyEvent.VK_A)
        {
            keyLeft=false;
        }
        else if(code == KeyEvent.VK_D)
        {
            keyright=false;
        }
        else if (code == KeyEvent.VK_F) {
            keyShoot = false;
        }
        
    }

}
