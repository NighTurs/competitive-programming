package task;

import java.util.ArrayList;
import java.util.List;

public class MaxCutFree {

    int MAXN = 1001;
    List<List<Integer>> g;
    boolean[] used;
    int timer;
    int[] tin = new int[MAXN];
    int[] fup = new int[MAXN];
    List<List<Integer>> bridge;


    public int solve(int n, int[] a, int[] b) {
        tin = new int[MAXN];
        fup = new int[MAXN];
        used = new boolean[MAXN];
        timer = 0;
        g = new ArrayList<>();
        bridge = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
            bridge.add(new ArrayList<>());
        }

        for (int i = 0; i < a.length; i++) {
            g.get(a[i]).add(b[i]);
            g.get(b[i]).add(a[i]);
        }
        find_bridges(n);

        int res = 0;
        boolean[] forbid = new boolean[n];
        int[] numBridge = new int[n];
        for (int i = 0; i < n; i++) {
            numBridge[i] = bridge.get(i).size();
        }

        for (int t = 0; t < n; t++) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (forbid[i]) {
                    continue;
                }
                if (numBridge[i] < 2) {
                    res++;
                    forbid[i] = true;
                    for (int h = 0; h < bridge.get(i).size(); h++) {
                        int to = bridge.get(i).get(h);
                        if (!forbid[to]) {
                            forbid[to] = true;
                            for (int j = 0; j < bridge.get(to).size(); j++) {
                                numBridge[bridge.get(to).get(j)]--;
                            }
                        }
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                break;
            }
        }
        return res;
    }

    void isBridge(int from, int to) {
        bridge.get(from).add(to);
        bridge.get(to).add(from);
    }

    void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < g.get(v).size(); ++i) {
            int to = g.get(v).get(i);
            if (to == p) {
                continue;
            }
            if (used[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    isBridge(v, to);
                }
            }
        }
    }

    void find_bridges(int n) {
        timer = 0;
        for (int i = 0; i < n; ++i) {
            used[i] = false;
        }
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                dfs(i, - 1);
            }
        }
    }
}
