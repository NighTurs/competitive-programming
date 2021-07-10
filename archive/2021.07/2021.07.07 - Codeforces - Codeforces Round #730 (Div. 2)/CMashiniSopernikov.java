package task;

import java.io.PrintWriter;

public class CMashiniSopernikov {

    double ERR = 1.e-10;

    double doit(int step, double p, double v, double a, double b, double c) {
        double sum = 0;
        sum += (step + 1) * p * c;
//        System.err.println(String.format("%.10f %.10f %.10f", a, b, c));
        if (a > 0) {
            if (a - v < ERR) {
                if (b > 0) {
                    sum += doit(step + 1, p * a, v, -1, b + a / 2, c + a / 2);
                } else {
                    sum += doit(step + 1, p * a, v, -1, -1, 1);
                }
            } else {
                if (b > 0) {
                    sum += doit(step + 1, p * a, v, a - v, b + v / 2, c + v / 2);
                } else {
                    sum += doit(step + 1, p * a, v, a - v, -1, c + v);
                }
            }
        }
        if (b > 0) {
            if (b - v < ERR) {
                if (a > 0) {
                    sum += doit(step + 1, p * b, v, a + b / 2, -1, c + b / 2);
                } else {
                    sum += doit(step + 1, p * b, v, -1, -1, 1);
                }
            } else {
                if (a > 0) {
                    sum += doit(step + 1, p * b, v, a + v / 2, b - v, c + v / 2);
                } else {
                    sum += doit(step + 1, p * b, v, -1, b - v, c + v);
                }
            }
        }
        return sum;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double v = in.nextDouble();
        out.println(String.format("%.10f", doit(0, 1, v, a, b, c)));

    }
}
