
package Pk_Main;


import javax.swing.JFrame;


public class Main {
    
    public static void main(String[] args) {
        //Create FRAME
        JFrame windown=new JFrame();
        windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windown.setResizable(false);
        windown.setTitle("Game 1");
        
        //Create Panel
        Jpanel panel=new Jpanel();
        windown.add(panel);
        windown.pack();        
        panel.threadGameStart();
               
        panel.setupGame();
        windown.setLocationRelativeTo(null);
        windown.setVisible(true);
                
    }
}
