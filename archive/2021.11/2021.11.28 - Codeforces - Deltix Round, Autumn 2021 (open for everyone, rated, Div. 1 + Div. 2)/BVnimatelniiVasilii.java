package task;

import java.io.PrintWriter;

public class BVnimatelniiVasilii {

    public boolean d(char[] s, int p, char c) {
        if (p < 0 || p >= s.length) {
            return false;
        }
        return s[p] == c;
    }


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        char[] s = in.next().toCharArray();

        long ct = 0;
        for (int i = 0; i < n - 2; i++) {
            if (s[i] == 'a' && s[i + 1] == 'b' && s[i + 2] == 'c') {
                ct++;
            }
        }

        for (int i = 0; i < m; i++) {
            int p = in.nextInt() - 1;
            char c = in.nextChar();

            switch (s[p]) {
                case 'a':
                    if (d(s, p + 1, 'b') && d(s, p + 2, 'c')) {
                        ct--;
                    }
                    break;
                case 'b':
                    if (d(s, p - 1, 'a') && d(s, p + 1, 'c')) {
                        ct--;
                    }
                    break;
                case 'c':
                    if (d(s, p - 2, 'a') && d(s, p - 1, 'b')) {
                        ct--;
                    }
                    break;
            }

            switch (c) {
                case 'a':
                    if (d(s, p + 1, 'b') && d(s, p + 2, 'c')) {
                        ct++;
                    }
                    break;
                case 'b':
                    if (d(s, p - 1, 'a') && d(s, p + 1, 'c')) {
                        ct++;
                    }
                    break;
                case 'c':
                    if (d(s, p - 2, 'a') && d(s, p - 1, 'b')) {
                        ct++;
                    }
                    break;
            }
            s[p] = c;
            out.println(ct);
        }

    }
}
