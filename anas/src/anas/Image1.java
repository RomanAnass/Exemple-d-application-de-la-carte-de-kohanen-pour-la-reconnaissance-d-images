
package anas;

import static anas.Classification_couleur.sortie_data;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Image1 extends JPanel {
    
    //tableau des données avant normalisées :
   static double data1[][] ;
    Maps SOM ;
    BufferedImage image ;
    //BufferedImage img ;
    //la taille de l'image l = Width*Height
    int l ; 
    
    int Width , Height ;
    
    PrintWriter out = null ; 
    static PrintWriter out1 = null ; 
    static BufferedReader sortie_data = null ; 
     
    static int a_cols = 0 , m_rows = 0 ;

    static StringTokenizer st3;
     
    static String ligne3  ;
    
    public Image1(Maps SOM , int l , int Width , int Height ) throws IOException {
       
        this.SOM = SOM;
        this.l = l ;
        this.Width = Width ;
        this.Height = Height ;
        
        image = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
      /*for(int i = 0 ; i < 10000 ; i++){
          for(int j = 0 ; j < 3 ; j++){
              
              System.out.print(data1[i][j]);
              System.out.print(" ");
          }
          System.out.println();
      }*/
        
        
     
    }
 
    public double calcule(double data[][]){
        
        double som  = 0; 
        
        for(int i = 0 ; i < data.length ; i++){
            
            for(int j = 0 ; j < data[i].length ; j++){
                
                som+=Math.pow(data[i][j],2);
                
                
            }
        }
        
        return Math.sqrt(som);
        
    }
    
    
   public int[] pixel(double SP , double k[]){
       
       int p[] = new int[k.length];
       
       for(int i = 0 ;i < p.length ; i++ ){
           
           p[i] = (int) (SP*k[i]);
           
       }
       
       return p ;
   }
     
    public void paint(Graphics g){
        
        double S ;
        int y1 = 0 ;
        int couleur[] = new int[3] ;
        
        WritableRaster raster = image.getRaster();
       // WritableRaster raster1 = img.getRaster();
        int j = 0 ;
        int t = 0 ;
        try {
            out = new PrintWriter(new FileWriter("couleur1.txt"),true);
           
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        Graphics2D g2 = (Graphics2D)g ; 
        
        Color C = null ; 
        
      
        for(int i = 0 ; i < l ; i++  ){
            
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
          //   if( (SOM.Map[i].Weight_of_Node[2] <= 255 && SOM.Map[i].Weight_of_Node[0] <= 255 && SOM.Map[i].Weight_of_Node[1] <=  255) || (SOM.Map[i].Weight_of_Node[2] >= 0 && SOM.Map[i].Weight_of_Node[0] >= 0 && SOM.Map[i].Weight_of_Node[1] >=  0) ){
                 
                 
                 double d[][] = new double[1][3] ;
                 d[0][0] = data1[i][0] ;
                 d[0][1] = data1[i][1] ;
                 d[0][2] = data1[i][2] ;
                 //System.out.println(data1[i][0]+" "+data1[i][1]+" "+data1[i][2]);
                 S = calcule(d);
                 double d1[] = new double[3]             ;
                 d1[0] =  SOM.Map[i].Weight_of_Node[0]   ;
                 d1[1] =  SOM.Map[i].Weight_of_Node[1]   ;
                 d1[2] =  SOM.Map[i].Weight_of_Node[2]   ;
                 couleur = pixel(S,d1);
                 
                 
                
                 
                 if(couleur[0] <= 255 && couleur[1] <= 255 && couleur[2] <= 255  &&  couleur[0] >= 0 && couleur[1] >= 0 && couleur[2] >= 0  ){
                   //System.out.println(couleur[0]+" "+couleur[1]+"  "+couleur[2] );
                C = new Color(couleur[0],couleur[1],couleur[2]);
              //  System.out.println(C.getRed()+" "+C.getGreen()+" "+C.getBlue());
                int c1[] = {C.getRed(),C.getGreen(),C.getBlue()};
                out.println(C.getRed()+" "+C.getGreen()+" "+C.getBlue());
                raster.setPixel(t,j,c1);
                // raster1.setPixel(t,j,c1);
                //  y1++;  
                
                 }
                 
               
                 
           //  }     
         
      
        
         j++ ;
         
          if(j == this.Width){
             
             j = 0 ;
             t++   ;              
          
          // g.setColor(new Color((float)c[0],(float)c[1],(float)c[2]));
          // g.drawRect(59,50,50,50);
           
          }
       
       
        }
        
        
     
  // System.out.println("y = "+y1);         
   g.drawImage(image,20,20,this);
      

           
    } 
    
    
    public static void main(String[] args) throws IOException {
        
       int Width  = 400 , Height = 400, n = 4 ;
       
       int colonne = 3 , dimMap, max = 40 ;
       File f1 = new File("Image.txt");
       double er = 0.0000001 , tau = 0.5 ;
       Maps som = new Maps();
      
       recherche re = new recherche(n,Width,Height);
       re.distance();
       re.colorPixel(re.dis);
       double data[][];// = new double[re.w][re.h];
       dimMap = re.w ;
       som.Creat_Map(3, f1, dimMap, max, er, tau);
       data1 = new double[re.getL()][3];
       data =  re.getC();
       int k = 0 ;   
        out1 = new PrintWriter(new FileWriter("couleur.txt"),true);
          for(int i = 0 ; i < re.getL() ; i++ ){
             // out1.println(k);
              for(int j = 0 ; j < 3 ; j++ ){
                  
                  data1[i][j] = data[i][j];
                  
                  out1.print(data1[i][j]);
                  out1.print(" ");
              }
              out1.println();
             // k++;
              
          } 
          
       
	      
        Image1 P = new Image1(som,re.getL(),re.w,re.h);
        P.setPreferredSize(new Dimension(500,500));
        JFrame frame = new JFrame("PHOTO");
        frame.getContentPane().setLayout(new GridLayout(1,1));
        frame.getContentPane().add(P);
       // frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
      /*new Thread(){
            
            
             public void run(){
                
               
                 try {
                     som.Train_classification_I(data,re.getL(),3);
                 } catch (IOException ex) {
                     Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
                  
                
                
            }
        }.start();
            
       while(!som.done){
            
            P.repaint();
            
            try{Thread.sleep(1000/30);}catch(Exception e){}
        }*/  
         
           
    }
}

