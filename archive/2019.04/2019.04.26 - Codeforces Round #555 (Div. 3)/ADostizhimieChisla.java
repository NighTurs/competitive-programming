package task;

import java.io.PrintWriter;
import java.util.HashSet;

public class ADostizhimieChisla {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        HashSet<Integer> ints = new HashSet<>();
        ints.add(n);
        while (true) {
            n = n + 1;
            while (n % 10 == 0) {
                n /= 10;
            }
            if (ints.contains(n)) {
                break;
            }
            ints.add(n);
        }

        out.println(ints.size());
    }
}
