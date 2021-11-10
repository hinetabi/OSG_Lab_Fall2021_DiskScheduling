/*
  C-SCAN: Circular Scan
   - Scans towards the nearest end and works it way all the way to the end of the system.
   - Once it hits the bottom or top it jumps to the other end and moves in the same direction.
*/
package Scheduling_Algorithm;

import java.util.ArrayList;
import java.util.Collections;

import MyInit.Init;

/**
 *
 * @author Legion
 */
public class Cscan {
    final static int LOW = 0;
    final static int HIGH = 199;
    
    public static void main() {
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> queue1 = new ArrayList<>();
        ArrayList<Integer> queue2 = new ArrayList<>();
        
        int head, q_size;
        int seek = 0, diff, temp;
        float avg;
        q_size = Validation.Inputter.inputInteger("Input the number of disk locations: ", false);
        head = Validation.Inputter.inputInteger("Enter initial head position: ", false);
        System.out.println("Enter disk positions to read: ");
        if (Validation.Inputter.isRanDom("You want to random " + q_size + " location(s)? (y/n): ")) 
        {
           Init.randomProcessToFile(q_size, "Process data.txt");
           int[] buffer = Init.readTextFile("Process data.txt");
           System.out.println("Random Successfully! Please view in Process data.txt\n");
           for (int i = 0; i < q_size; i++) 
           {
                //queue1 - elems greater than head
                if (buffer[i] >= head) 
                    queue1.add(buffer[i]);
                else 
                    queue2.add(buffer[i]);
            }
        } 
        else 
        {
            for (int i = 0; i < q_size; i++) 
            {
                temp = Validation.Inputter.inputIntegerInRange("Enter location " + (i + 1) + ": ", LOW, HIGH);
                //queue1 - elems greater than head
                if (temp >= head) 
                    queue1.add(temp);
                else 
                    queue2.add(temp);
            }
        }    
        
        //sort queue1 - increasing order
        Collections.sort(queue1);
        
        //sort queue2
        Collections.sort(queue2);
        queue.add(head);
        if (queue1.isEmpty()) {
            Collections.reverse(queue2);
            for (int i = 0; i < queue2.size(); i++) {
                queue.add(queue2.get(i));
            }
        } else {
            for (int i = 0; i < queue1.size(); i++) {
                queue.add(queue1.get(i));
            }
            
            if (!queue2.isEmpty()) {
                queue.add(HIGH);
                queue.add(LOW);
            }
            for (int i = 0; i < queue2.size(); i++) {
                queue.add(queue2.get(i));
            }
        }
        
        for (int j = 0; j < queue.size() - 1; j++) {
            diff = Math.abs(queue.get(j+1) - queue.get(j));
            seek += diff;
            System.out.printf("Disk head moves from %d to %d with seek %d\n", queue.get(j), queue.get(j+1), diff);
        }     
        System.out.println("Total seek time is " + seek);
        avg = seek/ (float) q_size;
        System.out.printf("Average seek time is %f\n", avg);
        
        MyInit.Init.saveProcessToFile(queue,"Output.txt", true, q_size);
        System.out.println("\nData are successfully saved in Output.txt");
    }
}