

package Pk_Main;

import Monster.Boss;
import Monster.Slime;
import Object.OBJ_Blue_Shield;
import Object.OBJ_Potion_red;
import Object.OBJ_axe;
import Object.OBJ_chest;
import Object.OBJ_door;
import Object.OBJ_fight;
import Object.OBJ_gate;
import Object.OBJ_key;
import entity.NPC_Cot;
import entity.NPC_Oldman;
import entity.entity;
import java.awt.Graphics;


public class AssetSetter {

    Jpanel pn;
    
    public AssetSetter(Jpanel pn)
    {      
        this.pn=pn;
        
    }
            
    public void setObject()
    {
       int i=0;
       int nummap=0;
       pn.obj[nummap][i]=new OBJ_Potion_red(pn);
       pn.obj[nummap][i].worldX=33*pn.tilesize;
       pn.obj[nummap][i].worldY=38*pn.tilesize;
       i++;
       if(pn.stage==2)
       {
            pn.obj[nummap][i]=new OBJ_fight(pn);
            pn.obj[nummap][i].worldX=31*pn.tilesize;
            pn.obj[nummap][i].worldY=37*pn.tilesize;
            i++;
            pn.obj[nummap][i]=new OBJ_gate(pn);
            pn.obj[nummap][i].worldX=28*pn.tilesize;
            pn.obj[nummap][i].worldY=34*pn.tilesize;
            i++;
       }
       i=0;
       nummap=1;
    }
    
    public void setNPC()
    {
        int i=0;
        int nummap=0;
        pn.npc[nummap][i]= new NPC_Oldman(pn);               
        pn.npc[nummap][i].worldX=34*pn.tilesize;
        pn.npc[nummap][i].worldY=38*pn.tilesize;
        i++;
//        pn.npc[i]= new NPC_Cot(pn);               
//        pn.npc[i].worldX=36*pn.tilesize;
//        pn.npc[i].worldY=37*pn.tilesize;
//        i++;

        nummap=1;
        
        
    }
    public void setMonster()
    {
        int i=0;
        int nummap=0;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=30*pn.tilesize;
        pn.monster[nummap][i].worldY=7*pn.tilesize;
        i++;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=40*pn.tilesize;
        pn.monster[nummap][i].worldY=5*pn.tilesize;
        i++;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=29*pn.tilesize;
        pn.monster[nummap][i].worldY=15*pn.tilesize;
        i++;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=29*pn.tilesize;
        pn.monster[nummap][i].worldY=37*pn.tilesize;
        i++;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=25*pn.tilesize;
        pn.monster[nummap][i].worldY=39*pn.tilesize;
        i++;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=22*pn.tilesize;
        pn.monster[nummap][i].worldY=9*pn.tilesize;
        i++;
        pn.monster[nummap][i]=new Slime(pn);
        pn.monster[nummap][i].worldX=40*pn.tilesize;
        pn.monster[nummap][i].worldY=15*pn.tilesize;
        i++;
        
        i=0;
        nummap=1;
        pn.monster[nummap][i]=new Boss(pn);
        pn.monster[nummap][i].worldX=22*pn.tilesize;
        pn.monster[nummap][i].worldY=22*pn.tilesize;
        i++;   
    }
}
