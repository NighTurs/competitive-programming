package task;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class InputReader {
    private static final int BUFFER_LENGTH = 1<<10;

    private InputStream stream;
    private byte[] buf = new byte[BUFFER_LENGTH];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    private int[] nextInts(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextInt();
        }
        return ret;
    }

    private int[][] nextIntTable(int n, int m) {
        int[][] ret = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[i][j] = nextInt();
            }
        }
        return ret;
    }

    private long[] nextLongs(int n) {
        long[] ret = new long[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextLong();
        }
        return ret;
    }

    private long[][] nextLongTable(int n, int m) {
        long[][] ret = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[i][j] = nextLong();
            }
        }
        return ret;
    }

    private double[] nextDoubles(int n) {
        double[] ret = new double[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextDouble();
        }
        return ret;
    }

    private double[][] nextDoubleTable(int n, int m) {
        double[][] ret = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[i][j] = nextLong();
            }
        }
        return ret;
    }

    private int nextC() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public char nextChar() {
        int c = nextC();
        while (isSpaceChar(c)) {
            c = nextC();
        }
        if ('a' <= c && c <= 'z') {
            return (char) c;
        }
        if ('A' <= c && c <= 'Z') {
            return (char) c;
        }
        throw new InputMismatchException();
    }

    public String next() {
        int c = nextC();
        while (isSpaceChar(c)) {
            c = nextC();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.append((char) c);
            c = nextC();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public int nextInt() {
        int c = skipWhileSpace();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextC();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c-'0';
            c = nextC();
        } while (!isSpaceChar(c));
        return res*sgn;
    }

    public long nextLong() {
        int c = skipWhileSpace();
        long sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextC();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c-'0';
            c = nextC();
        } while (!isSpaceChar(c));
        return res*sgn;
    }


    public double nextDouble() {
        // TODO: we should make some effort to handle this...
        return Double.valueOf(next());
    }

    public int skipWhileSpace() {
        int c = nextC();
        while (isSpaceChar(c)) {
            c = nextC();
        }
        return c;
    }

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
}