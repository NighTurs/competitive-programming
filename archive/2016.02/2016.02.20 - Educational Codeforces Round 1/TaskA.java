package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskA {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        List<Long> ptwo = new ArrayList<>();
        List<Long> ptwoSum = new ArrayList<>();
        long cur = 1;
        while (cur < Integer.MAX_VALUE) {
            ptwo.add(cur);
            ptwoSum.add(cur == 1 ? 1 : ptwoSum.get(ptwoSum.size() - 1) + ptwo.get(ptwo.size() - 1));
            cur *= 2;
        }
        for (int test = 0; test < t; test++) {
            long n = in.nextLong();
            int ind = Collections.binarySearch(ptwo, n);
            out.println((1 + n) * n / 2 - 2 * ptwoSum.get(ind >= 0 ? ind : -ind - 2));
        }
    }
}