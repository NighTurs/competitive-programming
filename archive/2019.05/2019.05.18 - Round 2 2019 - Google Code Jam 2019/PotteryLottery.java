package task;

import java.io.PrintWriter;

public class PotteryLottery {
    int M = 7;
    int G = 10;
    int N = 20;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i + 1;
        }
        ArrayUtils.shuffle(a);
        for (int i = 0; i < 99 - M - G; i++) {
            int day = in.nextInt() - 1;
            int vase = a[day % (N - M)];

            out.println(String.format("%d %d", vase, i + 1));
            out.flush();
        }
        int min = Integer.MAX_VALUE;
        int minVase = -1;

        int[] ct = new int[N];
        for (int i = 0; i < M; i++) {
            int day = in.nextInt() - 1;
            int vase = a[N - 1- i];
            out.println(String.format("%d %d", vase, 0));
            out.flush();
            int k = in.nextInt();
            for (int h = 0; h < k; h++) {
                int pl = in.nextInt();
            }
            ct[vase - 1] = k;
            if (k < min) {
                min = k;
                minVase = vase;
            }
        }

        for (int i = 0; i < G; i++) {
            int day = in.nextInt() - 1;
            int minVal = Integer.MAX_VALUE;
            int idx = 0;
            for (int h = N - M; h <= N - 1; h++) {
                if (a[h] == minVase) {
                    continue;
                }
                if (ct[a[h] - 1] < minVal) {
                    minVal = ct[a[h] - 1];
                    idx = a[h];
                }
            }
            out.println(String.format("%d %d", idx, day + 1));
            out.flush();
            ct[idx - 1]++;
        }
        int day = in.nextInt();
        out.println(String.format("%d %d", minVase, 100));
        out.flush();
    }
}
