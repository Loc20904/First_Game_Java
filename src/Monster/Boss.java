
package Monster;

import Pk_Main.Jpanel;
import entity.entity;
import java.util.Random;


public class Boss extends entity {

    public Boss(Jpanel pn) {
        super(pn);
        type=type_Monster;
        name="Boss";
        speed=2;
        attack=11;
        defen=6;
        maxlife=22;
        life=maxlife;
        maxMana=30;
        mana=maxMana;
        
        soliArea.x=2;
        soliArea.y=10;
        soliAreaDefaultX=soliArea.x;
        soliAreaDefaultY=soliArea.y;
        soliArea.width=32;
        soliArea.height=36;
        
        getImage();
    }
    
    public void getImage()
    {
        int i=4;
        down1=setup("/Monster/solider_down1", pn.tilesize*i, pn.tilesize*i);
        down2=setup("/Monster/solider_down2", pn.tilesize*i, pn.tilesize*i);
        up1=setup("/Monster/solider_up1", pn.tilesize*i, pn.tilesize*i);
        up2=setup("/Monster/solider_up2", pn.tilesize*i, pn.tilesize*i);
        left1=setup("/Monster/solider_left1", pn.tilesize*i, pn.tilesize*i);
        left2=setup("/Monster/solider_left2", pn.tilesize*i, pn.tilesize*i);
        right1=setup("/Monster/solider_right1", pn.tilesize*i, pn.tilesize*i);
        right2=setup("/Monster/solider_right2", pn.tilesize*i, pn.tilesize*i);
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
