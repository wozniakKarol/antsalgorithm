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
klasa przechowywująca punkty oraz metody dostępowe.
*/
public class Points implements Cloneable{
    
    private double x;
    private double y;
    
    public double getX()
    {
    return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
@Override
protected Object clone() throws CloneNotSupportedException {
return super.clone();
}
    
}
