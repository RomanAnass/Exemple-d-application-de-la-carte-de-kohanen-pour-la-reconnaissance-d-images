

package anas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Classification_couleur extends JPanel  {
    
    Maps som ;
    //longueur et largueur des neurones de kohanen : 
    int nodeSize ;
    int IconSize ; 
    boolean drawLine ; 
    boolean drawIcon ;
    
    //le nombre des vecteurs d'entrées ; 
    int n = 8 ; 
    
    static BufferedReader sortie_data = null ; 
     
    static int a_cols = 0 , m_rows = 0 ;
     
    static String data1  = "test" ; 

    static StringTokenizer st3;
     
    static String ligne3  ; 
    
    public Classification_couleur(Maps Som ,int nodesize , int iconsize , boolean drawline , boolean drawicon ){
        
        this.som = Som ;
        this.nodeSize = nodesize ;
        this.IconSize = iconsize ; 
        this.drawLine = drawline ;
        this.drawIcon = drawicon ;
        
        
    }  
    
    
   public void paint(Graphics g){
       
       Graphics g2 = (Graphics2D)g;
       super.paint(g2);
       //number_of_node 
  /* int i1 = 0 , j =0 ;
    for (int x= 0; x < som.dimMap; x++) {

        // for (int y= 0; y < som.dimMap; y++) {
 
           g2.setColor(new Color((float)som.Map[x].Weight_of_Node[0],(float)som.Map[x].Weight_of_Node[1],(float)som.Map[x].Weight_of_Node[2]));
         //  g2.setColor(Color.red);
         if(j <= 100 ){
     
            j++;
         }
           

         else if(i1<=100){
             i1++;
             j= 0 ;
            
        }
    
          g2.fillRect(i1*nodeSize, j*nodeSize, nodeSize, nodeSize); 
          } */
      int w = 0 ; 
      for (int x= 0; x < som.dimMap; x++) {

            for (int y= 0; y < som.dimMap; y++) {
                    
                g2.setColor(new Color((float)som.Map[w].Weight_of_Node[0],(float)som.Map[w].Weight_of_Node[1],(float)som.Map[w].Weight_of_Node[2]));
                g2.fillRect(x*nodeSize, y*nodeSize, nodeSize, nodeSize);
            w++;
            }
        }
            
        

    if(drawLine){
        
        g2.setColor(Color.BLACK);
        
        for(int i = 0 ; i<= som.dimMap ; i++){
            
            g2.drawLine(i*nodeSize,0, i*nodeSize, som.dimMap*nodeSize);
            g2.drawLine(0, i*nodeSize, som.dimMap*nodeSize, i*nodeSize);
            
        }
        
    }
    
   
    
       
   }
   
   
   
    public static void main(String[] args) throws IOException{
        
       double data[][] = new double [8][3] ;// {{0,0,0},{0,0,1},{0,1,0},{0,1,1},{1,0,0},{1,0,1},{1,1,0},{1,1,1}}; 
        Maps som = new Maps();
        double er = 0.000001 , tau = 1;
        int  dimMap =100 , max = 300 ;
        File f = new File("do1.txt");
         int frameSize = 800; // the size of the entire grid as it will appear on screen in pixels
     //   int gridSize = 100;  // i.e. if 100 it will generate 100x100 grid
        
        int nodeSize = frameSize/dimMap; // the size of each node as it will appear on screen in pixels
        int iconSize = 24; // the size of the icons as they will appear on screen in pixels
        boolean drawLines = true; // draw lines on grid?
        boolean drawIcons = true; // draw data points icons?
        
        sortie_data = new BufferedReader(new FileReader("test.txt"));
	          
	             while((ligne3=sortie_data.readLine())!=null){
                         
	           a_cols=0 ; 
                   
	           st3=new StringTokenizer(ligne3);
                           
	           while(st3.hasMoreTokens()){
	            
	            data[m_rows][a_cols] = Double.valueOf(st3.nextToken()).doubleValue();
	           
	            a_cols++;
                    
	           }
                   
	         
	          m_rows++;
	           
	       } 
                     
        som.Creat_Map(3, f, dimMap, max, er, tau);
        Classification_couleur c = new Classification_couleur(som,nodeSize,iconSize,drawLines,drawIcons);
       
        /***********/
        
        c.setPreferredSize(new Dimension(frameSize+1, frameSize+1));
        
        JFrame frame = new JFrame("SOM Color Demo");
        frame.getContentPane().setLayout(new GridLayout(1,1));
        frame.getContentPane().add(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
         new Thread(){
            
            public void run(){
                
                try {
                    som.Train_classification_C(data,8,3);
                } catch (IOException ex) {
                    Logger.getLogger(Classification_couleur.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
                
                
            }
        }.start();
        
        while(!som.done){
            
            c.repaint();
            
            try{Thread.sleep(1000/30);}catch(Exception e){}
        }
}
}
