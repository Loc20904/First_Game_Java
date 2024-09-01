
package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_Shiled_normal extends entity {

    public OBJ_Shiled_normal(Jpanel pn) {
        super(pn);
        type=type_Sheild;
        name="shieldWood";
        down1=setup("/Object/shield_wood", pn.tilesize, pn.tilesize);
        defen=1;
        description="["+name+"]"+"\nShield make by wood";
    }

}
