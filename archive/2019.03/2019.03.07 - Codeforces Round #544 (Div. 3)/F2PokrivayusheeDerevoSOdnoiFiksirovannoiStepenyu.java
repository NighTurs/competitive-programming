package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class F2PokrivayusheeDerevoSOdnoiFiksirovannoiStepenyu {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int d = in.nextInt();
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

        Set<Integer> shouldTake = new HashSet<>();
        int[] color = new int[n];
        int curColor = 1;
        for (int i = 1; i < n; i++) {
            if (color[i] != 0) {
                continue;
            }
            color[i] = curColor;
            boolean found = false;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int v = queue.pop();
                for (int h = 0; h < a.get(v).size(); h++) {
                    int to = a.get(v).get(h);
                    if (to == 0) {
                        if (!found) {
                            shouldTake.add(v);
                            found = true;
                        }
                        continue;
                    }
                    if (color[to] == 0) {
                        color[to] = curColor;
                        queue.add(to);
                    }
                }
            }
            curColor++;
        }

        if (shouldTake.size() > d) {
            out.println("NO");
            return;
        }

        for (Integer i: a.get(0)) {
            if (shouldTake.contains(i)) {
                continue;
            }
            if (d - shouldTake.size() == 0) {
                break;
            }
            shouldTake.add(i);
        }

        if (shouldTake.size() != d) {
            out.println("NO");
            return;
        }

        out.println("YES");
        Set<Integer> taken = shouldTake;

        taken.add(0);
        LinkedList<Integer> q = new LinkedList<>();
        for (Integer to : taken) {
            if (to == 0) {
                continue;
            }
            out.println(String.format("%d %d", 1, to + 1));
            q.add(to);
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
