package Scheduling_Algorithm;

import java.util.ArrayList;

import MyInit.Init;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SSTF {
    final static int LOW = 0;
    final static int HIGH = 199;
    public static void main(){
        ArrayList<Integer> queue = new ArrayList<>();
        int[] queue2;

        int head;
        int seek = 0;
        int qSize;
        float avg;

        System.out.println("SSTF Disk Scheduling");


        qSize = Validation.Inputter.inputInteger("Enter queue size: ", false);

        queue2 = new int[qSize];

        head = Validation.Inputter.inputInteger("Enter initial head position: ", false);

        
        System.out.println("Input Elements: ");
        if (Validation.Inputter.isRanDom("You want to random " + qSize + " location(s)? (y/n): ")) 
        {
            Init.randomProcessToFile(qSize, "Process data.txt");
            int[] buffer = Init.readTextFile("Process data.txt");
            System.out.println("Random Successfully! Please view in Process data.txt\n");
            for (int i = 0; i < qSize; i++) 
                queue.add(buffer[i]);
        } 
        else 
            for (int i = 0; i < qSize; i++) 
                queue.add(Validation.Inputter.inputIntegerInRange("Enter location " + (i + 1) + ": ", LOW, HIGH));
        try
        {
            File file = new File("Output.txt");
        FileWriter writer;
        writer = new FileWriter(file);
        PrintWriter pWriter = new PrintWriter(writer);

        for (int i = 0; i < qSize; i++) {
            int diff = Math.abs(head - queue.get(0));
            int buffer = 0;
            for(int j = 0; j < queue.size(); j++)
                if(diff > Math.abs(head - queue.get(j)))
                {
                    diff = Math.abs(head - queue.get(j));
                    buffer = j;
                }
            System.out.println("Move " + head + " to " + queue.get(buffer) + " with seek " + diff);
            pWriter.println("Move " + head + " to " + queue.get(buffer) + " with seek " + diff);
            head = queue.get(buffer);
            queue2[i] = diff;
            queue.remove(buffer);

        }
        for (int i = 0 ; i < qSize ; i++)
            seek += queue2[i];
        System.out.println("Total seek time is " + seek);
        pWriter.println("Total Seek time is " + seek);
        avg = seek / (float) qSize;
        System.out.println("Average seek time is " + avg);
        pWriter.println("Average seek time is " + avg);
        
        pWriter.close();
        writer.close();
    }
    catch(IOException e)
    {
        System.out.println(e);
    }
        System.out.println("\nData are successfully saved in Output.txt");
    }
}