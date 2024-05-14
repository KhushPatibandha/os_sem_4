import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class RR {
    public static void main(String[] args) {

        Process p1 = new Process("p1", 5, 5);
        Process p2 = new Process("p2", 4, 6);
        Process p3 = new Process("p3", 3, 7);
        Process p4 = new Process("p4", 1, 9);
        Process p5 = new Process("p5", 2, 2);
        Process p6 = new Process("p6", 6, 3);

        int q = 3;
        Process[] processes = {p1, p2, p3, p4, p5, p6};
        Arrays.sort(processes, Comparator.comparingInt(o -> o.arrivalTime));

        ArrayList<Process> queue = new ArrayList<>();
        queue.add(processes[0]);
        int time = processes[0].arrivalTime;
        while(!queue.isEmpty()) {
            Process currProcess = queue.remove(0);
            if(currProcess.burstTime > q) {
                currProcess.burstTime -= q;
                time += q;
                for(int i = 0; i < processes.length; i++) {
                    if(queue.contains(processes[i])) {
                        continue;
                    } else if (processes[i] == currProcess || processes[i].burstTime == 0) {
                        continue;
                    }

                    if(processes[i].arrivalTime <= time) {
                        queue.add(processes[i]);
                    }
                }
                queue.add(currProcess);
            } else {
                time += currProcess.burstTime;
                currProcess.burstTime = 0;
                currProcess.finishTime = time;
                currProcess.turnAroundTime = currProcess.finishTime - currProcess.arrivalTime;
                currProcess.waitingTime = currProcess.turnAroundTime - currProcess.bt;
            }
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n", "ProcessID", "Arrival", "Burst", "Finish", "Waiting", "TurnAround");
        for(int i = 0; i < processes.length; i++) {
            System.out.printf("%-10s %-10d %-10d %-10d %-10d %-10d%n", 
            processes[i].processId, 
            processes[i].arrivalTime, 
            processes[i].bt, 
            processes[i].finishTime, 
            processes[i].waitingTime, 
            processes[i].turnAroundTime);
        }
        System.out.println("--------------------------------------------------------------------");
    }
}

class Process {
    String processId;
    int arrivalTime;
    int burstTime;
    final int bt;
    int finishTime;
    int waitingTime;
    int turnAroundTime;

    public Process(String processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.bt = burstTime;
    }
}
