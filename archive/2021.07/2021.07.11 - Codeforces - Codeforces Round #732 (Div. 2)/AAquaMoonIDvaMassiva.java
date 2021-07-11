package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AAquaMoonIDvaMassiva {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        long suma = 0;
        long sumb = 0;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            suma += a[i];
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
            sumb += b[i];
        }
        if (suma != sumb) {
            out.println(-1);
            return;
        }

        List<Integer> is = new ArrayList<>();
        List<Integer> js = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (a[i] < b[i]) {
                js.add(i + 1);
                a[i]++;
            }
            while (a[i] > b[i]) {
                is.add(i + 1);
                a[i]--;
            }
        }
        out.println(is.size());
        for (int i = 0; i < is.size(); i++) {
            out.println(is.get(i) + " " + js.get(i));
        }
    }
}
