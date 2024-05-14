import java.util.ArrayList;
import java.util.Collections;

public class Optimal {
    public static void main(String[] args) {
        // int[] arr = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        // int frames = 4;

        // int[] arr = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        // int frames = 3;

        // int[] arr = {3, 2, 1, 3, 4, 1, 6, 2, 4, 3, 4, 2, 1, 4, 5, 2, 1, 3, 4};
        // int frames = 3;

        // int[] arr = {0, 2, 1, 6, 4, 0, 1, 0, 3, 1, 2, 1};
        // int frames = 4;
        
        int[] arr = {1, 2, 3, 4, 5, 3, 4, 1, 6, 7, 8, 7, 8, 9, 7, 8, 9, 5, 4, 5, 4, 2};
        int frames = 4;

        ArrayList<Integer> frameList = new ArrayList<>(frames);
        ArrayList<String> hitOrMissList = new ArrayList<>();
        int hitCount = 0;
        int missCount = 0;

        int i = 0;
        while(frameList.size() != frames) {
            if(frameList.contains(arr[i])) {
                hitOrMissList.add("Hit");
                hitCount++;
            } else {
                frameList.add(arr[i]);
                hitOrMissList.add("Miss");
                missCount++;
            }
            i++;
        }

        while(i < arr.length) {
            if(frameList.contains(arr[i])) {
                hitOrMissList.add("Hit");
                hitCount++;
            } else {
                int j = i + 1;
                ArrayList<Integer> temp = new ArrayList<>(Collections.nCopies(frameList.size(), 0));
                while (j < arr.length) {
                    if(frameList.contains(arr[j])) {
                        int index = frameList.indexOf(arr[j]);
                        temp.set(index, 1);
                        if (Collections.frequency(temp, 0) == 1) {
                            break;
                        }
                    }
                    j++;
                }
                int index = temp.indexOf(0);
                frameList.remove(index);
                frameList.add(index, arr[i]);
                hitOrMissList.add("Miss");
                missCount++;
            }
            i++;
        }

        System.out.println(frameList);
        System.out.println(hitOrMissList);

        System.out.println("Hit Count: " + hitCount);
        System.out.println("Miss Count: " + missCount);

        double hitRatio = ((double)hitCount / arr.length) * 100;
        double missRatio = ((double)missCount / arr.length) * 100;

        System.out.println(hitRatio);
        System.out.println(missRatio);
    }
}
