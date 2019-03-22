package task;

import java.io.PrintWriter;

public class ACHetniePodstroki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();

        long count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) - '0') % 2 == 0) {
                count += i + 1;
            }
        }

        out.println(count);
    }
}
