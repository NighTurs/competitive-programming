package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BSortirovkaSdvigami {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minPos = -1;
            for (int h = i; h < n; h++) {
                if (a[h] < min) {
                    min = a[h];
                    minPos = h;
                }
            }
            if (i != minPos) {
                int size = minPos - i;
                ans.add(String.format("%d %d %d", i + 1, minPos + 1, size));
                for (int h = minPos - 1; h >= 0; h--) {
                    a[h + 1] = a[h];
                }
            }
        }
        out.println(ans.size());
        for (String ss : ans) {
            out.println(ss);
        }
    }
}
