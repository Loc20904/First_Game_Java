
package Pk_Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class configGame {

    public Jpanel pn;
    
    public String fileData;
    
    public configGame(Jpanel pn)
    {
        this.pn=pn;
        fileData="dataGame.txt";
    }
    
    public void saveData()
    {
        pn.player.save();
        for(int i=0;i<pn.monster.length;i++)
        {
            if(pn.monster[i]!=null)
            {
                pn.monster[pn.currentMap][i].save();
            }
        }
        for(int i=0;i<pn.npc.length;i++)
        {
            if(pn.obj[i]!=null)
            {
                pn.obj[pn.currentMap][i].save();
            }
        }
    }
    
    public void loadData()
    {
        
    }
}
