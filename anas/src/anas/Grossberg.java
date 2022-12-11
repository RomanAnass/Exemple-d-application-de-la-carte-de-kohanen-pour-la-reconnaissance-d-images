/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author user
 */
public class Grossberg {
    
   Node Winner = new Node();
   
   //tableau de sortie désirée
   double yd[] ; 
   
   // sortie calculer 
   double y = 0   ;
   
   double ViteesApp = 0.7 ;
   
   
   int neuroneCacher ;
   
   // tableau des neurones de grossberg :  
   Node ca[] ; 
   
   // tableau des sorties de kohanen
   int k[] ; 
   
   //pour enregistrer les poids initiale de grosberg : 
   File file = new File("gdata.txt");
   
   //pour enregistrer les resultats finales :
   File res = new File("resultat.txt");
   
   //pour enregistrer les poids finale de kohanen :
   File file1 = new File("poids_finale_k.txt");
   
  // pour enregistrer les poids finale de grosberg : 
   File file2 = new File("poids_finale_g.txt");
   
   //pour enregistrer les poids initiale de kohanen : 
   File File_weight_kohanen  = new File("Don.txt"); 
   
   //pour enregistrer les sortieés calculée de grossberg : 
   File filey = new File("sortie_gross.txt");
   
   
   StringTokenizer str ; 
   String ligne ; 
   PrintWriter out = null ; 
   PrintWriter out1 = null ;
   PrintWriter out2 =  null ;
   PrintWriter out3 = null ;
   PrintWriter out4 = null ;
   double errMo_g = 0 ; 
   double err_g = 0 ; 
   int  iter = 0 ;
   Maps som = new Maps();
   double Matrix_input[][] = new double[256][256];
   double input_vector[] = new double[256];
   int a_cols = 0 , m_rows = 0 , data_rows = 0 , data_cols = 0 ; 
   int nombrepoids ; 
   BufferedReader sortie_data = null ; 
   BufferedReader sortie_kohanen = null ; 
   int d ; // numero de poids  
   double Er = 0 ; 
   int Number_of_Node_k ;
   int size_of_inputVector_and_Weight ;
   double erreur_k = 0 ;
    double erreurMo_k = 0 ;
    int nbr_ligne = 0 ;
    double er = 0 ;
    
    //taux d'apprentissage  :   
    double Learning_Rat  ;
    
   public Grossberg(){
       
       
   }
    
    
    
    
    // fonction pour créer la couche grossberg  
    public void create(int nembreneuron,int nembrepoids)throws IOException{ // fonction qui permet de creer la couche grossberg
   
       neuroneCacher =  nembreneuron ;
       PrintWriter se=null;
       se =new PrintWriter(new FileWriter(file),true);
       ca = new Node[nembreneuron]; 
       for(int i =0 ; i < nembreneuron ; i++){
           
           ca[i] = new Node();
           
           ca[i].Initial_Weight(nembrepoids);
           
       
       se.println("Node"+(i+1)+":");
       for(int l=0 ;l<nembrepoids;l++){
       se.println(ca[i].Weight_of_Node[l]);
       
       }
    
}   
       
       
    }
    
    
     //fonction qui determine le taux d'apprentissage
         public double Vitesse_app(int current_iteration,double v,int h){
       
       
     // return  Start_Lerning_Rate*Math.exp(-((double)(current_iteration/Max_Iteration)));
     return  v*Math.exp(-((double)(current_iteration*Math.log(neuroneCacher))/h));
       }
    
// fonction de l'apprentissage de kohanen plus grossberg à rétro propagation :     
public void Train(int nembreneuron , int dimMap ,int iterMax , double errMax ,double tau ,String data1, String data2 ) throws FileNotFoundException, IOException{
    
    
   
  nombrepoids = dimMap*dimMap ;
  Number_of_Node_k = dimMap*dimMap ;
  yd = new double[nembreneuron];
   
 
  
  // data1 = matrice d'entrée ;
  sortie_data = new BufferedReader(new FileReader(data1+".txt"));


// pour recuperer matrice d'entrée :
  while((ligne = sortie_data.readLine())!=null){
      
      a_cols = 0 ;
      
      str = new StringTokenizer(ligne);
      
      while(str.hasMoreTokens()){
          
          Matrix_input[m_rows][a_cols]= Double.valueOf(str.nextToken()).doubleValue();
          a_cols++ ;
      }
      m_rows++ ;
      
      data_rows = m_rows ; 
      data_cols = a_cols ;
      size_of_inputVector_and_Weight = a_cols ;
      nbr_ligne = m_rows ; 
      
  }
    
   /*for(int i = 0 ; i < data_rows ; i++ ){
       for(int j = 0 ; j < data_cols ; j++){
           
           System.out.print(Matrix_input[i][j]);
           System.out.print("\t");
           
       }
       System.out.println();
       
   }*/
   
 // la création de la carte de kohanen :   
 som.Creat_Map(data_rows,File_weight_kohanen, dimMap , iterMax, errMax,tau);
 
 
 //normalisation des données d'entrées : 
 som.Normalisation(Matrix_input);
 
 // la création de la couche de grossberg ;  
 this.create(nembreneuron, nombrepoids);
 
 k = new int[nombrepoids];  
 
 // data2 les sortiées désirée : 
 sortie_data = new BufferedReader( new FileReader(data2+".txt"));
 
 m_rows = 0 ; 
 
 while((ligne=sortie_data.readLine())!=null){
     
     
     
     str = new StringTokenizer(ligne);
     
     while(str.hasMoreTokens()){
         
         
         yd[m_rows] = Double.valueOf(str.nextToken()).doubleValue();
         
         
     }
     
     m_rows++ ; 
     
 }
    
/* for(int i = 0 ; i < m_rows ; i++){
     
     System.out.print(yd[i]);
     
     System.out.println();
     
     
 }*/

//out1 = new PrintWriter(new FileWriter("data.txt"),true);
 

do{
    
    out1 = new PrintWriter(new FileWriter("data.txt"),true);
    
    for(int i = 0 ; i < data_rows ; i++){
        
       for(int j = 0 ; j < data_cols ; j++){
           
             
          input_vector[j] = Matrix_input[i][j]   ;
         
           
       }
       out = new PrintWriter(new FileWriter(filey),true);
       
       // pour recuperer le vainqueur de chaque iteration :
       Winner = som.Train_k(input_vector,iter,erreurMo_k,errMax) ;
        System.out.println("winner : "+Winner.X_Node+","+Winner.Y_Node);
       
       //pour calculer la déffirence entre les entrées et les poids des neurones de kohanen  : 
       erreur_k += Winner.euclidean( input_vector, size_of_inputVector_and_Weight) ; 
       
       // pour enregistrer les coordonnées du neurone vainqueur dans le fichier data.txt :    
       out1.println(Winner.X_Node+" "+Winner.Y_Node);
       
       //pour calculer le numero de neurone vainqueur : 
       d = (Winner.X_Node * dimMap ) + Winner.Y_Node ;
     
       
       // pour modifier les poids de grossberg : 
       
       // boucle des neurones de grossberg
       for(int k1 = 0 ; k1 < nembreneuron ; k1++ ){
            
            //boucle des poids des neurones de grossberg :            
             for(int t = 0 ; t < nombrepoids ; t++){
                 
                 if(d == t)
                     
                     k[t] = 1 ;  
                 
                 
                 else 
                     
                    k[t] = 0 ; 
                 
                 
             }
         // modification de poids de neurone de grossberg :      
         ca[k1].Update_Weight_g( ViteesApp , k , yd , k1 , nombrepoids );
            
         for(int t1 = 0 ; t1 < nombrepoids ; t1++){
                
             
         // calculer la sortie de grossberg : 
         y+=ca[k1].Weight_of_Node[t1]*k[t1];
        
         
         
        }
       
         
          
         // calculer l'erreur entre la sortie désirée et sortie de gorssberg           
         Er = yd[k1] - y  ;
         
         
         // enregistrer la sortie de grossberg dans le fichier sortie_gross.txt
         out.print(y) ;
         out.println();
         
         y = 0 ; 
         
         if(Er > 0)
             
             err_g += Er ; 
         
         else 
             
             err_g+= Er*(-1);
        
         
       
    }
       
    iter++ ;
     
     
    }   
       
   //iter++ ;
   errMo_g = err_g/nembreneuron ; 
   erreurMo_k = erreur_k/nbr_ligne ; 
   err_g = 0 ; 
   erreur_k = 0 ;
   
System.out.println("erreur moyen g = "+errMo_g);
System.out.println("erreur moyen k = "+erreurMo_k);
    
//iter < iterMax ||
}while(iter < iterMax );
 //  iter < iterMax && errMo_g > errMax && erreurMo_k > errMax
// System.out.println("fin d'qpprentissage");

// pour enregistrer les poids finales des neurones de kohanen : 
 out2 = new PrintWriter(new FileWriter("poids_finale_k.txt"),true);

  out2.println("************************************** LES POIDS FINALES DES NEURONES DE KOHANEN ********************************");
       
         for(int i=0;i<Number_of_Node_k;i++){
         out2.print("Node ");out.print((i+1)+" leur Position:");out.print("\t");out2.println(som.Map[i].X_Node+","+som.Map[i].Y_Node);
         out2.println("les poids :");
      
          for(int j=0;j<size_of_inputVector_and_Weight;j++){
          out2.println(som.Map[i].Weight_of_Node[j]);
             
          }


}

//pour enregistrer les poids finales des neurones de grossberg          
out3 = new PrintWriter(new FileWriter("poids_finale_g.txt"),true);  

out3.println("************************************** LES POIDS FINALES DES NEURONES DE GROSSBERG ********************************");         

for(int i = 0 ; i < nembreneuron ; i++ ){
    
    out3.println("neurone :"+i);
    for(int j = 0 ; j < nombrepoids ; j++){
        
        out3.println(ca[i].Weight_of_Node[j]);
        
    }
    
    
}
//pour enregistrer les resultat finale : 
out4 = new PrintWriter(new FileWriter("resultat.txt"),true);

out4.println(erreurMo_k);
out4.println(som.Learning_Rate);
out4.println(iter);
out4.println(errMo_g);


}

}

