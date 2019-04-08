package task;

import java.util.Random;

public class StringHash {

    private static int BASE = (int) 1e9;
    private static int[] PRIMES = {BASE + 21, BASE + 33, BASE + 87, BASE + 93, BASE + 97};
    int[][] hash;
    int[][] ppow;

    public StringHash(String str, int numHash) {
        hash = new int[str.length()][numHash];
        ppow = new int[str.length()][numHash];
        Random rnd = new Random();

        for (int nHash = 0; nHash < numHash; nHash++) {
            long h = 0;
            long p = 3 + rnd.nextInt(1000);
            long pow = 1;
            for (int i = 0; i < str.length(); i++) {
                h = (h + (str.charAt(i) - 'a' + 1) * pow) % PRIMES[nHash];
                hash[i][nHash] = (int) h;
                ppow[i][nHash] = (int) pow;
                pow = (pow * p) % PRIMES[nHash];
            }
        }
    }

    public boolean equals(int i1, int i2, int len) {
        for (int h = 0; h < hash[0].length; h++) {
            long h1 = hash[i1 + len - 1][h];
            if (i1 != 0) {
                h1 = (h1 - hash[i1 - 1][h] + PRIMES[h]) % PRIMES[h];
            }
            long h2 = hash[i2 + len - 1][h];
            if (i2 != 0) {
                h2 = (h2 - hash[i2 - 1][h] + PRIMES[h]) % PRIMES[h];
            }

            if ((i1 < i2 && (h1 * ppow[i2 - i1][h]) % PRIMES[h] != h2) ||
                    (i1 > i2 && h1 != (h2 * ppow[i1 - i2][h]) % PRIMES[h])) {
                return false;
            }
        }
        return true;
    }
}
