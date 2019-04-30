package task;

import java.io.PrintWriter;

public class C2VozrastayushayaPodposledovatelnostUslozhnennayaVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int leftIdx = 0;
        int rightIdx = n - 1;
        int cur = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder();
        while (leftIdx <= rightIdx) {

            if (a[leftIdx] < a[rightIdx]) {
                if (cur < a[leftIdx]) {
                    sb.append('L');
                    cur = a[leftIdx];
                    leftIdx++;
                } else if (cur < a[rightIdx]) {
                    sb.append('R');
                    cur = a[rightIdx];
                    rightIdx--;
                } else {
                    break;
                }
            } else if (a[leftIdx] > a[rightIdx]) {
                if (cur < a[rightIdx]) {
                    sb.append('R');
                    cur = a[rightIdx];
                    rightIdx--;
                } else if (cur < a[leftIdx]) {
                    sb.append('L');
                    cur = a[leftIdx];
                    leftIdx++;
                } else {
                    break;
                }
            } else {
                if (cur >= a[leftIdx]) {
                    break;
                }
                if (leftIdx == rightIdx) {
                    sb.append('L');
                    cur = a[leftIdx];
                    leftIdx++;
                    continue;
                }
                int ctFirst = 0;
                for (int i = leftIdx + 1; i <= rightIdx; i++) {
                    if (a[i] > a[i - 1]) {
                        ctFirst++;
                    } else {
                        break;
                    }
                }
                int ctSecond = 0;
                for (int i = rightIdx - 1; i >= leftIdx; i--) {
                    if (a[i] > a[i + 1]) {
                        ctSecond++;
                    } else {
                        break;
                    }
                }
                int ct = Math.max(ctFirst, ctSecond);
                char c = ctFirst > ctSecond ? 'L' : 'R';
                for (int i = 0; i <= ct; i++) {
                    sb.append(c);
                }
                break;
            }
        }
        out.println(sb.length());
        out.println(sb);
    }
}

