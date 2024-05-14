import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Priority {
    public static void main(String[] args) {
        int[][] arrivalTimeBurstTimeAndPriority = {
            { 0, 6, 2 },
            { 1, 2, 2 },
            { 1, 3, 4 },
            { 2, 1, 1 },
            { 2, 2, 3 }
        };

        Arrays.sort(arrivalTimeBurstTimeAndPriority, Comparator.comparingInt(o -> o[2]));

        ArrayList<int[]> queue = new ArrayList<>();
        for (int i = 0; i < arrivalTimeBurstTimeAndPriority.length; i++) {
            queue.add(arrivalTimeBurstTimeAndPriority[i]);
        }

        int time = 0;
        ArrayList<Integer> finishTime = new ArrayList<>();
        ArrayList<Integer> waitingTime = new ArrayList<>();
        ArrayList<Integer> turnAroundTime = new ArrayList<>();
        while (!queue.isEmpty()) {
            int arrivalTimeOfCurrentProcess = queue.get(0)[0];
            int burstTimeOfCurrentProcess = queue.get(0)[1];
            queue.remove(0);
            if (time < arrivalTimeOfCurrentProcess) {
                queue.add(queue.size() - 1, new int[] { arrivalTimeOfCurrentProcess, burstTimeOfCurrentProcess });
            } else {
                time += burstTimeOfCurrentProcess;
                finishTime.add(time);
                turnAroundTime.add(time - arrivalTimeOfCurrentProcess);
                waitingTime.add((time - arrivalTimeOfCurrentProcess) - burstTimeOfCurrentProcess);
           
                queue.sort(Comparator.comparingInt(o -> o[1]));
            }
        }

        float averageWaitingTime = 0;
        for (int i = 0; i < waitingTime.size(); i++) {
            averageWaitingTime += waitingTime.get(i);
        }
        averageWaitingTime /= waitingTime.size();

        float averageTurnAroundTime = 0;
        for (int i = 0; i < turnAroundTime.size(); i++) {
            averageTurnAroundTime += turnAroundTime.get(i);
        }
        averageTurnAroundTime /= turnAroundTime.size();

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Arrival Time | Burst Time | Priority | Finish Time | Waiting Time | Turn Around Time");
        for (int i = 0; i < arrivalTimeBurstTimeAndPriority.length; i++) {
            System.out.format("%13d | %10d | %8d | %11d | %12d | %15d%n", 
            arrivalTimeBurstTimeAndPriority[i][0], 
            arrivalTimeBurstTimeAndPriority[i][1], 
            arrivalTimeBurstTimeAndPriority[i][2], 
            finishTime.get(i), 
            waitingTime.get(i), 
            turnAroundTime.get(i));
        }
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turn Around Time: " + averageTurnAroundTime);

    }
}
