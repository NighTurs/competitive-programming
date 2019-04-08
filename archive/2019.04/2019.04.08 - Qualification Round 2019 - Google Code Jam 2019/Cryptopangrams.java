package task;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cryptopangrams {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String trash = in.next();
        int l = in.nextInt();
        BigInteger[] a = new BigInteger[l];
        for (int i = 0; i < l; i++) {
            a[i] = new BigInteger(in.next());
        }

        Deque<BigInteger> message = new ArrayDeque<>();

        for (int i = 0; i < l - 1; i++) {
            BigInteger gcd = a[i].gcd(a[i + 1]);
            if (gcd.equals(a[i])) {
                continue;
            }

            message.addLast(a[i].divide(gcd));
            message.addLast(gcd);
            int h = i - 1;
            BigInteger last = message.getFirst();
            while (h >= 0) {
                message.addFirst(a[h].divide(last));
                last = message.getFirst();
                h--;
            }
            h = i + 1;
            last = gcd;
            while (h < l) {
                message.addLast(a[h].divide(last));
                last = message.getLast();
                h++;
            }
            break;
        }

        List<BigInteger> primes =
                new HashSet<>(message).stream().sorted(BigInteger::compareTo).collect(Collectors.toList());

        out.print(String.format("Case #%d: ", testNumber));
        for (BigInteger letter : message) {
            for (int i = 0; i < primes.size(); i++) {
                if (letter.equals(primes.get(i))) {
                    out.print((char) ('A' + i));
                }
            }
        }
        out.println();
    }
}
