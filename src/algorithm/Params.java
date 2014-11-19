package algorithm;

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
Parametry wczytane z gui oraz metody dostępowe.
*/
public class Params {
    
    private double alpha;
    private double beta;
    private double Q;
    private double rho;
    private String filename;
    private int time;
    private int numberAnts;
    
    public double getAlpha()
    {
        return alpha;
    }
    public String getFilename()
    {
        return filename;
    }
    public void setFileName(String filename)
    {
        this.filename = filename;
    }
    public int getTime()
    {
        return time;
    }
    public void setTime(int time)
    {
        this.time = time;
    }
    public int getNumberAnts()
    {
        return numberAnts;
    }
    public void setNumberAnts(int numberAnts)
    {
        this.numberAnts = numberAnts;
    }
    
    public double getBeta()
    {
        return beta;
    }
    
    public double getQ()
    {
        return Q;
    }
    
    public double getRho()
    {
        return rho;
    }
    
    public void setAlpha(double alpha)
    {
        this.alpha = alpha;
    }
    
    public void setBeta(double beta)
    {
        this.beta = beta;
    }
    
    public void setQ(double Q)
    {
        this.Q = Q;
    }
    
    public void setRho(double rho)
    {
        this.rho = rho;
    }
}
