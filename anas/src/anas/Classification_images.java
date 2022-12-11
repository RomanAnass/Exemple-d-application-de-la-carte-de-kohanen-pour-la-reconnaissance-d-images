
package anas;

import static anas.Classification_images.Img;
import static anas.Image1.data1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Classification_images extends JPanel {
    
   
// hauteur et largeur de l'image
   static int  Width , Height ;
    
//tableau des coordonnées des couleurs des images 
  static double Img[][];   
   
//tableau des coordonnées des couleurs des images 
    static double data[][] ;
    
// nombre des images
   static int N; 
    
static  int b = 0 ;  
   
    Maps SOM ;
    
    BufferedImage image ;
   
    int colonne ;
    
    JLabel l ;
    
    PrintWriter out ;
    
    static int n  ;
  
    
    public Classification_images(int n , Maps som, int Width , int Height ) throws IOException{
         
        this.N = n ;
        this.SOM = som ; 
        this.Width = Width ;
        this.Height = Height ;
        image = new BufferedImage(Width ,Height,BufferedImage.TYPE_INT_RGB);
        System.out.println("dddddddddddddddd");
         
       // l= new JLabel(new ImageIcon(image));
    }// fin constructor
    
 /*   public void Coordonnée() throws IOException{
        
        for(int i = 0 ; i < N ; i++ ){
            
            f = new File("C:\\Users\\user\\Desktop\\hamza\\output"+i+".png");
            
            image = ImageIO.read(f);
            
            this.Width = image.getWidth();
            this.Height = image.getHeight();
            
            int t = 0 ; 
            
            for(int j = 0 ; j < Width ; j++){
                for(int k = 0 ; k < Height ; k++){
                    
                    Color c = new Color(image.getRGB(j,k));
                    Img[i][t]= c.getGreen() ;
                    Img[i][t+1] = c.getRed();
                    Img[i][t+2] = c.getBlue();
                    t=t+3 ;
                    
                }// fin k ;
                
            }// fin j ;
            
        }//fin i ;
        
        
        
    }//fin fonction coordonnée*/ 
    
    
    public double calcule(double data[]){
        
        double som  = 0; 
        
        for(int i = 0 ; i < data.length ; i++){
            
           
                som+=Math.pow(data[i],2);
                
           
       
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
           
       super.paint(g);
       
       double S ;
             
       int couleur[] = new int[70*70*3] ;
        
       Graphics2D g2 = (Graphics2D)g;
        
       g.setColor(Color.WHITE);
       
       
       
       WritableRaster raster = image.getRaster();
       
        try {
            out = new PrintWriter(new FileWriter("couleur1.txt"),true);
           
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       int j = 50 , k = 50 , r = 0 ;
       Color C ;
     
        double d[] = new double[70*70*3];
        
        double d1[] = new double[70*70*3];
        
        for(int i = 0 ; i < n ; i++){
           
            for(int s = 0 ; s < 70*70*3 ; s++){
               
                d[s] = data[i][s] ;
                d1[s] = SOM.ner1[i].Weight_of_Node[s];
                
           }
           
          
           S = calcule(d);
           couleur = pixel(S,d1);
           
           int t = 0 ;
           
            out.println("ligne"+(i+1));
           
           
           for(int x = 0 ; x < Width ; x++ ){
               
               
               
               for(int y = 0 ; y < Height ; y++){
                   
                   
                    out.println(couleur[t]+" "+couleur[t+1]+" "+couleur[t+2]);
                    //    System.out.println(couleur[t]+" "+couleur[t+1]+" "+couleur[t+2]);
                    t+=3 ;
               }  
           
           
           }
         
           //dessination de l'image 
          /* int c[] = new int[3];
           int t = 0 ;
           
           for(int x = 0 ; x < Width ; x++ ){
               
               for(int y = 0 ; y < Height ; y++){
                 
                    for(int m = 0 ; m < 3 ; m++ ){
               
                      c[m] = couleur[t]; 
                      t++ ;
               
           }
                    
                    raster.setPixel(x, y, c);
                   
                   
               }
               
               
           } 
       
      
          g.drawImage(image,j,k , this);
          r++ ;
          j+=80 ;
          if(r == 3 ){
              
              r = 0 ;
              j = 50 ;
              k+= 80 ; 
              
              
          }*/
           
       }
      
        
    }
    
    public static void main(String []args) throws IOException{
        
        File f ;
        
        PrintWriter out1 = null ;
        
        BufferedImage image ;
        
        Maps som  = new Maps();
        
        n = 2 ;
        
        int Width = 70 , Height = 70 ;
        
        int colonne = 70*70*3 , dimMap = 10 , max = 50 ;
        
        double er = 0.00001 , tau = 2 ;
                
        
        File f1 = new File("Don3");
        
        Img = new double[100][70*70*3] ;  
        data = new double[100][70*70*3] ; 
        
        out1 = new PrintWriter(new FileWriter("couleur.txt"),true);
        
         for(int i = 1 ; i <= n ; i++ ){
            // System.out.println(i);
           
            f = new File("C:\\Users\\user\\Desktop\\a\\output"+i+".png");
            
            image = ImageIO.read(f);
            
            Width = image.getWidth();
            Height = image.getHeight();
             //System.out.println(i);
             //System.out.println(Width+" "+ Height);
            int t = 0 ; 
      
            
            
             out1.println("ligne"+i);
            
            for(int j = 0 ; j < Width ; j++){
                
               
                for(int k = 0 ; k < Height ; k++){
                    
                    Color c1 = new Color(image.getRGB(j,k));
                    
                   
                      
                    Img[i-1][t]   =    c1.getRed()     ;
                    Img[i-1][t+1] =    c1.getGreen()   ;
                    Img[i-1][t+2] =    c1.getBlue()    ;
                    data[i-1][t]  =    c1.getRed()     ;
                    data[i-1][t+1] =   c1.getGreen()   ;
                    data[i-1][t+2] =   c1.getBlue()    ;
                    
                    out1.println(data[i-1][t]+" "+data[i-1][t+1]+" "+data[i-1][t+2]+" "); 
        
                    t=t+3 ;  
                        
                   
                    
                   
                    
                }// fin k ;
                
               
                
            }// fin j ;
            out1.println();
             out1.println();
              out1.println();
               out1.println();
                out1.println();
                 out1.println();
                  out1.println();
                   out1.println();
                    out1.println();
        }//fin i ;
        
         som.Creat_Map(colonne, f1, dimMap, max, er, tau);
         b = 1 ;
         Classification_images c = new Classification_images(n,som,Width,Height);
        
         int frameSize = 400; 
        JFrame frame = new JFrame("SOM Image Demo");
        c.setPreferredSize(new Dimension(frameSize+1, frameSize+1));
        frame.getContentPane().setLayout(new GridLayout(1,1));
        frame.getContentPane().add(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        
      /*  new Thread(){
            
            public void run(){
                
              //  try {
                  //  som.Train_classification_I(Img,n,70*70*3);
                //} catch (IOException ex) {
                    Logger.getLogger(Classification_images.class.getName()).log(Level.SEVERE, null, ex);
               // }
                
                
            }
        }.start();
        //
          while(!som.done){
            
            c.repaint();
            
            try{Thread.sleep(1000/30);}catch(Exception e){}
        }*/
        
         
         
    }
    
}
