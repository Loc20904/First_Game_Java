

package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_Blue_Shield extends entity {

    public OBJ_Blue_Shield(Jpanel pn) {
        super(pn);
        type=type_Sheild;
        name="Diamon Shield";
        defen=2;
        description="["+name+"]"+"\nThe shield make by Diamon can be parry attack";
        down1=setup("/Object/shield_blue", pn.tilesize, pn.tilesize);
    }

}
