
package anas;

import java.io.IOException;


public class test {
    
    int n ;
    static double c1[][] ;
    recherche RE ; 
    int l ;
    int Width , Height ;

    public test(int n, int Width , int Height) {
       
        this.n = n;
        this.Width = Width ;
        this.Height = Height ;
        RE = new recherche(n,Width,Height);
    
    }
    
    public void D() throws IOException{
        
        RE.distance();
        RE.colorPixel(RE.dis);
        l  = RE.getL();
        c1 = new double[l][3];
        c1 = RE.getC();
        
        
        
    }
    
    public static void main(String[] args) throws IOException {
        
        test t = new test(3,100,100);
        t.D();
        int k = 0 ;
        for(int i = 0 ; i < c1.length ; i++){
            
            for(int j = 0 ; j < c1[i].length ; j++){
                
                 System.out.print((int)c1[i][j]);       
                 System.out.print(" ");
                 k++ ;
            }
            
            System.out.println();
            
        }
        
        System.out.println(" k = "+k);
    }
    
    
    
    
    
    
    
    
}
