/*
    Scan - Disk Scheduling Algorithm (Elevator)
    scans down towards the nearest end and then when it hits the bottom,
    it scans up servicing the requests that it didn't get going down.
    If a request comes in after it has been scanned it will not be serviced
    until the process comes back down or moves back up.
 */
package Scheduling_Algorithm;

import java.util.Arrays;

import MyInit.Init;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Scan {

    final static int LOW = 0;
    final static int HIGH = 199;

    public static void main() {
        int head;
        int[] queue;
        int seekTime = 0;
        double avg;
        int dloc = 0; // location of disk(head) in arr

        int qSize = Validation.Inputter.inputInteger("Input no of disk locations: ", false);

        queue = new int[qSize + 1];
        head = Validation.Inputter.inputInteger("Enter head position: ", false);

        System.out.println("Input elements in disk queue: ");
        if (Validation.Inputter.isRanDom("You want to random " + qSize + " location(s)? (y/n): ")) {
            Init.randomProcessToFile(qSize, "Process data.txt");
            int[] buffer = Init.readTextFile("Process data.txt");
            System.out.println("Random Successfully! Please view in Process data.txt\n");
            for (int i = 0; i < qSize; i++)
                queue[i] = buffer[i];
        } else
            for (int i = 0; i < qSize; i++)
                queue[i] = Validation.Inputter.inputIntegerInRange("Enter location " + (i + 1) + ": ", LOW, HIGH);

        queue[qSize] = head;
        // sort disk locations queue
        Arrays.sort(queue);
        dloc = Arrays.binarySearch(queue, head);

        try {
            File file = new File("Output.txt");
            FileWriter writer;
            writer = new FileWriter(file);
            PrintWriter pWriter = new PrintWriter(writer);

            for (int i = dloc; i < queue.length - 1; i++) {
                System.out.println("Move " + queue[i] + " to " + queue[i + 1]);
                pWriter.println("Move " + queue[i] + " to " + queue[i + 1]);
                seekTime += Math.abs(queue[i] - queue[i + 1]);
            }

            System.out.println("Move " + queue[queue.length - 1] + " to " + HIGH);
            pWriter.println("Move " + queue[queue.length - 1] + " to " + HIGH);
            seekTime += Math.abs(HIGH - queue[queue.length - 1]);

            if(dloc != 0)
            {
                System.out.println("Move " + HIGH + " to " + queue[dloc - 1]);
                pWriter.println("Move " + HIGH + " to " + queue[dloc - 1]);
                seekTime += Math.abs(HIGH - queue[dloc - 1]);

                for (int i = dloc - 1; i > 0; i--) 
                {
                    System.out.println("Move " + queue[i] + " to " + queue[i - 1]);
                    pWriter.println("Move " + queue[i] + " to " + queue[i - 1]);
                    seekTime += Math.abs(queue[i] - queue[i - 1]);
                }
            }

            avg = seekTime / (double) qSize;
            System.out.println("Total seek time is " + seekTime);
            System.out.println("Average seek time is " + avg);
            pWriter.println("Total seek time is " + seekTime);
            pWriter.println("Average seek time is " + avg);

            pWriter.close();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("\nData are successfully saved in Output.txt");
    }
}