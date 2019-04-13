package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlienRhyme {

    List<Vertex> vs = new ArrayList<>();
    List<List<Vertex>> byDepth = new ArrayList<>();
    public static final int DEPTH = 50;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        vs = new ArrayList<>();
        byDepth = new ArrayList<>();

        for (int i = 0; i <= DEPTH; i++) {
            byDepth.add(new ArrayList<>());
        }
        vs.add(new Vertex());

        for (int i = 0; i < n; i++) {
            String s = in.next();
            addString(vs.get(0), s, s.length() - 1, i, 0);
        }

        boolean[] taken = new boolean[n];
        int ans = 0;

        for (int i = DEPTH; i >= 1; i--) {
            for (Vertex v : byDepth.get(i)) {
                int count = 0;
                for (Integer id : v.ids) {
                    if (!taken[id]) {
                        count++;
                    }
                }
                if (count > 1) {
                    ans += 2;
                    int toTake = 2;
                    for (Integer id : v.ids) {
                        if (!taken[id] && toTake > 0) {
                            taken[id] = true;
                            toTake--;
                        }
                    }
                }
            }
        }

        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    public void addString(Vertex v, String s, int pos, int id, int depth) {
        v.ids.add(id);
        if (pos < 0) {
            return;
        }
        if (!v.adj.containsKey(s.charAt(pos))) {
            vs.add(new Vertex());
            byDepth.get(depth + 1).add(vs.get(vs.size() - 1));
            v.adj.put(s.charAt(pos), vs.get(vs.size() - 1));
        }
        addString(v.adj.get(s.charAt(pos)), s, pos - 1, id, depth + 1);
    }

    public static class Vertex {

        List<Integer> ids = new ArrayList<>();
        HashMap<Character, Vertex> adj = new HashMap<>();
    }
}
