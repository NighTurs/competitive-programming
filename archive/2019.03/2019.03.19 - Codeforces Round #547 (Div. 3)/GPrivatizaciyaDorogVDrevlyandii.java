package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GPrivatizaciyaDorogVDrevlyandii {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Pair<Integer, List<Pair<Integer, Integer>>>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Pair<>(i, new ArrayList<>()));
        }


        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            a.get(from).sc.add(new Pair<>(to, i));
            a.get(to).sc.add(new Pair<>(from, i));
        }

        a.sort(Comparator.comparingInt(x -> x.sc.size()));

        int companies = a.get(n - 1 - k).sc.size();

        a.sort(Comparator.comparingInt(x -> x.fs));
        int[] color = new int[n];
        int[] ecolor = new int[n];
        Arrays.fill(color, -1);
        color[0] = -2;
        out.println(companies);
        dfs(companies, 0, a, color, ecolor);

        for (int i = 0; i < n - 1; i++) {
            out.print(ecolor[i]);
            out.print(' ');
        }
    }

    void dfs(int companies, int cur, List<Pair<Integer, List<Pair<Integer, Integer>>>> edges, int[] color, int[] ecolor) {
        int curColor = color[cur];
        int cl = 0;
        for (int i = 0; i < edges.get(cur).sc.size(); i++) {
            if (cl == curColor) {
                cl = (cl + 1) % companies;
            }
            Pair<Integer, Integer> p = edges.get(cur).sc.get(i);
            if (color[p.fs] != -1) {
                continue;
            }
            color[p.fs] = cl;
            ecolor[p.sc] = cl + 1;
            dfs(companies, p.fs, edges, color, ecolor);
            cl = (cl + 1) % companies;
        }
    }
}
