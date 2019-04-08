package task;

public class BinPow {

    @SuppressWarnings("AssignmentToMethodParameter")
    public static long binPow(long a, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res *= a;
            }
            a *= a;
            n >>= 1;
        }
        return res;
    }

    public static long binPow(long a, long n, long mod) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1;
        }
        return res;
    }
}
