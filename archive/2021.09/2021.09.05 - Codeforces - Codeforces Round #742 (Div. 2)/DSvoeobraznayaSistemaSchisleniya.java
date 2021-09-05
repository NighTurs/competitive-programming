package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DSvoeobraznayaSistemaSchisleniya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int sum = in.nextInt();
        int n = in.nextInt();

        List<Integer> d = new ArrayList<>();


        int mul = 1;
        while (sum > 0) {
            int a = sum % 10;
            for (int i = 0; i < a; i++) {
                d.add(mul);
            }
            sum -= a;
            sum /= 10;
            mul *= 10;
        }

        d.sort(Integer::compare);
        while (d.size() < n) {
            List<Integer> dd = new ArrayList<>();
            for (int i = 0; i < d.size(); i++) {
                if (d.get(i) == 1) {
                    continue;
                }
                int val = d.get(i);
                for (int h = 0; h < d.size(); h++) {
                    if (i != h) {
                        dd.add(d.get(h));
                    }
                }
                for (int h = 0; h < 10; h++) {
                    dd.add(val / 10);
                }
                break;
            }
            dd.sort(Integer::compare);
            d = dd;
        }
        for (int i = 0; i < n - 1; i++) {
            out.print(d.get(i) + " ");
        }
        int s = 0;
        for (int i = n - 1; i < d.size(); i++) {
            s += d.get(i);
        }
        out.println(s);
    }
}