
package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_gate extends entity {

    public OBJ_gate(Jpanel pn) {
        super(pn);
        name="gate";
        down1=setup("/Object/gate", pn.tilesize, pn.tilesize);    
    }

}
