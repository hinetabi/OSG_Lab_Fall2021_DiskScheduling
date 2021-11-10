package Scheduling_Algorithm;


import MyInit.Init;

public class FCFS {
    final static int LOW = 0;
    final static int HIGH = 199;
    public static void main() {
        int seek = 0;
        int diff;
        int head;
        float avg;
        int[] queue;

        System.out.println("FCFS Disk Scheduling");

        int qSize = Validation.Inputter.inputInteger("Enter queue size: ", false);

        head =  Validation.Inputter.inputInteger("Enter initial head position: ", false);

        queue = new int[qSize + 1];

        System.out.println("Input Queue elements: ");
        if (Validation.Inputter.isRanDom("You want to random " + qSize + " location(s)? (y/n): ")) 
        {
           Init.randomProcessToFile(qSize, "Process data.txt");
           int[] buffer = Init.readTextFile("Process data.txt");
           for(int i = 0; i < qSize; i++)
           {
               queue[i + 1] = buffer[i];
           }
        }
        else
        {
            for (int i = 1; i < qSize + 1; i++) {
                queue[i] = Validation.Inputter.inputIntegerInRange("Enter location " + (i) + ": ", LOW, HIGH);
            }
        }


        queue[0] = head;

        for (int j = 0; j < qSize; j++) {
            diff = Math.abs(queue[j + 1] - queue[j]);
            seek += diff;
            System.out.println("Move " + queue[j] + " to " + queue[j + 1] + " with seek " + diff);
        }

        System.out.println("Total Seek time is " + seek);
        avg = seek / (float) qSize;
        System.out.println("Average seek time is " + avg);
        
        MyInit.Init.saveProcessToFile(queue, "Output.txt", qSize);
        System.out.println("\nData are successfully saved in Output.txt");
    }

}