

package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_Sword_normal extends entity {

    public OBJ_Sword_normal(Jpanel pn) {
        super(pn);
        type=type_Sword;
        name="normalSword";
        down1=setup("/Object/sword_normal", pn.tilesize, pn.tilesize);
        streng=1;
        description="["+name+"]"+"\nOld sword make by metal";
        attackArea.width=23;
        attackArea.height=23;
    }

}
