
package Monster;

import Pk_Main.Jpanel;
import entity.entity;
import java.util.Random;


public class Slime extends entity {

    public Slime(Jpanel pn) {
        super(pn);
        type=type_Monster;
        name="slime";
        speed=1;
        maxlife=8;
        life=maxlife;
        attack=5;
        defen=0;
        exp=5; //EXP PLAYER RECIVE WHEN KILL THIS MONSTER
        
        soliArea.x=2;
        soliArea.y=10;
        soliAreaDefaultX=soliArea.x;
        soliAreaDefaultY=soliArea.y;
        soliArea.width=32;
        soliArea.height=26;
        
        getImage();
        
    }
    
    public void getImage()
    {
        up1=setup("/Monster/slime_down_1", pn.tilesize,pn.tilesize);
        up2=setup("/Monster/slime_down_2", pn.tilesize,pn.tilesize);
        down1=setup("/Monster/slime_down_1", pn.tilesize,pn.tilesize);
        down2=setup("/Monster/slime_down_2", pn.tilesize,pn.tilesize);
        left1=setup("/Monster/slime_down_1", pn.tilesize,pn.tilesize);
        left2=setup("/Monster/slime_down_2", pn.tilesize,pn.tilesize);
        right1=setup("/Monster/slime_down_1", pn.tilesize,pn.tilesize);
        right2=setup("/Monster/slime_down_2", pn.tilesize,pn.tilesize);        
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
}
