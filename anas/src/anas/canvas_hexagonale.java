
package anas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JPanel;


public class canvas_hexagonale extends Canvas{
    
    int nombre_de_node  ;
    int dimMap ;
    double [][]matrix_input=new double[256][2]; 
    int rows_data=0 , cols_data = 0;
    int a_cols = 0 , m_rows = 0;
    BufferedReader sortie_position = null ;
  //  int rows = 0 , cols = 0 ;
    StringTokenizer str;
    
    String ligne ;
    public double []data=new double[50] ;
    public int []data1=new int[50] ;
    int index[];
    int n = 0;
    int i = 0 ;
    
    int y1=20,y2=0,y3=0,y4=20,y5=40,y6=40;
    int x1= 0 , x2 = 20 , x3 = 40 , x4=60 ,x5 = x3 , x6= x2 ;
    int a = x1 ;
    int WIDTH = 6 ;
   // int bas[] = {y1,y2,y3,y4,y5,y6};
   // int haut[] = {y1,y2,y3,y4,y5,y6};
   
    int xPoints[] = new int[6];
    int yPoints[] = new int[6];
    
    public canvas_hexagonale(int a) throws FileNotFoundException, IOException{
        
        dimMap = a ;
        nombre_de_node = dimMap*dimMap ;
        sortie_position=new BufferedReader(new FileReader("data.txt"));   
        
        while((ligne=sortie_position.readLine())!=null){
	           
	           str=new StringTokenizer(ligne);
	           while(str.hasMoreTokens()){
	           
	           data[i]=Double.valueOf(str.nextToken()).doubleValue();
	           i++;
	          
	           }
	          n=i/2;
	           
	       }
        
        
         index = new int[n];
       
        for(int j = 0 ; j < i ; j++ ){
            
            data1[j] = (int)data[j];
            
        }
        
        
         int t=0,k1=0 ;
        
    for(int k = 0 ;k < dimMap ; k++){
        
        for(int j = 0 ; j < dimMap ; j++){
            
            for(int j1= 0 ; j1 < i-1 ; j1+=2 ){
               
             if(k==data1[j1]){
                 
                 if(data1[j1+1]==j){
                     
                     System.out.println(k+"\t"+j);
                     
                     index[k1] = t ;
                     k1++ ;
                 }
          
            
        }
             
         
        
    } 
            
    t++ ;
    
   }
        
    }
     
 for(int j = 0 ; j < n ; j++){
        
        System.out.println(index[j]);
        
  
} 
 
 
    
    }
    
    @Override
    public void paint(Graphics g){
        
         Graphics2D gr = (Graphics2D)g;
         
         gr.setColor(Color.blue);
         
         
        int t1 = 0 ; 
        int a = 0 ;
        
       for(int i = 1 ; i <= 10 ; i++){
           
         xPoints[0] = x1 ;
         xPoints[1] = x2 ;
         xPoints[2] = x3 ;
         xPoints[3] = x4 ;
         xPoints[4] = x5 ;
         xPoints[5] = x6 ;
         
         yPoints[0] = y1 ;
         yPoints[1] = y2 ;
         yPoints[2] = y3 ;
         yPoints[3] = y4 ;
         yPoints[4] = y5 ;
         yPoints[5] = y6 ;
           
         
       
       gr.drawPolygon(xPoints, yPoints, WIDTH);
       
       for(int k1= 0 ; k1 < n ; k1++){
                 
                 if(index[k1] == t1 ){
                 
                     gr.fillPolygon(xPoints, yPoints, WIDTH);
                    
                     
                 }
                 
       }
       
        t1++;
        
           for(int j = 1 ; j < 10 ; j++){
               
               if(j%2 != 0 ){
                    
                   
                   
                   for(int k = 0 ; k < 6 ; k++ ){
                       
                       xPoints[k] += 40 ; 
                       yPoints[k] += 20 ;
                       
                   }
                   
                   gr.drawPolygon(xPoints, yPoints, WIDTH);
                   
                   for(int k1= 0 ; k1 < n ; k1++){
                 
                 if(index[k1] == t1){
                 
                     gr.fillPolygon(xPoints, yPoints, WIDTH);
                     
                     
                 }
                   
                   }
                //   t1++ ;
                   
               }else{
                   
                   for(int k = 0 ; k < 6 ; k++ ){
                       
                       xPoints[k] += 40 ; 
                       yPoints[k] -= 20 ; 
                   }
                   
                   gr.drawPolygon(xPoints, yPoints, WIDTH);
                   
                    for(int k1= 0 ; k1 < n ; k1++){
                 
                 if(index[k1] == t1){
                 
                     gr.fillPolygon(xPoints, yPoints, WIDTH);
                 
                 }
                   
                   }
                    
                    //t1++;
               }
               
               t1++ ;
               
           }

           y1+=40  ;
           y2 = y6 ;
           y3 = y5 ;
           y4 = y1 ; 
           y5 = y1+20 ; 
           y6 = y5 ;
           //t1++;
          
           
       }
         
   //    gr.drawPolygon(bas, haut, WIDTH);
   
   
        
       
         
      
    }
    
}
