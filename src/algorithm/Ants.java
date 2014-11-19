package algorithm;


import java.util.ArrayList;
import java.util.Random;

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
Klasa ants przechowywująca liczbę mrówek oraz ich ścieżki.
*/
public class Ants {
    
    private final int numberOfAnts;
    private int[][] antsTrail;
    private int[] trail;
    
    Random random = new Random();
    
    public Ants(int size, int numberOfAnts) throws Exception
    {
        this.numberOfAnts = numberOfAnts;
        trail = new int[size];
        antsTrail = new int[numberOfAnts][size];
        
        
        
        
    }
    /*
    Metoda inicjalizująca trasy mrówek. Ustawia je według losowych wartości przy użyciu
    metody randomTrail.
    */
    public void initAnts( int size) throws Exception
    {
        for( int i =0; i < numberOfAnts; i++)
        {
            int start = random.nextInt(size);
            trail = randomTrail(start, size);
            antsTrail[i] = trail; 
        }
    }
    
    public int getNumberOfAnts()
    {
        return numberOfAnts;
    }
    
    public int[][] getAntsTrail()
    {
        return antsTrail;
    }
    
    /*
    Prywatna metoda zwracajaca losowe wartosci ścieżek.
    */
    private int[] randomTrail(int start, int size) throws Exception
    {
        int[] t = new int[size];
        
        for(int i=0; i<size; i++)
        {
            t[i]=i;
        }
        for(int i=0; i < size; i++)
        {
            int r = randomInteger(i,size,random);
            int tmp = t[r];
            t[r] = t[i];
            t[i] = tmp;
        }        
        int id = indexOf(t,start);
	int tp = t[0];
	t[0]=t[id];
	t[id]=tp;
	
	return t;
    }
    /*
    Metoda która update'uje trasy mrówek. Korzysta z metody buildTrail
    */
    public void updateAnts(Pheromon phe, Matrix mat, Params param) throws Exception
        {
            int size = phe.getSize();
            for (int i = 0; i < getNumberOfAnts(); i++)
            {
                int start = random.nextInt(size);
                int[] newTrail = buildTrail(i, start, phe, mat,param);
                trail = newTrail;
                antsTrail[i] = trail;
                
                
            }
        }
    /*
    metoda buildtrail zwraca nowe trasy mrówek ustalane przy użyciu Algorytmy mrówkowego.
    */
    private int[] buildTrail(int k, int start, Pheromon phe, Matrix mat, Params param)
        {
            int size = phe.getSize();
            int[] t = new int[size];
            boolean[] vis = new boolean[size];
            t[0] = start;
            vis[start] = true;
            for (int i = 0; i < size - 1; ++i)
            {
                int cityX = t[i];
                int next = nextCity(k, cityX, vis, phe, mat, param);
                
                t[i + 1] = next;
                vis[next] = true;
                
            }
            return t;
        }
    /*
    Metoda nextCity zwraca kolejne miejscowości przy użyciu Algorytmu mrówkowego.
    */
    private int nextCity(int k, int city, boolean[] vis, Pheromon phe, Matrix mat, Params param)
        {
            double[] probabillity = probs(k, city, vis, phe, mat, param);
            double[] c = new double[probabillity.length + 1];
            for (int i = 0; i < probabillity.length; i++)
                c[i + 1] = c[i] + probabillity[i]; 
            double p = random.nextDouble(); 
            int j =0;
            for (int i = 0; i < c.length - 1; i++){
                if (p >= c[i] && p < c[i + 1])
                {
                    j = i;     
                }
                   
            }
            
         return j;   
        }
    /*
    W tej metodzie znajduje sie ogólny wzór algorytmu, który oblicza prawdopodobieństwo wyboru miast.
    */
    private double[] probs(int k, int city, boolean[] vis, Pheromon phe, Matrix mat, Params param)
        {
            int size = phe.getSize();
            
            double[] tau = new double[size];
            double sum = 0.0; 
            for (int i = 0; i < tau.length; i++)
            {
                if (i == city)
                    tau[i] = 0.0;
                else if (vis[i] == true)
                    tau[i] = 0.0;
                else
                    tau[i] = Math.pow(phe.getPheromon(city,i), param.getAlpha()) * Math.pow((1.0 / distance(city, i, mat)), param.getBeta());
                sum += tau[i];
            }

            double[] probabillity = new double[size];
            for (int i = 0; i < probabillity.length; i++)
                probabillity[i] = tau[i] / sum;
            return probabillity;
        }
    /*
    pomocnicza metoda zwracająca odległośc z macierzy.
    */
    public double distance(int cityA, int cityB, Matrix mat)
        {
            return mat.getMatrix(cityA,cityB);
        }
    /*
    pomocnicza metoda zwracająca inta z dwóch przedziałów.
    */
    private int randomInteger(int aStart, int aEnd, Random aRandom){
    if (aStart > aEnd) {
      throw new IllegalArgumentException();
    }
    long range = (long)aEnd - (long)aStart;   
    long fraction = (long)(range * aRandom.nextDouble());
    int randomNumber =  (int)(fraction + aStart);    
    return randomNumber;
    
  }
    /*
    pomocnicza metoda szukająca indexu w tablicy po wartości.
    */
  public int indexOf( int[] t, int value) throws Exception
{
    
	for ( int i =0; i < t.length; i++)
	{
		if ( t[i] == value)
			return i;		
	}
   throw new Exception("Target not found in IndexOfTarget");    
}
  /*
  Metoda która zwraca ostateczną najlepszą trasę mrówki. 
  */
  public String display(int[] trail)
        {
            String s;
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < trail.length; ++i)
            {
                t.append(trail[i] + " ");
                
               
            }
            t.append('\n');
            s = t.toString();
            return s;
        }
  /*
  metoda, która drukuje początkowo zainicjowane mrówki
  */
  public String showAnts(Ants ants, Matrix mat, Pheromon phe)
        {
            String s;
            StringBuilder tm = new StringBuilder();
            for (int i = 0; i < ants.getNumberOfAnts(); ++i)
            {
                
                tm.append("ants " + i + ": [ ");
                for (int j = 0; j < mat.getSize(); ++j)
                    tm.append(ants.getAntsTrail()[i][j] + " ");                                   
                tm.append("] len = ");
                double len = phe.length(ants.getAntsTrail()[i],mat , ants);
                tm.append(len);
                tm.append('\n');
                
            }
            s = tm.toString();
            return s;
        }
  
}
	

    
    

