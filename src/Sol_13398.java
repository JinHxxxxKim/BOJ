package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_13398 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        int[][] DP = new int[N][2];
        int max = Integer.MIN_VALUE;
        // DP[i][j]: i번째 수의 j==0이면 제가 X, j==1이면 제거
        // 1차 순회 => 아무것도 제거하지 않았을 때의 테이블 완성
        // 2차 순회 => 임의의 수 하나를 제거 할 때의 테이블 완성
        DP[0][0] = array[0];
        DP[0][1] = array[0];
        max = Math.max(max, DP[0][0]);
        max = Math.max(max, DP[0][1]);
        for (int i = 1; i < N; ++i) {
            DP[i][0] = Math.max(array[i], DP[i - 1][0] + array[i]);
            DP[i][1] = Math.max(DP[i - 1][0], DP[i - 1][1] + array[i]);
            max = Math.max(max, DP[i][0]);
            max = Math.max(max, DP[i][1]);
        }
        System.out.println(max);
    }
}
