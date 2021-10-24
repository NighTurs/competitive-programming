package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class DVupsenPupsenI0 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Pair<Integer, Integer>> a = new ArrayList<>();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            b[i] = val;
            a.add(Pair.of(i, val));
        }
        a.sort(Comparator.comparingInt(x -> x.sc));
        int[] ans = new int[n];

        if (n % 2 == 1) {
            boolean found = false;
            int j = -1;
            for (int i = 2; i < n; i++) {
                if (a.get(i - 2).sc == a.get(i).sc) {
                    found = true;
                    ans[a.get(i - 2).fs] = 2;
                    ans[a.get(i - 1).fs] = -1;
                    ans[a.get(i).fs] = -1;
                    j = i;
                    break;
                }
            }
            if (found) {
                a.remove(j);
                a.remove(j - 1);
                a.remove(j - 2);
            } else {
                Pair<Integer, Integer> j1 = a.get(n - 1);
                Pair<Integer, Integer> j2 = a.get(n - 2);
                Pair<Integer, Integer> j3 = a.get(n - 3);
                int s1 = j1.sc + j2.sc;
                if (s1 == 0) {
                    Pair<Integer, Integer> z = j1;
                    j1 = j3;
                    j3 = z;
                    s1 = j1.sc + j2.sc;
                }
                int s2 = j3.sc;

                ans[j1.fs] = -s2;
                ans[j2.fs] = -s2;
                ans[j3.fs] = s1;
                a.remove(a.size() - 1);
                a.remove(a.size() - 1);
                a.remove(a.size() - 1);
            }
        }
        for (int i = 1; i < a.size(); i += 2) {
            Pair<Integer, Integer> j1 = a.get(i - 1);
            Pair<Integer, Integer> j2 = a.get(i);
            ans[j1.fs] += -j2.sc;
            ans[j2.fs] += j1.sc;
        }

        int ssum = 0;


        for (int i = 0; i < n; i++) {
            ssum += ans[i] * b[i];
            out.print(ans[i] + " ");
        }
        out.println();
//        out.println(ssum);



    }
}
