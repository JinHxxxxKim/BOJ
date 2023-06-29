package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol_1309 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 0열에 놓거나, 1열에 놓거나, 안놓거나 총3가지 경우의 수 존재
        int[][] DP = new int[N + 1][3];
        DP[1][0] = 1;
        DP[1][1] = 1;
        DP[1][2] = 1;
        for (int i = 2; i < N + 1; ++i) {
            DP[i][0] = (DP[i - 1][1] + DP[i - 1][2]) % 9901;
            DP[i][1] = (DP[i - 1][0] + DP[i - 1][2]) % 9901;
            DP[i][2] = (DP[i - 1][0] + DP[i - 1][1] + DP[i - 1][2]) % 9901;
        }
        System.out.println((DP[N][0] + DP[N][1] + DP[N][2]) % 9901);
    }
}
