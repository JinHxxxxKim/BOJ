package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol_2133 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N + 1];
        DP[0] = 1;
        DP[1] = 0;
        if (N > 1) {
            DP[2] = 3;
        }
        for (int i = 3; i <= N; ++i) {
            if (i % 2 == 1) {
                continue;
            }
            DP[i] = (DP[i - 2] * 3);
            for (int j = 4; i - j >= 0; j += 2) {
                DP[i] += DP[i - j] * 2;
            }
        }
        System.out.println(DP[N]);
    }
}
