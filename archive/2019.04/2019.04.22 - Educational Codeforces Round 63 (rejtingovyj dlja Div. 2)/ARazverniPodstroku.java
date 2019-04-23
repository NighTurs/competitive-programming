package task;

import java.io.PrintWriter;

public class ARazverniPodstroku {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int maxChar = 0;
        int pos = -1;
        for (int i = 0; i < s.length(); i++) {
            if (pos != -1 && s.charAt(i) < maxChar){
                out.println("YES");
                out.println(String.format("%d %d", pos + 1, i + 1));
                return;
            }
            if (s.charAt(i) > maxChar) {
                maxChar = s.charAt(i);
                pos = i;
            }
        }
        out.println("NO");
    }
}
