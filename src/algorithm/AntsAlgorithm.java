package algorithm;


import algorithm.Ants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
Klasa tzw "Silnik Obliczeniowy" to tutaj wykonywana jest główna pętla algorytmu.
Klasa AntsAlgorithm dziedziczy po Thread. Przesłonięta jest metoda run, która uruchamia wątek.
*/
public class AntsAlgorithm extends Thread{ 
    private static boolean theEnd = false; 
    private static String result = new String(); 
    private final Params param;
    private boolean flag = true;
    private final Matrix mat;
    
    public AntsAlgorithm(Params dane) throws IOException, FileNotFoundException, NumberFormatException, CloneNotSupportedException
    {
        param = dane;
        mat = new Matrix(dane.getFilename());
    }
   
    /**
     *
     * @param bestLength
     * @param size
     * @param time
     * @param maxTime
     * @param best
     * @param ant
     * @param phe
     * @param mat
     * @param par
     * @param b
     * @throws Exception
     */
    /*
    pomocnicza metoda, w której znajduję się główna pętla.
    */
    public void alg( int size, int time, int maxTime, Ants ant, Pheromon phe, Matrix mat, Params par, BestLength b) throws Exception {
        StringBuilder tmp = new StringBuilder();
        tmp.append(ant.showAnts(ant, mat, phe));
        tmp.append('\n');
        double currBestLength = 0.0;
        int[] b1 = new int[size];
        System.arraycopy(b.getBestLength(),0,b1,0,b.getBestLength().length);
        double bestLength = phe.length(b1, mat, ant);
        
        while(time<maxTime){ 
            ant.updateAnts(phe, mat, par); 
            phe.updatePheromon(ant, mat, par);            
            b.bestTrail(ant, mat, phe); 
            currBestLength = phe.length(b.getBestLength(), mat, ant); 
            if(currBestLength < bestLength) { 
                bestLength = currBestLength; 
                System.arraycopy(b.getBestLength(), 0, b1, 0, b1.length); 
                tmp.append("New best length of " + bestLength + " found at time " + time + '\n'); 
                
            } 
            time++; 
        }
        
        tmp.append(ant.display(b.getBestLength())); 
        result = tmp.toString(); 
        theEnd = true; 
    } 
    
public static boolean getStatus(){ 
        return theEnd; 
    } 
public static String getResult(){ 
    return result; 
}
public boolean getFlag()
{
    return flag;
}
    @Override
    public void run()
{
    
           
            
             
            int size = mat.getSize(); 
            try { 
                Ants ant = new Ants(size, param.getNumberAnts()); 
                ant.initAnts(size);
                double b = 0.01;
                Pheromon pheromon = new Pheromon(size);
                pheromon.initPheromon(b);
                
                int maxTime = param.getTime();
                BestLength bTrail = new BestLength(size); 
                bTrail.bestTrail(ant, mat, pheromon); 
                 
                int time =0;
                alg( size, time, maxTime, ant, pheromon, mat, param, bTrail);
            } catch (Exception ex) { 
                Logger.getLogger(AntsAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
                flag = false;
                
            } 
        
}
}

