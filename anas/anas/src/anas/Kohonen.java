/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anas;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

import anas.Maps;

public class Kohonen {
   

    public double []data=new double[50];String ligne;
    public int n=0;
    Maps som =new Maps();
    int []res=null;
   
   File file=new File("Node.txt"); // contient les nodes vinuer
   File file2=new File("Don.txt");// contient les poids
   double [][]matrix_input=new double[256][256];
   String ligne2,ligne3;
   double[][]poids=new double[50][50];
   BufferedReader sortie_position=null;
    BufferedReader sortie_poids=null;
    BufferedReader sortie_data=null;
   StringTokenizer st;
   StringTokenizer st2;
    StringTokenizer st3;
    int k=0,l;
    int a_cols=0,m_rows=0,cols_data=0,rows_data=0;
 
    
     // fonction principale d apprentissage (matrice )
    public void Tm(int dim,int ite,double err,double tau,String data1) throws IOException{
	      
	        
	  //  som.appretissage(matr);
            //la le probleme 
	     
	      sortie_data=new BufferedReader(new FileReader(data1+".txt"));
	          while((ligne3=sortie_data.readLine())!=null){
	           a_cols=0; 
	           st3=new StringTokenizer(ligne3);
	           while(st3.hasMoreTokens()){
	           
	           matrix_input[m_rows][a_cols]=Double.valueOf(st3.nextToken()).doubleValue();
	           
	            a_cols++;
	           }
	           System.out.println("nombre ligne"+rows_data);
	          m_rows++;
	           rows_data=m_rows;
	           cols_data=a_cols;  
	       }
                  //    rows_data =100 ;




	/* double[][]mat={{0.2,8.6,3.8,15.4,9.2},{4.6,15.6,6.6,14.5,8.4},{22.2,16.11,6.5,30.5,7.8},{1,0.6,8.7,3.9,5.6,9.9},{4,5,6,1,8},{2,16,6,3,7},{33,7,65,9,6}};
	       for(int i=0;i<mat.length;i++){
	       for(l=0;l<mat[i].length;l++){}
	       k=l;
	       
	      }
          */ 
	       //SOM som=new SOM();
    
	       som.Creat_Map(cols_data,file2,dim,ite,err,tau);
	      // som.data(mat,4,file3);
	       som.Train(matrix_input,rows_data,cols_data,file);
	      
	       int i=0;
	       
	    sortie_position=new BufferedReader(new FileReader("Node.txt"));   
	    sortie_poids=new BufferedReader(new FileReader("data.txt"));   
	       while((ligne=sortie_position.readLine())!=null){
	           
	           st=new StringTokenizer(ligne,"\t");
	           while(st.hasMoreTokens()){
	           
	           data[i]=Double.valueOf(st.nextToken()).doubleValue();
	           i++;
	          
	           }
	           n=i;
	           
	       }
	       for(int j=0;j<n;j++){
	          // tab[j]=data[j];
	          
	          
	          }
	       
	       int m=0,a=0,cols=0,rows=0;
	        while((ligne2=sortie_poids.readLine())!=null){
	           a=0;
	           st2=new StringTokenizer(ligne2,"\t");
	           while(st2.hasMoreTokens()){
	           
	           poids[m][a]=Double.valueOf(st2.nextToken());
	           a++;
	            
	           }
	           
	          m++;
	           rows=m;
	           cols=a;
	         
	           
	       }
	       
	      /*System.out.println(n);
	      System.out.println(cols);
	      System.out.println(rows);
	       System.out.println(cols_data);
	       System.out.println(rows_data);
	      for(int j=0;j<n;j++){
	        System.out.println(data[j]);
	       
	       
	       }*/
	      
	 /*     System.out.println("les donnes intiales");
	      
	      for(int f=0;f<rows;f++){
	      for(int d=0;d<cols;d++){
	      System.out.println(poids[f][d]);}}
	      for(int p=0;p<rows_data;p++){
	       for(int c=0;c<cols_data;c++){
	       
	       System.out.print(matrix_input[p][c]);System.out.print("\t");
	       
	       }
	       System.out.println();
	      
	       
	       }
	   */   
	       }
       
   
    
}
