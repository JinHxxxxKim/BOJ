package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_17404 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[N][3];
        int[][] DP = new int[N][3];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
            table[i][2] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;

        // 첫번 째 색 고정
        // i==0: RED, i==1: GREEN, i==2: BLUE
        for (int i = 0; i < 3; ++i) {
            DP[0][0] = 1000;
            DP[0][1] = 1000;
            DP[0][2] = 1000;
            DP[0][i] = table[0][i];

            for (int j = 1; j < N; ++j) {
                for (int k = 0; k < 3; ++k) {
                    switch (k) {
                        case 0:
                            DP[j][k] = Math.min(DP[j - 1][1], DP[j - 1][2]) + table[j][k];
                            break;
                        case 1:
                            DP[j][k] = Math.min(DP[j - 1][0], DP[j - 1][2]) + table[j][k];
                            break;
                        case 2:
                            DP[j][k] = Math.min(DP[j - 1][0], DP[j - 1][1]) + table[j][k];
                            break;
                    }
                }
            }
            int temp = 0;
            switch (i) {
                case 0:
                    temp = Math.min(DP[N - 1][1], DP[N - 1][2]);
                    break;
                case 1:
                    temp = Math.min(DP[N - 1][0], DP[N - 1][2]);
                    break;
                case 2:
                    temp = Math.min(DP[N - 1][0], DP[N - 1][1]);
                    break;
            }
            ans = Math.min(ans, temp);
        }
        System.out.println(ans);
    }
}
