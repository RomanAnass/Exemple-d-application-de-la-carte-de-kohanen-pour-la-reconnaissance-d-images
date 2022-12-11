
package anas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class recherche {
    
   //dimension de l'image :
   int w , h ;
    
  //langueur et hauteur de l'image :
   double  Width , Height ;
    
  // nombre des images ; 
  int N ; 
  
  double dis[][] ;
  
  // les coordonnées des couleurs des pixels
  double c[][] ;
  
  int l ;

  public recherche(int n ,int Width , int Height ) {
   
    this.N = n ;
    this.Height = Height ;
    this.Width = Width ;
            
      
  }
  
  
   public void distance() throws IOException{
        
        File f ;
        dis = new double[2][N];
        double a , b ;
        BufferedImage Image1 ;
        
        for(int i = 0 ; i < N ; i++){
            
            f = new File("C:\\Users\\user\\Desktop\\anass\\A\\output"+(i+1)+".png");
            Image1 = ImageIO.read(f);
            w = Image1.getWidth();
            h = Image1.getHeight();
           // System.out.println(Math.sqrt(Math.pow(Width - w, 2)+ Math.pow(Height - h, 2)));
            dis[0][i] = Math.sqrt(Math.pow(Width - w, 2)+ Math.pow(Height - h, 2));
          //  System.out.println( dis[0][i]);
            dis[1][i] = i+1 ;
        //    System.out.println(dis[1][i]);
        }
        
        for(int i = 0 ; i < N ; i++){
            
           // System.out.println("dis "+i+""+(int)dis[1][i]);
        }
        
        for(int i = 0 ; i < N - 1 ; i++ ){
            
            
            a = dis[0][i];
             
            
            for(int j = i+1 ; j < N  ; j++){
                
                if( a > dis[0][j]  ){
                    
                    a = dis[0][j] ;
                    dis[0][j] = dis[0][i];
                    dis[0][i] = a ;
                    b = dis[1][j] ;
                    dis[1][j] = dis[1][i];
                    dis[1][i] = b ;
                }
                
            }
            
        }
        
         for(int i = 0 ; i < N ; i++){
            
            //System.out.println("dis "+i+""+(int)dis[1][i]);
        }
        
        
    }
   
    public void colorPixel(double di[][]) throws IOException{
        
        BufferedImage Image2 ;
        
        int d = (int)di[1][0];
        
       /* for(int i = 0 ; i < N ;i++ ){
            
            System.out.println((int)di[1][i]);
            
        }*/
        
        File f = new File("C:\\Users\\user\\Desktop\\anass\\A\\output"+d+".png");
        
        Image2 = ImageIO.read(f);
        
        int w = 0 ;
        this.w =Image2.getWidth();
        this.h = Image2.getHeight();
        this.l = Image2.getWidth()*Image2.getHeight();
        System.out.println("l = "+l+" d = "+d);
        c = new double[l][3]; 
        
        for(int i = 0 ; i < Image2.getWidth() ; i++){
            for(int j = 0 ; j < Image2.getHeight(); j++){
                
                Color c1 = new Color(Image2.getRGB(i, j));
                
                c[w][0] = c1.getRed();
                c[w][1] = c1.getGreen();
                c[w][2] = c1.getBlue();
                w++ ;
            }
            
            
        }
        
        
        
    }

    public double[][] getC() {
        return c;
    }

    public int getL() {
        return l;
    }
    
    
    
}
