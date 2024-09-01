    

package entity;

import Object.OBJ_Blue_Shield;
import Pk_Main.Jpanel;
import java.util.Random;


public class NPC_Oldman extends entity {
    
    public NPC_Oldman(Jpanel pn) {
        super(pn);
        diredtion="down";
        speed=1;
        soliArea.x=0;
        soliArea.y=10;
        soliAreaDefaultX=soliArea.x;
        soliAreaDefaultY=soliArea.y;
        soliArea.width=36;
        soliArea.height=26;
        setImage();
        setMess();
    }

    public void setImage()
    {
        up1 = setup("/npc/oldman_up_1", pn.tilesize,pn.tilesize);
        down1 = setup("/npc/oldman_down_1", pn.tilesize,pn.tilesize);
        left1 = setup("/npc/oldman_left_1", pn.tilesize,pn.tilesize);
        right1 = setup("/npc/oldman_right_1", pn.tilesize,pn.tilesize);
        up2 = setup("/npc/oldman_up_2", pn.tilesize,pn.tilesize);
        down2 = setup("/npc/oldman_down_2", pn.tilesize,pn.tilesize);
        left2 = setup("/npc/oldman_left_2", pn.tilesize,pn.tilesize);
        right2 = setup("/npc/oldman_right_2", pn.tilesize,pn.tilesize);
    }
       
    @Override
    public void setMess()
    {
        if(pn.stage==1)
        {
            dialogMes[0]="Hello, I'm oldMan ";
            dialogMes[1]="Wellcome to Lo Voi";
            dialogMes[2]="Monster had invading this village";
            dialogMes[3]="Please, help my village";
            dialogMes[4]="Let clear monster in my Village";
            dialogMes[5]="Good luck on your journey";    
        }
        else
        {
            dialogMes[0]="Good job heros";
            dialogMes[1]="You save my village";
            dialogMes[2]="But, DarkHero is strongest monster in this land"; 
            dialogMes[3]="Be carefull, you can practice at object Sword in near river";
            dialogMes[4]="You can hunting monster in this Village to be stronger"; 
            dialogMes[5]="When you are strong enough, then touch the Gate to teleport to DarkHero";
            dialogMes[6]="This is Diamon Shiled, i hope it can support you";
            dialogMes[7]="Good luck on your journey";    
        }
            
    }
    
    @Override
    public void setAction()
    {
        if(counterLockOn==120)
        {
            Random rd = new Random();
            int num = rd.nextInt() % 4 + 1;
            switch (num) {
                case 1:
                    diredtion = "up";
                    break;
                case 2:
                    diredtion = "down";
                    break;
                case 3:
                    diredtion = "left";
                    break;
                case 4:
                    diredtion = "right";
                    break;
            }
            counterLockOn=0;
        }
    }
    
    @Override
    public void speek()
    {
        if(dialogMes[messIndex]==null)
        {
            pn.state=pn.gamePlay;
            messIndex=0;
            if(pn.stage==2)
            {
                entity shield=new OBJ_Blue_Shield(pn);
                shield.worldX=worldX;
                shield.worldY=worldY;
                for(int i=0;i<pn.obj[pn.currentMap].length;i++)
                {
                    if(pn.obj[pn.currentMap][i]==null)
                    {
                        pn.obj[pn.currentMap][i]=shield;
                        break;
                    }
                        
                }  
            }
        }
        pn.ui.dialogMess=dialogMes[messIndex];
        messIndex++;
        switch(pn.player.diredtion)
        {
            case "up":diredtion="down";break;
            case "down":diredtion="up";break;
            case "left":diredtion="right";break;
            case "right":diredtion="left";     
        }
    }
    
}
