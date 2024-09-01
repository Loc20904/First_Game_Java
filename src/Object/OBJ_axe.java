

package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_axe extends entity {

    public OBJ_axe(Jpanel pn) {
        super(pn);
        type=type_axe;
        name="Axe";
        description="["+name+"]"+"\nCan use to cut tree.";
        down1=setup("/Object/axe", pn.tilesize, pn.tilesize);
        attackArea.height=18;
        attackArea.width=18;
    }

}
