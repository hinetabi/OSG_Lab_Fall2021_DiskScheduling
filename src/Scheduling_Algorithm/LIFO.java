/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduling_Algorithm;

import java.util.ArrayList;
import MyInit.Init;
/**
 *
 * @author Legion
 */
public class LIFO {
    static final int HIGH = 199;
    static final int LOW = 0;
    public static void main() {
        ArrayList<Integer> queue = new ArrayList<>();

        int head, qSize;
        int seek = 0, diff;
        float avg;
        qSize = Validation.Inputter.inputInteger("Input the number of disk locations: ", false);
        head = Validation.Inputter.inputInteger("Enter initial head position: ", false);
        System.out.println("Enter disk positions to read: ");

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
        queue.add(head);
        

        for (int j = queue.size()-1; j > 0; j--) {
            diff = Math.abs(queue.get(j) - queue.get(j-1));
            seek += diff;
            System.out.printf("Disk head moves from %d to %d with seek %d\n", queue.get(j), queue.get(j - 1), diff);
        }

        System.out.println("Total seek time is " + seek);
        avg = seek / (float) qSize;
        System.out.printf("Average seek time is %f\n", avg);
        
        MyInit.Init.saveProcessToFile(queue, "Output.txt", false, qSize);
        System.out.println("\nData are successfully saved in Output.txt");
    }
}

