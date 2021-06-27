package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class DUdalyayaDeliteli {

//    int N = 10000;
//
//    int[] mem = new int[N];

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Set<Long> except = new HashSet<>();
        for (long p2 = 2; p2 <= 1e9 + 10; p2 *= 4) {
//            out.println(p2);
            except.add(p2);
        }
        int n = in.nextInt();
        if (except.contains((long)n)) {
            out.println("Bob");
            return;
        }
        if (n % 2 == 0) {
            out.println("Alice");
        } else {
            out.println("Bob");
        }

//        Arrays.fill(mem, -1);
//        mem[1] = 0;
//        for (int i = 2; i < N; i++) {
//            boolean found = false;
//            for (int h = 2; h * h <= i; h++) {
//                if (i % h == 0) {
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                mem[i] = 0;
//            }
//        }
//
//        for (int i = 2; i < N; i++) {
//            if (mem[i] == -1) {
//                doit(i);
//            }
//        }
//
//        for (int i = 1; i < N; i++) {
//            if (i % 2 == 0 && mem[i] != 1) {
//                out.println(i + " " + mem[i]);
//            } else if (i % 2 == 1 && mem[i] != 0) {
//                out.println(i + " " + mem[i]);
//            }
//        }

    }

//    private void doit(int i) {
//        if (mem[i] != -1) {
//            return;
//        }
//        for (int h = 2; 2 * h <= i; h++) {
//            if (i % h == 0) {
//                doit(i - h);
//                if (mem[i - h] == 0) {
//                    mem[i] = 1;
//                    return;
//                }
//            }
//        }
//        mem[i] = 0;
//    }
}
