package task;

import java.io.PrintWriter;

public class ALuntikIKontserti {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        out.println((a + b * 2 + c * 3) % 2);
    }
}
