package task;

import java.io.PrintWriter;

public class AMaksimIBiologiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int minD = Integer.MAX_VALUE;

        for (int st = 0; st < s.length() - 3; st++) {

            int sum = dist(s.charAt(st), 'A');
            sum += dist(s.charAt(st + 1), 'C');
            sum += dist(s.charAt(st + 2), 'T');
            sum += dist(s.charAt(st + 3), 'G');

            minD = Math.min(minD, sum);
        }
        out.println(minD);
    }

    int dist(char a, char b) {
        int aa = a - 'A';
        int bb = b - 'A';
        return Math.min(Math.min(Math.abs(bb - aa), Math.abs(bb - aa - 26)), Math.abs(bb + 26 - aa));
    }


}
