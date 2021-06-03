package task;

public class CombinUtils {

    public static long binom(int n, int k, long[] fact, long mod) {
        return (fact[n] * BinPow.binPow(fact[k], mod - 2, mod)) % mod * BinPow.binPow(fact[n - k], mod - 2, mod) % mod;
    }
}
