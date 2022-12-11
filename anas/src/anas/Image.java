
package anas;

import static anas.Classification_couleur.sortie_data;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Image extends JPanel {
    
      double data1[][] = new double[10000][3] ;
    Maps SOM ;
    BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
    
    PrintWriter out = null ; 
   
    static BufferedReader sortie_data = null ; 
     
    static int a_cols = 0 , m_rows = 0 ;

    static StringTokenizer st3;
     
    static String ligne3  ; 

    public Image(Maps SOM,double data1[][]  ) throws IOException {
       
        this.SOM = SOM;
        this.data1 = data1 ;
        
     
    }
     
     
    public void paint(Graphics g){
        
        WritableRaster raster = image.getRaster();
        int j = 0 ;
        int t = 0 ;
        try {
            out = new PrintWriter(new FileWriter("couleur1.txt"),true);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        Graphics2D g2 = (Graphics2D)g ; 
        
        Color C = null ;
      
        for(int i = 0 ; i < 10000 ; i++  ){
            
          float  c[] = {(float)SOM.Map[i].Weight_of_Node[0],(float)SOM.Map[i].Weight_of_Node[1],(float)SOM.Map[i].Weight_of_Node[2]};
         out.println(i);
         out.println(SOM.Map[i].Weight_of_Node[0]);
         out.println(SOM.Map[i].Weight_of_Node[1]);
         out.println(SOM.Map[i].Weight_of_Node[2]);    
         
       /* if(SOM.Map[i].Weight_of_Node[0] > 1 )
             
                System.out.println(SOM.Map[i].Weight_of_Node[0]);
         
         if(SOM.Map[i].Weight_of_Node[1] > 1 )
             
              System.out.println(SOM.Map[i].Weight_of_Node[1]);
         
         if( SOM.Map[i].Weight_of_Node[2] > 1 )
                 
              System.out.println(SOM.Map[i].Weight_of_Node[2]);*/
             if( SOM.Map[i].Weight_of_Node[2] < 1 && SOM.Map[i].Weight_of_Node[0] < 1 && SOM.Map[i].Weight_of_Node[1] <  1 ){
                 
                   C = new Color((float)SOM.Map[i].Weight_of_Node[0],(float)SOM.Map[i].Weight_of_Node[1],(float)SOM.Map[i].Weight_of_Node[2]);
                   int c1[] = {C.getRed(),C.getGreen(),C.getBlue()};
                   out.println(C.getRed()+" "+C.getGreen()+" "+C.getBlue());
                   raster.setPixel(t, j,c1);
             }     
         
      
        
         j++ ;
         
          if(j == 100){
             
             j = 0 ;
             t++   ;              
          
          // g.setColor(new Color((float)c[0],(float)c[1],(float)c[2]));
          // g.drawRect(59,50,50,50);
           
          }
         
       }
        
   g.drawImage(image,300,300,this);
     

           
    } 
    
    
    public static void main(String[] args) throws IOException {
        
       double data[][] = new double[10000][3] ;
       int colonne = 3 , dimMap = 100 , max = 50 ;
       File f1 = new File("Image.txt");
       double er = 0.00001 , tau = 2 ;
       Maps som = new Maps();
       som.Creat_Map(3, f1, dimMap, max, er, tau);
       
       sortie_data = new BufferedReader(new FileReader("couleur.txt"));
	          
	           while((ligne3=sortie_data.readLine())!=null){
                         
	           a_cols=0 ; 
                   
	           st3=new StringTokenizer(ligne3);
                           
	           while(st3.hasMoreTokens()){
	            
	            data[m_rows][a_cols] = Double.valueOf(st3.nextToken()).doubleValue();
	           
	            a_cols++;
                    
	           }
                   
	         
	          m_rows++;
	           
	       } 
        Image P = new Image(som,data);
        P.setPreferredSize(new Dimension(500,500));
        JFrame frame = new JFrame("PHOTO");
        frame.getContentPane().setLayout(new GridLayout(1,1));
        frame.getContentPane().add(P);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
      new Thread(){
            
            
             public void run(){
                
               
                 try {
                     som.Train_classification(data,10000,3);
                 } catch (IOException ex) {
                     Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
                  
                
                
            }
        }.start();
            
       while(!som.done){
            
            P.repaint();
            
            try{Thread.sleep(1000/30);}catch(Exception e){}
        }  
         
           
    }
}
