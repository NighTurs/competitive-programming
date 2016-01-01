package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(a[n - 1]);
        int i = n - 1;
        while (i >= 0 && a[i] == a[n - 1]) {
            i--;
        }
        while (i >= 0) {
            if (i > 1 && a[i - 1] == a[i] && a[i - 2] == a[i]) {
                // skip
            } else {
                if (dq.size() > 0 && dq.peekLast() != a[i]) {
                    dq.addLast(a[i]);
                } else {
                    dq.addFirst(a[i]);
                }
            }
            i--;
        }
        out.println(dq.size());
        while(dq.size() > 0) {
            out.print(dq.pollFirst() + " ");
        }
    }
}
