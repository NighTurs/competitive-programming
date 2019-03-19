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
}
