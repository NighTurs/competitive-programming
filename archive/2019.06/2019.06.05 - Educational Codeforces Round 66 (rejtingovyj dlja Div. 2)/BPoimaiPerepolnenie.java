package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BPoimaiPerepolnenie {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Long> a = new ArrayList<>();
        a.add(1L);
        long val = 0;
        long max = (1L << 32) - 1;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            long last = a.get(a.size() - 1);
            switch (s) {
                case "add":
                    val += last;
                    if (val > max) {
                        out.println("OVERFLOW!!!");
                        return;
                    }
                    break;
                case "for":
                    long next = in.nextLong();
                    if (last <= max) {
                        a.add(last * next);
                    } else {
                        a.add(last);
                    }
                    break;
                case "end":
                    a.remove(a.size() - 1);
                    break;
            }
        }
        out.println(val);
    }
}
