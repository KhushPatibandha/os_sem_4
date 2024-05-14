import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SJF {
    public static void main(String[] args) {
        int[][] arrivalTimeAndBurstTime = {
            {2, 6},
            {5, 2},
            {1, 8},
            {0, 3},
            {4, 4},
        };

        Arrays.sort(arrivalTimeAndBurstTime, Comparator.comparingInt(o -> o[1]));

        ArrayList<int[]> queue = new ArrayList<>();
        for(int i = 0; i < arrivalTimeAndBurstTime.length; i++) {
            queue.add(arrivalTimeAndBurstTime[i]);
        }

        int time = 0;
        ArrayList<Integer> finishTime = new ArrayList<>();
        ArrayList<Integer> waitingTime = new ArrayList<>();
        ArrayList<Integer> turnAroundTime = new ArrayList<>();
        while(!queue.isEmpty()) {
            int arrivalTimeOfCurrentProcess = queue.get(0)[0];
            int burstTimeOfCurrentProcess = queue.get(0)[1];
            queue.remove(0);
            if(time < arrivalTimeOfCurrentProcess) {
                queue.add(queue.size() - 1, new int[]{arrivalTimeOfCurrentProcess, burstTimeOfCurrentProcess});
            } else {
                time += burstTimeOfCurrentProcess;
                finishTime.add(time);
                turnAroundTime.add(time - arrivalTimeOfCurrentProcess);
                waitingTime.add((time - arrivalTimeOfCurrentProcess) - burstTimeOfCurrentProcess);
            }
        }

        float averageWaitingTime = 0;
        for(int i = 0; i < waitingTime.size(); i++) {
            averageWaitingTime += waitingTime.get(i);
        }
        averageWaitingTime /= waitingTime.size();

        float averageTurnAroundTime = 0;
        for(int i = 0; i < turnAroundTime.size(); i++) {
            averageTurnAroundTime += turnAroundTime.get(i);
        }
        averageTurnAroundTime /= turnAroundTime.size();

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Arrival Time | Burst Time | Finish Time | Waiting Time | Turn Around Time");
        for(int i = 0; i < arrivalTimeAndBurstTime.length; i++) {
            System.out.format("%13d | %10d | %11d | %12d | %16d%n", 
            arrivalTimeAndBurstTime[i][0], 
            arrivalTimeAndBurstTime[i][1], 
            finishTime.get(i), 
            waitingTime.get(i), 
            turnAroundTime.get(i));
        }
        
        System.out.println("------------------------------------------------------------------------------------------");
        
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turn Around Time: " + averageTurnAroundTime);
    }
}
