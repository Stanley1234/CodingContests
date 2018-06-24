class Solution {
    private static long[] DIGIT_NUM = {
            9,
            90 * 2,
            900 * 3,
            9000 * 4,
            90000 * 5,
            900000 * 6,
            9000000 * 7,
            90000000 * 8,
            (long)900000000 * 9,
            (long)(Integer.MAX_VALUE - 1000000000 + 1) * 10
    };

    private int pow10(int n) {
        if (n == 0) { return 1; }
        return 10 * pow10(n - 1);
    }

    private int getNthDigit(long num, int n) {
        while (n > 0) {
            num /= 10;
            n --;
        }
        return (int)num % 10;
    }

    public int findNthDigit(int n) {
        long n0 = n;

        int interval = 0;
        while (true) {
            if (n0 - DIGIT_NUM[interval] <= 0) {
                break;
            }
            n0 -= DIGIT_NUM[interval];
            interval ++;
        }
        n0 --;
        long num = n0 / (interval + 1) + (long)pow10(interval);
        n0 %= (interval + 1);
        n0 = (interval - n0);
        return getNthDigit(num, (int)n0);
    }
}
