class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1.0;
       
        double[] sum = new double[K + W];
        sum[0] = 1.0;
        for (int i = 1;i < K + W;i ++) {
            if (i <= W) {
                if (i < K) { sum[i] = sum[i - 1] + sum[i - 1] / W; }
                else { sum[i] = sum[i - 1] + sum[K - 1] / W; }
            } else {
                if (i < K) { sum[i] = sum[i - 1] + (sum[i - 1] - sum[i - W - 1]) / W; }
                else { sum[i] = sum[i - 1] + (sum[K - 1] - sum[i - W - 1]) / W; }
            }
        }
        double all = sum[K + W - 1] - sum[K - 1];
        double part = sum[N] - sum[K - 1];
        return part / all;
    }
}
