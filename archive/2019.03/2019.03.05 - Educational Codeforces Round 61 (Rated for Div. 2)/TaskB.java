package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer> a = new ArrayList<>();
        long s = 0;
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
            s += a.get(a.size() - 1);
        }
        a.sort(Collections.reverseOrder());

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int q = in.nextInt();
            out.println(s - a.get(q - 1));
        }
    }
}
