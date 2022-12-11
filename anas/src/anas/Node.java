/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anas;

import java.util.*;

public class Node {
     public Node() {
   }
    

    double  []Weight_of_Node;//poids de neurons
    int X_Node,Y_Node;
    Random rand =new Random();
    
    
    
    
   // int Rand_Max=rand.nextInt(9);
    
    //le poids aléatoire
    public int Rand_Weight(){
    return rand.nextInt(9);
    }
    
    public double Rand_Weight2(){
    return rand.nextDouble();
    }
    
    //fonction qui permet d'initialiser les poids des entreés
    public void Initial_Weight(int size){
    Weight_of_Node=new double[size];
    for(int i=0;i<size;i++){Weight_of_Node[i]=Rand_Weight2();}
    
    }
    
    
    //fonction qui recupere la position des neurones
    public void Position(int x,int y){this.X_Node=x;
                                       this.Y_Node=y;}
     
    
    
    
    
    
    //fonction qui calcule la distance entre les entrÃ©es et les diferrents Neurones
    public double euclidean(double[]x,int dim){
     
     double distance=0;
    for(int i=0;i<dim;i++)
        distance+=Math.pow((x[i]-this.Weight_of_Node[i]),2);
     //   System.out.println("distance ="+distance);
    return Math.sqrt(distance);
     
     
     }
    
    
    //fonction de modification des piods paraport le neurones vinqueur
    
    public void Update_Weight(double[]N,double LerningRate,double Distance_from_BMU,int _size ){
    for(int i=0;i<_size;i++){Weight_of_Node[i]+=(LerningRate* Distance_from_BMU)*(N[i]-Weight_of_Node[i]);}
   }
  
    public void Update_Weight_g(double vitesse_apprentissage,int []k,double []d,int j ,int  rows_data){
        for(int i=0;i<rows_data;i++){
            Weight_of_Node[i]+=(vitesse_apprentissage*(d[j]-Weight_of_Node[i])*k[i]) ;
                
                }
    } 
    
     public void Update_Weight_gI(double vitesse_apprentissage,double []k,double []d,int j ,int  rows_data){
        for(int i=0;i<rows_data;i++){
            Weight_of_Node[i]+=(vitesse_apprentissage*(d[j]-Weight_of_Node[i])*k[i]) ;
                
                }
    } 
    
}
