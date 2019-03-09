package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSbalansirovannayaKomanda {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
        }
        Collections.shuffle(a);
        Collections.sort(a);
        int max = Integer.MIN_VALUE;
        int h = 0;
        for (int i = 0; i < n; i++) {
            while (h < n && a.get(h) - a.get(i) <= 5) {
                h++;
            }
            if (h - i > max) {
                max = h - i;
            }
        }
        out.println(max);
    }
}
