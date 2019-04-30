package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BKoshachePreobrazovanieFurfure {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer> ans = new ArrayList<>();
        boolean op = true;
        int count = 0;
        outer:
        while (true) {
            for (int i = 29; i >= 0; i--) {
                if (n == ((1 << i) - 1)) {
                    break outer;
                }
            }
            if (op) {
                for (int i = 29; i >= 0; i--) {
                    if ((1 << i) < n) {
                        if ((n & (1 << i)) == 0) {
                            ans.add(i + 1);
                            n = n ^ ((1 << (i + 1)) - 1);
                            break;
                        }
                    }
                }
            } else {
                n += 1;
            }
            count++;
            op = !op;
        }
        out.println(count);
        for (int i = 0; i < ans.size(); i++) {
            out.print(ans.get(i));
            out.print(' ');
        }
    }
}
