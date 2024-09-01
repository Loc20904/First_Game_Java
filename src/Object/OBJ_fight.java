
package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_fight extends entity{

    public OBJ_fight(Jpanel pn) {
        super(pn);
        name="fight";
        down1=setup("/Object/fight", pn.tilesize, pn.tilesize);
    }
    
}
