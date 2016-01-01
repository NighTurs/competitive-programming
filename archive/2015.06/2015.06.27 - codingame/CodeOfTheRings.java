package task;

import java.io.IOException;
import java.io.PrintWriter;

public class CodeOfTheRings {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String str = null;
        try {
            str = decompose(in.reader.readLine(), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(str);
        out.println(str.length());
    }

    public int LET = 27;
    public int N = 30;

    public String solve(String s, String commands) {
        int[] spell = new int[s.length()];
        int[] dist = new int[LET];
        int[] bestDist = new int[LET];
        boolean[] chs = new boolean[LET];
        int ct = 0;

        for (int i = 0; i < s.length(); i++) {
            spell[i] = toIndex(s.charAt(i));
            if (!chs[spell[i]]) {
                chs[spell[i]] = true;
                ct++;
            }
        }

        int[] set = new int[ct];
        int kn = 0;
        for (int i = 0; i < LET; i++) {
            if (chs[i]) {
                set[kn++] = i;
            }
        }


        int min = Integer.MAX_VALUE;
        String best = "";

        for (int chunk = (ct + 6) / 7; chunk <= ct; chunk++) {
            for (int shift = 0; shift < chunk; shift++) {
                int n = (ct + chunk - 1) / chunk;
                int[] r = new int[n];
                r[0] = shift;
                for (int i = 1; i < n; i++) {
                    r[i] = r[i - 1] + chunk;
                }
                int fact = 1;
                for (int i = 2; i < n; i++)
                    fact *= i;

                for (int ii = 0; ii <= fact; ii++) {


                    int gg = 0;
                    for (int i = 0; i < n; i++) {
                        for (int h = 0; h < chunk && r[i] + h < ct; h++) {
                            dist[set[r[i] + h]] = gg;
                        }
                        if (gg == 0) {
                            gg = 1;
                        } else {
                            if (gg < 15) {
                                gg = N - gg;
                            } else {
                                gg = N - gg + 1;
                            }
                        }
                    }

                    String ans = play(spell, dist, commands);

                    if (ans.length() < min) {
                        min = ans.length();
                        best = ans;
                        bestDist = dist.clone();
                    }

                    next_permutation(r);
                }
            }
        }

        int COUNT = 500;
        boolean improve = true;

        while (improve && COUNT > 0) {
            improve = false;
            for (int i = 0; i < ct; i++) {
                int curLet = set[i];
                int prevLet = set[i - 1 == -1 ? ct - 1 : i - 1];
                int nextLet = set[i + 1 == ct ? 0 : i + 1];
                if (bestDist[curLet] != bestDist[prevLet]) {
                    int z = bestDist[curLet];
                    bestDist[curLet] = bestDist[prevLet];
                    String ans = play(spell, bestDist, commands);
                    if (ans.length() < min) {
                        min = ans.length();
                        best = ans;
                        improve = true;
                        break;
                    } else {
                        bestDist[curLet] = z;
                    }
                }
                if (bestDist[curLet] != bestDist[nextLet]) {
                    int z = bestDist[curLet];
                    bestDist[curLet] = bestDist[nextLet];
                    String ans = play(spell, bestDist, commands);
                    if (ans.length() < min) {
                        min = ans.length();
                        best = ans;
                        improve = true;
                        break;
                    } else {
                        bestDist[curLet] = z;
                    }
                }
            }
            COUNT--;
        }


        return best;
    }

    boolean next_permutation(int[] p) {
        for (int a = p.length - 2; a >= 0; a--) {
            if (p[a] < p[a + 1]) {
                for (int b = p.length - 1; ; b--) {
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; a++, b--) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String play(int[] spell, int[] dist, String commands) {
        int[] state = null;
        int curPos = 0;
        if (commands.length() > 0) {
            RET r = playCommands(commands);
            state = r.state;
            curPos = r.curPos;
        } else {
            state = new int[N];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < spell.length; i++) {
            int let = spell[i];
            int gt = dist[let];
            int lg = distArr(curPos, gt);
            char direction = lg > 0 ? '>' : '<';
            appendN(ans, direction, Math.abs(lg));
            int curSt = state[gt];
            int sf = distLet(curSt, let);
            char shift = sf > 0 ? '+' : '-';
            appendN(ans, shift, Math.abs(sf));
            appendN(ans, '.', 1);
            curPos = gt;
            state[gt] = let;
        }

        return ans.toString();
    }

    public StringBuilder appendN(StringBuilder sb, char c, int n) {
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb;
    }

    public int toIndex(char c) {
        if (c == ' ') {
            return 0;
        } else {
            return Character.getNumericValue(c) - Character.getNumericValue('A') + 1;
        }
    }

    public int distArr(int a, int b) {
        return cyclDist(a, b, N);
    }

    public int distLet(int a, int b) {
        return cyclDist(a, b, LET);
    }

    private int cyclDist(int a, int b, int m) {
        int sign = (int) Math.signum(b - a);
        int f = Math.min(a, b);
        int s = Math.max(a, b);

        int d = s - f;
        int r = f + m - s;


        if (d < r) {
            return sign * d;
        } else {
            return -1 * sign * r;
        }
    }

    int REP = 5;

    public String decompose(String s, String commands) {

        String best = solveGeneral(s, 1, commands);

        for (int i = 1; i <= s.length() / REP; i++) {
            int h = i;
            int ct = 2;
            boolean match = true;
            while (match && h + i - 1 < s.length()) {
                for (int j = 0; j < i; j++) {
                    if (s.charAt(j) != s.charAt(h + j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    h += i;
                    ct++;
                } else {
                    h -= i;
                    ct--;
                }
            }
            if (h + i >= s.length()) {
                h -= i;
                ct--;
            }
            if (ct >= 5) {
                StringBuilder cur = new StringBuilder();
                cur.append(solveGeneral(s.substring(0, i), ct, commands));
                if (i * ct < s.length()) {
//                    appendN(cur, '>', 6);
                    cur.append(decompose(s.substring(i * ct), cur.toString()));
                }
                if (best.length() > cur.length()) {
                    best = cur.toString();
                }
            }
        }

        return best;
    }

    public String solveGeneral(String str, int nn, String commands) {
        if (nn == 1) {
            return solve(str, commands);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(solve(str + str, commands));
            int dotsC = str.length();
            int curNum = 0;
            int firstHalfEnd = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '.') {
                    curNum++;
                }
                if (curNum == dotsC) {
                    firstHalfEnd = i;
                    break;
                }
            }
            String firstHalf = sb.substring(0, firstHalfEnd + 1);
            String secondHalf = sb.substring(firstHalfEnd + 1);
            int firstPos = simulatePosition(0, firstHalf);
            int secondPos = simulatePosition(firstPos, secondHalf);
            int counterPos = lastRightUsed(0, firstHalf) + 1;
            sb = new StringBuilder();
            sb.append(firstHalf);
            appendN(sb, '>', distArr(firstPos, counterPos));

            int left = nn - 1;
            while (left > 0) {
                int cur = Math.min(26, left);
                cyclic(sb, counterPos, firstPos, secondPos, secondHalf, cur);
                left -= cur;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < nn; i++) {
                stringBuilder.append(str);
            }

            String simple = solve(stringBuilder.toString(), commands);
            return simple.length() > sb.length() ? sb.toString() : simple;
        }
    }

    public StringBuilder cyclic(StringBuilder sb, int counterPos, int firstPos, int secondPos, String secondHalf, int n) {
        int dist = distLet(0, n);
        char sign = dist > 0 ? '+' : '-';
        appendN(sb, sign, Math.abs(dist));
        appendN(sb, '[', 1);
        dist = distArr(counterPos, firstPos);
        sign = dist > 0 ? '>' : '<';
        appendN(sb, sign, Math.abs(dist));
        sb.append(secondHalf);
        dist = distArr(secondPos, counterPos);
        sign = dist > 0 ? '>' : '<';
        appendN(sb, sign, Math.abs(dist));
        sb.append("-]");
        return sb;
    }

    public int lastRightUsed(int now, String steps) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < steps.length(); i++) {
            if (steps.charAt(i) == '<') {
                now--;
            } else if (steps.charAt(i) == '>') {
                now++;
            }
            max = Math.max(max, now);
        }
        return max;
    }

    public int simulatePosition(int now, String steps) {
        for (int i = 0; i < steps.length(); i++) {
            if (steps.charAt(i) == '<') {
                now--;
            } else if (steps.charAt(i) == '>') {
                now++;
            }
        }
        if (now < 0) {
            now += N;
        }
        return now;
    }

    public RET playCommands(String commands) {
        int[] state = new int[N];
        int curPos = 0;
//        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < commands.length()) {

            switch(commands.charAt(i)) {
                case '>':
                    curPos++;break;
                case '<':
                    curPos--;break;
                case '+':
                    state[curPos]++;break;
                case '-':
                    state[curPos]--;break;
                case '[':
                    if (state[curPos] == 0) {
                        int ii = i;
                        while (commands.charAt(ii) != ']') {
                            ii++;
                        }
                        i = ii;
                    }
                    break;
                case ']':
                    if (state[curPos] != 0) {
                        int ii = i;
                        while (commands.charAt(ii) != '[') {
                            ii--;
                        }
                        i = ii;
                    }
                    break;
//                case '.':
//                    if (curPos == ' ') {
//                        sb.append(" ");
//                    } else {
//                        sb.append((char) ('A' + state[curPos] - 1));
//                    }
//                    break;

            }
            if (curPos < 0) {
                curPos += N;
            }
            curPos = curPos % N;
            if (state[curPos] < 0) {
                state[curPos] += LET;
            }
            state[curPos] = state[curPos] % LET;
            i++;
        }

        RET r = new RET();
        r.state = state;
        r.curPos = curPos;
//        r.answer = sb.toString();
//        playCommandsLastS = commands;
//        playCommandsLastRET = r;
        return r;
    }

    public static class RET {
        public int[] state;
        public int curPos;
        public String answer;
    }
}
