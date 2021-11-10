/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyInit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Init {
    static Random rd = new Random();
    public static int getRandomInt()
    {
        return rd.nextInt(Integer.MAX_VALUE);
    }

    public static int getRandomInRange(int min, int max)
    {
        return rd.nextInt(max - min + 1) + min;
    }

    public static int[] readTextFile(String filename)
    {
        int count = 0;
        String line;
        File text = new File(filename);
        try 
        {
            Scanner scan = new Scanner(text);
            while(scan.hasNextLine())
            {
                line = scan.nextLine();
                if(!line.isEmpty())
                    count++;
                
            }
            int[] data = new int[count];
            scan = new Scanner(text);
            for(int i = 0; scan.hasNextLine();)
            {
                line = scan.nextLine();
                String[] buffer = line.split(" ");
                if(!line.isEmpty())
                    data[i++] = Integer.parseInt(buffer[buffer.length - 1]);
            }
            return data;
        } 
        catch (FileNotFoundException ex) 
        {
            return null;
        }     
    }
    public static void randomProcessToFile(int numOfProcess, String filename)
    {
        int[] listProcess = new int[numOfProcess];
        for(int i = 0; i < numOfProcess; i++)
        {
            listProcess[i] = getRandomInRange(0, 199);
        }
        File file = new File(filename);
        FileWriter writer;
        try 
        {
            writer = new FileWriter(file);
            PrintWriter pWriter = new PrintWriter(writer);
            for(int i = 0; i < numOfProcess; i++)
            {
                pWriter.println("Process number " + (i + 1) + ": " + listProcess[i]);
            }
            pWriter.close();
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }
    public static void saveProcessToFile(ArrayList<Integer> queue , String filename, boolean increasingOrder, int qSize)
    {
  
        File file = new File(filename);
        FileWriter writer;
        int seek = 0;
        try 
        {
            writer = new FileWriter(file);
            PrintWriter pWriter = new PrintWriter(writer);
            if (increasingOrder){
                for(int i = 0; i < queue.size() - 1; i++)
                {
                    int diff = Math.abs(queue.get(i+1) - queue.get(i));
                    seek += diff;
                    pWriter.println("Move " + queue.get(i) + " to " + queue.get(i+1) + " with seek " + diff);
                } 
                pWriter.println("Total Seek time is " + seek);
                double avg = seek / (float) qSize;
                pWriter.println("Average seek time is " + avg);
            } else {
                for(int i = queue.size()-1; i > 0; i--)
                {
                    int diff = Math.abs(queue.get(i) - queue.get(i-1));
                    seek += diff;
                    pWriter.println("Move " + queue.get(i) + " to " + queue.get(i-1) + " with seek " + diff);
                } 
                pWriter.println("Total Seek time is " + seek);
                double avg = seek / (float) qSize;
                pWriter.println("Average seek time is " + avg);
            }
            pWriter.close();
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }
    
    public static void saveProcessToFile(int[] queue , String filename, int qSize)
    {
  
        File file = new File(filename);
        FileWriter writer;
        int seek = 0;
        try 
        {
            writer = new FileWriter(file);
            PrintWriter pWriter = new PrintWriter(writer);
            for(int i = 0; i < qSize; i++)
            {
                int diff = Math.abs(queue[i+1] - queue[i]);
                seek += diff;
                pWriter.println("Move " + queue[i] + " to " + queue[i+1] + " with seek " + diff);
            } 
            pWriter.println("Total Seek time is " + seek);
            double avg = seek / (float) (qSize);
            pWriter.println("Average seek time is " + avg);
            pWriter.close();
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }
}
