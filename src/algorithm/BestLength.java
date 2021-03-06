package algorithm;


import algorithm.Pheromon;
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
Klasa do której zapisuje obecną najlepszą trasę.
*/
public class BestLength {
    private int[] bestLength;
    
    
    public BestLength(int size)
    {
        bestLength = new int[size];
    }
    public void setBestLength(int[] bestLength)
    {
        this.bestLength = bestLength;
    }
    public void bestTrail(Ants ants, Matrix mat, Pheromon phe) 
        {
            double best = phe.length(ants.getAntsTrail()[0], mat, ants);
            int idxBestLength = 0;
            for (int k = 1; k < ants.getNumberOfAnts(); k++)
            {
                double len = phe.length(ants.getAntsTrail()[k], mat, ants);
                if (len < best)
                {
                    best = len;
                    idxBestLength = k;
                }
            }
                       
        System.arraycopy(ants.getAntsTrail()[idxBestLength], 0, bestLength, 0, mat.getSize());
        
        }
    
    public int[] getBestLength()
    {
        return bestLength;
    }
    
}
