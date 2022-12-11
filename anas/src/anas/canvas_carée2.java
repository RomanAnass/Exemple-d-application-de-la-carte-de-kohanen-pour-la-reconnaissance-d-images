/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


public class canvas_carée2 extends Canvas {
    
    
    
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
    
    
    public canvas_carée2(int a) throws FileNotFoundException, IOException{
        
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
    
    
   
 
  public void paint(Graphics g){
        
        Graphics2D gr = (Graphics2D)g; 
        
     int x , y = 0;
     
    
       
      
      gr.setColor(Color.BLUE);
    //  gr.drawOval(0,0, 50,100);
      //  gr.drawOval(50,0, 50,100);
      int t1 = 0 , k = 0; 
      for(int i = 0 ; i < dimMap ; i++){
         
          x = 0 ;
          
      for(int j = 0 ; j < dimMap; j++){
          
            gr.setColor(Color.BLUE);
             gr.drawRect(x,y, 44,42);
    
             for(int k1= 0 ; k1 < n ; k1++){
                 
                 if(index[k1] == t1 ){
                     
                     gr.setColor(Color.BLUE);
                    gr.fillRect(x,y, 44,42);
                     
                 }
                 
             }
             
             
             t1++ ;
             x+=44 ;
         } 
          
         y+=42;    
          
         // t2++ ;
          
      }
          
          
           
          
      //}
          
          
      }
      
    
}
