package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Sol_2023 {
    public static int N;
    public static LinkedList<Integer> ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = new LinkedList<>();

        for (int i = 2; i < 10; ++i) {
            if (isPrimeNumber(i)) {
                DFS(i, 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer ans : ans) {
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void DFS(int num, int currDigitCnt) {
        if (currDigitCnt == N) {
            ans.add(num);
            return;
        }
        for (int nextDigit = 1; nextDigit < 10; ++nextDigit) {
            int n = num * 10 + nextDigit;
            if (isPrimeNumber(n)) {
                DFS(n, currDigitCnt + 1);
            }
        }
    }

    public static boolean isPrimeNumber(int num) {
        if (num == 1) {
            return false;
        }
        int end = (int) Math.sqrt(num);
        for (int i = 1; i <= end; ++i) {
            if (i == 1) {
                continue;
            }
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
