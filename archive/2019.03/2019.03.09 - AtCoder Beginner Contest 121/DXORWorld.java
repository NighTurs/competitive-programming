package task;

import java.io.PrintWriter;

public class DXORWorld {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        out.println(a ^ b);
//        int a[] = binary(in.nextLong());
//        int b[] = binary(in.nextLong());
//        StringBuilder sb = new StringBuilder();
//        long mult = 0;
//        for (int i = 0; i < a.length; i++) {
//            long changes = mult * 2 - a[i] + b[i];
//            long ones;
//            if (a[i] == 0) {
//                ones = (changes + 1) / 2;
//            } else {
//                ones = (changes + 2) / 2;
//            }
//            sb.append(ones % 2 == 0 ? '0' : '1');
//            mult = changes;
//        }
//        out.println(Long.valueOf(sb.toString(), 2));
    }

    int[] binary(long num) {
        String str = Long.toBinaryString(num);
        int b[] = new int[50];
        int cur = 49;
        for (int i = str.length() - 1; i >= 0; i--) {
            b[cur] = str.charAt(i) - '0';
            cur--;
        }
        return b;
    }
}
