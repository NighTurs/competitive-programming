package task;

public class Euclid {

    @SuppressWarnings("AssignmentToMethodParameter")
    public static long gcd(long a, long b) {
        while (b != 0) {
            a %= b;
            long z = a;
            a = b;
            b = z;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
