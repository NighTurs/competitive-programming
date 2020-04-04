package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AValeraIDek {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        LinkedList<Integer> a = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
        }
        List<Pair<Integer, Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int first = a.pollFirst();
            int second = a.pollFirst();
            ans.add(new Pair<>(first, second));
            if (first > second) {
                a.addFirst(first);
                a.addLast(second);
            } else {
                a.addFirst(second);
                a.addLast(first);
            }
        }

        List<Integer> st = new ArrayList<>();
        st.addAll(a);
        for (int i = 0; i < m; i++) {
            long op = in.nextLong() - 1;
            if (op < ans.size()) {
                out.println(String.format("%d %d", ans.get((int)op).fs, ans.get((int)op).sc));
            } else {
                op = op % (n - 1);
                out.println(String.format("%d %d", st.get(0), st.get((int)op + 1)));
            }
        }
    }
}
