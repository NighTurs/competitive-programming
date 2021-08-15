package task;

import java.io.PrintWriter;

public class BMochaIKrasnoeISinee {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        char[] ans = new char[n];
        boolean found = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '?') {
                found = true;
                ans[i] = s.charAt(i);
                for (int h = i - 1; h >= 0; h--) {
                    char val = s.charAt(h);
                    if (val == '?') {
                        if (ans[h + 1] == 'R') {
                            ans[h] = 'B';
                        } else {
                            ans[h] = 'R';
                        }
                    } else {
                        ans[h] = val;
                    }
                }
                for (int h = i + 1; h < n; h++) {
                    char val = s.charAt(h);
                    if (val == '?') {
                        if (ans[h - 1] == 'R') {
                            ans[h] = 'B';
                        } else {
                            ans[h] = 'R';
                        }
                    } else {
                        ans[h] = val;
                    }
                }
                break;
            }
        }
        if (found) {
            print(ans, out);
        } else {
            ans[0] = 'R';
            for (int i = 1; i < n; i++) {
                if (ans[i - 1] == 'R') {
                    ans[i] = 'B';
                } else {
                    ans[i] = 'R';
                }
            }
            print(ans, out);
        }
    }

    void print(char[] ans, PrintWriter out) {
        for (int i = 0; i < ans.length; i++) {
            out.print(ans[i]);
        }
        out.println();
    }
}
