package task;

import java.util.HashSet;

public class LiteratureOptimal {
    public int minTurns(int N, int[] Teja, int[] history) {

        HashSet<Integer> unknown = new HashSet<>();
        for (int i = 1; i <= N * 3; i++) {
            unknown.add(i);
        }
        for (int i = 0; i < Teja.length; i++) {
            unknown.remove(Teja[i]);
        }

        int num2 = 0;
        int num3 = 0;
        for (int i = 0; i < history.length; i++) {
            int hist = history[i];
            if (i % 3 == 1) {
                if (unknown.contains(hist)) {
                    unknown.remove(hist);
                    num3++;
                }
            }
            if (i % 3 == 2) {
                if (unknown.contains(hist)) {
                    unknown.remove(hist);
                    num2++;
                }
            }
        }
        int cur = history.length;
        int steps = 0;
        while (num2 < N && num3 < N) {
            if (cur % 3 == 1) {
                num3++;
            }
            if (cur % 3 == 2) {
                num2++;
            }
            cur++;
            steps++;
        }
        return steps;
    }
}
