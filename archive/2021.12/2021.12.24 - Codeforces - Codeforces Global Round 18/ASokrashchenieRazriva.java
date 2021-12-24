package task;

import java.io.PrintWriter;

public class ASokrashchenieRazriva {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += in.nextLong();
        }
        if (sum % n == 0) {
            out.println(0);
        } else {
            out.println(1);
        }

    }
}
