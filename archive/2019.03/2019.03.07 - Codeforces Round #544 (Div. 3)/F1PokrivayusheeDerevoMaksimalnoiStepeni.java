package task;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class F1PokrivayusheeDerevoMaksimalnoiStepeni {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Integer>> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int j1 = in.nextInt() - 1;
            int j2 = in.nextInt() - 1;
            a.get(j1).add(j2);
            a.get(j2).add(j1);
        }

        int max = Integer.MIN_VALUE;
        int st = 0;
        for (int i = 0; i < n; i++) {
            if (a.get(i).size() > max) {
                max = a.get(i).size();
                st = i;
            }
        }
        Set<Integer> taken = new HashSet<>();
        taken.add(st);
        LinkedList<Integer> q = new LinkedList<>();
        for (Integer to : a.get(st)) {
            out.println(String.format("%d %d", st + 1, to + 1));
            q.add(to);
            taken.add(to);
        }

        while (taken.size() != n) {
            int v = q.pop();
            for (Integer to : a.get(v)) {
                if (taken.contains(to)) {
                    continue;
                }
                out.println(String.format("%d %d", v + 1, to + 1));
                q.add(to);
                taken.add(to);
            }
        }
    }
}
