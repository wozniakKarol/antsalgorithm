package algorithm;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
Klasa Matrix zawiera metode readpoints, która wczytuje z pliku punkty, a następnie zapisuje je do listy.
Wywoływana jest w konstruktorze klasy Matrix, gdzie stworzona zostanie macierz odległości.
*/
public class Matrix {
    
    private final double[][] matrix;
    private final int size;
    
    public Matrix(String filename) throws IOException, FileNotFoundException, CloneNotSupportedException, NumberFormatException
    {
        List<Points> list;
        list = readPoints(filename);
        size = list.size();
        matrix = new double[size][size];
          for(int j=0; j<size; j++){
                for(int i=0; i <size; i++)
                {
                    matrix[j][i] = distance(list.get(j), list.get(i));
                }
          }
       
    }
    
    private List<Points> readPoints(String filename) throws FileNotFoundException, IOException, CloneNotSupportedException, NumberFormatException
    {
        FileReader fstream;
      fstream = new FileReader(filename);
      
          BufferedReader br = new BufferedReader(fstream);
          String strLine;
          List<Points> lista;
          lista = new ArrayList<>();
          Points point = new Points();
          while ((strLine = br.readLine()) != null)   {
              String[] parts = strLine.split(" ");
              point.setX(Double.parseDouble(parts[0]));
              point.setY(Double.parseDouble(parts[1]));
              Points prim = (Points) point.clone();             
              lista.add(prim);             
          }
       return lista;   
    }
    private double distance(Points p1, Points p2)
    {
        return Math.sqrt(Math.pow((p1.getX()-p2.getX()),2) + Math.pow((p1.getY()-p2.getY()),2));
    }
    
    public double getMatrix(int i, int j)
    {
        if (i < 0 || i >= size ) {
            throw new ArrayIndexOutOfBoundsException(i);
           
        }
        else if(j < 0 || j>= size )
        {
             throw new ArrayIndexOutOfBoundsException(j);
        }
        return matrix[i][j];
    }
    public int getSize()
    {
        return size;
    }
}
