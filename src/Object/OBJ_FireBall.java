

package Object;

import Pk_Main.Jpanel;
import entity.projecttile;


public class OBJ_FireBall extends projecttile
{

    public OBJ_FireBall(Jpanel pn) {
        super(pn);
        name="Fire Ball";
        speed=5;
        life=maxlife;
        attack=3;
        useCost=1;
        alive=false;
        getImage();
    }

    public void getImage()
    {
        up1 = setup("/Object/fireball_up_1", pn.tilesize,pn.tilesize);
        down1 = setup("/Object/fireball_down_1", pn.tilesize,pn.tilesize);
        left1 = setup("/Object/fireball_left_1", pn.tilesize,pn.tilesize);
        right1 = setup("/Object/fireball_right_1", pn.tilesize,pn.tilesize);
        up2 = setup("/Object/fireball_up_2", pn.tilesize,pn.tilesize);
        down2 = setup("/Object/fireball_down_2", pn.tilesize,pn.tilesize);
        left2 = setup("/Object/fireball_left_2", pn.tilesize,pn.tilesize);
        right2 = setup("/Object/fireball_right_2", pn.tilesize,pn.tilesize);
    }
}
