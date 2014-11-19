package algorithm;


import algorithm.Ants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karol
 */

/*
Klasa Pheromon zawiera macierz, w której znajdują się wartości feromonu poszczególnych przejść.
*/
public class Pheromon {
    
    private double[][] pheromon;
    private final int n;
    
    public Pheromon(int n)
    {
        this.n = n;
        pheromon = new double[n][n];
        for(int i=0; i < n; i++)
        {
            for(int j=0; j < n; j++)
            {
                pheromon[i][j] = 0.0;
            }
        }
    }
    
    public void initPheromon(double value)
    {
       for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    pheromon[i][j] = value; 
    }
    
    public void updatePheromon(Ants ants, Matrix mat, Params param)
        {
            
            for (int i = 0; i < n; i++)
            {
                for (int j = i + 1; j < n; j++)
                {
                    for (int k = 0; k < ants.getNumberOfAnts(); ++k)
                    {
                        double length = length(ants.getAntsTrail()[k], mat, ants);
                        double d = (1.0 - param.getRho()) * pheromon[i][j];
                        double in = 0.0;
                        if (isEdge(i, j, ants.getAntsTrail()[k], n) == true) in = (param.getQ() / length);
                            pheromon[i][j] = d + in;                     
                        pheromon[j][i] = pheromon[i][j];
                    }
                }
            }
        }
    
   public double length(int[] trail, Matrix mat, Ants ants)
        {
            double result = 0.0;
            for (int i = 0; i < trail.length - 1; i++)
                result += ants.distance(trail[i], trail[i + 1], mat);
            return result;
        }
   private boolean isEdge( int cityA, int cityB, int[] tr, int size)
    {
        boolean t=false;
        for(int i=1; i<size -1; i++)
        {
            if((tr[i-1] == cityA && tr[i]==cityB) || (tr[i]==cityA && tr[i+1]==cityB))
                t= true;            
        }
    return t;
    }

   public int getSize()
    {
        return n;
    }
    
    public double getPheromon(int i, int j)
    {
        if (i < 0 || i >= n ) {
            throw new ArrayIndexOutOfBoundsException(i);
           
        }
        else if(j < 0 || j>= n )
        {
             throw new ArrayIndexOutOfBoundsException(j);
        }
        return pheromon[i][j];
    }
    
}
