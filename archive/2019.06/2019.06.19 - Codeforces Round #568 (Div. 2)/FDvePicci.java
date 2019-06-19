package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class FDvePicci {

    static final int MASKS = 1 << 9;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] rank = new int[MASKS];
        for (int i = 0; i < n; i++) {
            int c = in.nextInt();
            int mask = 0;
            for (int h = 0; h < c; h++) {
                mask = mask | (1 << (in.nextInt() - 1));
            }
            for (int h = 0; h < MASKS; h++) {
                if ((h & mask) == mask) {
                    rank[h]++;
                }
            }
        }

        long[][] minCost = new long[MASKS][2];
        int[][] minCostIdx = new int[MASKS][2];
        for (int i = 0; i < MASKS; i++) {
            minCost[i][0] = Long.MAX_VALUE;
            minCost[i][1] = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            long cost = in.nextInt();
            int c = in.nextInt();
            int mask = 0;
            for (int h = 0; h < c; h++) {
                mask = mask | (1 << (in.nextInt() - 1));
            }
            if (minCost[mask][0] == Long.MAX_VALUE) {
                minCost[mask][0] = cost;
                minCostIdx[mask][0] = i;
            } else if (minCost[mask][0] != Long.MAX_VALUE && minCost[mask][0] > cost) {
                minCost[mask][1] = minCost[mask][0];
                minCostIdx[mask][1] = minCostIdx[mask][0];
                minCost[mask][0] = cost;
                minCostIdx[mask][0] = i;
            } else if (minCost[mask][1] > cost) {
                minCost[mask][1] = cost;
                minCostIdx[mask][1] = i;
            }
        }
        long[] minFinal = new long[MASKS];
        int[][] minFinalIdx = new int[MASKS][2];
        Arrays.fill(minFinal, Long.MAX_VALUE);

        for (int i = 0; i < MASKS; i++) {
            for (int h = 0; h < MASKS; h++) {
                if (i != h) {
                    if (minCost[i][0] == Long.MAX_VALUE || minCost[h][0] == Long.MAX_VALUE) {
                        continue;
                    }
                    if (minCost[i][0] + minCost[h][0] < minFinal[i | h]) {
                        minFinal[i | h] = minCost[i][0] + minCost[h][0];
                        minFinalIdx[i | h][0] = minCostIdx[i][0];
                        minFinalIdx[i | h][1] = minCostIdx[h][0];
                    }
                } else {
                    if (minCost[i][1] == Long.MAX_VALUE) {
                        continue;
                    }
                    if (minFinal[i] > minCost[i][0] + minCost[i][1]) {
                        minFinal[i] = minCost[i][0] + minCost[i][1];
                        minFinalIdx[i][0] = minCostIdx[i][0];
                        minFinalIdx[i][1] = minCostIdx[i][1];
                    }
                }
            }
        }

        int maxRank = Integer.MIN_VALUE;
        long _minCost = Long.MAX_VALUE;
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < MASKS; i++) {
            if (minFinal[i] == Long.MAX_VALUE) {
                continue;
            }
            if (rank[i] > maxRank || (rank[i] == maxRank && _minCost > minFinal[i])) {
                maxRank = rank[i];
                _minCost = minFinal[i];
                idx1 = minFinalIdx[i][0];
                idx2 = minFinalIdx[i][1];
            }
        }
        out.print(idx1 + 1);
        out.print(' ');
        out.print(idx2 + 1);
    }
}
