package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class TaskC {
    public static class Step {
        int type, num, left, count;
        long prevCount, curCount;
    }
    int N = (int) 1e5;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Step[] steps = new Step[n];
        int[] firstNums = new int[N];
        int cur = 0;
        long curCount = 0;
        for (int i = 0; i < n; i++) {
            Step s = new Step();
            steps[i] = s;
            s.type = in.nextInt();
            s.prevCount = curCount;
            if (s.type == 1) {
                s.num = in.nextInt();
                if (cur < N) {
                    firstNums[cur++] = s.num;
                }
                curCount++;
            } else {
                s.left = in.nextInt();
                s.count = in.nextInt();
                int ind = 0, rep = 0;
                while (cur < N && rep < s.count) {
                    firstNums[cur++] = firstNums[ind++];
                    if (ind == s.left) {
                        ind = 0;
                        rep++;
                    }
                }
                curCount += s.left * s.count;
            }
            s.curCount = curCount;
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            long d = in.nextLong();
            int ind = lowerBound(steps, d);
            Step tar = steps[ind];
            if (tar.type == 1) {
                out.print(tar.num + " ");
            } else {
                int ii = (int)(d - tar.prevCount - 1) % tar.left;
                out.print(firstNums[ii] + " ");
            }
        }
    }

    public int lowerBound(Step[] steps, long d) {
        int t1 = 0, t2 = steps.length - 1;
        while (t1 < t2) {
            int t = (t1 + t2) / 2;
            if (steps[t].curCount < d) {
                t1 = t + 1;
            } else {
                t2 = t;
            }
        }
        return t1;
    }
}
