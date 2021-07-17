package task;

import java.io.PrintWriter;

public class EMinimaks {

    final int CHARS = 'z' - 'a' + 1;

    boolean canany(StringBuilder sb, int left, int first, int ctFirst) {
        if (left < 1) {
            return false;
        }
        return left / 2 >= ctFirst;
    }

    String doit(int[] ct, StringBuilder sb) {
        int sum = 0;
        for (int i = 0; i < CHARS; i++) {
            sum += ct[i];
        }

        int first = sb.charAt(0) - 'a';
        while (canany(sb, sum, first, ct[first])) {
            for (int i = 0; i < CHARS; i++) {
                if (i == first && sb.charAt(sb.length() - 1) - 'a' == first) {
                    continue;
                }
                if (ct[i] > 0) {
                    ct[i]--;
                    sb.append((char) ('a' + i));
                    sum--;
                    break;
                }
            }
        }
        while (sum > 0) {
            if (sb.charAt(sb.length() - 1) - 'a' != first) {
                sb.append((char) ('a' + first));
                sum--;
                ct[first]--;
            } else {
                for (int i = 0; i < CHARS; i++) {
                    if (i == first) {
                        continue;
                    }
                    if (ct[i] > 0) {
                        ct[i]--;
                        sb.append((char) ('a' + i));
                        sum--;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        int count = s.length();
        int[] ct = new int[CHARS];
        for (int i = 0; i < s.length(); i++) {
            ct[s.charAt(i) - 'a']++;
        }

        int first = -1;
        int numnum = 0;

        for (int st = 0; st < CHARS; st++) {

            if (ct[st] > 0) {
                numnum++;
            }

            if (first == -1 && ct[st] > 0) {
                first = st;
            }

            if (ct[st] == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append((char) ('a' + st));
                for (int i = 0; i < CHARS; i++) {
                    if (i == st) {
                        continue;
                    }
                    for (int h = 0; h < ct[i]; h++) {
                        sb.append((char) ('a' + i));
                    }
                }
                out.println(sb);
                return;
            }
            if (ct[st] == count) {
                out.println(s);
                return;
            }
        }


        for (int st = 0; st < CHARS; st++) {
            if (ct[st] == 0) {
                continue;
            }
            int len = count - 2;

            if (len / 2 < ct[st] - 2) {
                len++;
                StringBuilder sb = new StringBuilder();
                sb.append((char) ('a' + st));
                ct[st]--;
                for (int i = st + 1; i < CHARS; i++) {
                    if (ct[i] > 0) {
                        if (numnum == 2) {
                            while (ct[i] > 0) {
                                ct[i]--;
                                sb.append((char) ('a' + i));
                                len--;
                            }
                        } else {
                            len--;
                            ct[i]--;
                            sb.append((char) ('a' + i));
                        }
                        break;
                    }
                }
                while (len > 0) {
                    for (int i = 0; i < CHARS; i++) {
                        if (ct[i] == 0) {
                            continue;
                        }
                        if (i == sb.charAt(1) - 'a' && sb.charAt(sb.length() - 1) - 'a' == st) {
                            continue;
                        }
                        ct[i]--;
                        sb.append((char) ('a' + i));
                        len--;
                        break;
                    }
                }

                out.println(sb);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append((char) ('a' + st));
            sb.append((char) ('a' + st));
            ct[st] -= 2;
            out.println(doit(ct, sb));
            return;
        }
    }
}
