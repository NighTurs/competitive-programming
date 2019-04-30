package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ENekoIFleshbeki {

    List<Integer> seq;
    HashMap<Integer, Adj> vs;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        vs = new HashMap<>();

        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n - 1; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            b[i] = in.nextInt();
        }
        int some = -1;
        for (int i = 0; i < n - 1; i++) {
            vs.putIfAbsent(a[i], new Adj());
            vs.putIfAbsent(b[i], new Adj());
            vs.get(a[i]).adj.add(b[i]);
            vs.get(b[i]).adj.add(a[i]);
            vs.get(a[i]).map.add(vs.get(b[i]).adj.size() - 1);
            vs.get(b[i]).map.add(vs.get(a[i]).adj.size() - 1);
            some = a[i];
        }

        List<Integer> odds = new ArrayList<>();
        for (Integer v : vs.keySet()) {
            if (vs.get(v).adj.size() % 2 == 1) {
                odds.add(v);
            }
        }
        if (odds.size() == 1 || odds.size() > 2) {
            out.println(-1);
            return;
        }
        int first = -1;
        int second = -1;
        if (odds.size() > 0) {
            first = odds.get(0);
            second = odds.get(1);
            vs.get(first).adj.add(second);
            vs.get(second).adj.add(first);
            vs.get(first).map.add(vs.get(second).adj.size() - 1);
            vs.get(second).map.add(vs.get(first).adj.size() - 1);
        }

        seq = new ArrayList<>();

        for (Integer v : vs.keySet()) {
            vs.get(v).taken = new boolean[vs.get(v).adj.size()];
        }
        build(some);

        if (seq.size() != n + (first != -1 ? 1 : 0)) {
            out.println(-1);
            return;
        }

        if (first == -1) {
            for (int i = 0; i < seq.size(); i++) {
                out.print(seq.get(i));
                out.print(' ');
            }
        } else {
            for (int i = 0; i < seq.size(); i++) {
                int cur = seq.get(i);
                int nextIdx = (i == seq.size() - 1) ? 0 : i + 1;
                int next = seq.get(nextIdx);
                if ((cur == first && next == second) || (cur == second && next == first)) {
                    for (int h = nextIdx; h < seq.size() - 1; h++) {
                        out.print(seq.get(h));
                        out.print(' ');
                    }
                    for (int h = 0; h <= i; h++) {
                        out.print(seq.get(h));
                        out.print(' ');
                    }
                    break;
                }
            }
        }
    }

    private void build(int v) {
        Adj adj = vs.get(v);
        for (int i = 0; i < adj.adj.size(); i++) {
            if (adj.taken[i]) {
                continue;
            }
            adj.taken[i] = true;
            vs.get(adj.adj.get(i)).taken[adj.map.get(i)] = true;
            build(adj.adj.get(i));
        }
        seq.add(v);
    }

    public static class Adj {

        ArrayList<Integer> adj = new ArrayList<>();
        ArrayList<Integer> map = new ArrayList<>();
        boolean[] taken;
    }
}
