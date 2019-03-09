package task;

import java.io.PrintWriter;

public class ASeredinaKontesta {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();
        int m = (minutes(s2) + minutes(s1)) / 2;
        out.print(String.format("%02d", m / 60));
        out.print(":");
        out.print(String.format("%02d", m % 60));
    }

    public int minutes(String s) {
        return number(s, 0) * 60 + number(s, 3);
    }

    public int number(String s, int pos) {
        return (s.charAt(pos) - '0') * 10 + (s.charAt(pos + 1) - '0');
    }
}
