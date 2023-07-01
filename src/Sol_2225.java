package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_2225 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] DP = new int[K + 1][N + 1];
        Arrays.fill(DP[0], 0);
        Arrays.fill(DP[1], 1);
        for (int i = 2; i <= K; ++i) {
            DP[i][0] = 1;
            for (int j = 1; j <= N; ++j) {
                DP[i][j] = (DP[i - 1][j] + DP[i][j - 1]) % 1000000000;
            }
        }
        System.out.println(DP[K][N]);
    }
}
