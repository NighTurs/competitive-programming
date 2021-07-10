package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DVzaimnorastushchayaPosledovatelnost {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        int prev = a[0];
        for (int i = 1; i < n; i++) {
            int val = a[i] | prev;
            ans.add(val ^ a[i]);
            prev = val;
        }
        for (Integer v : ans) {
            out.print(v + " ");
        }
        out.println();
    }
}
