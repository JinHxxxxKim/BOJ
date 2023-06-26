package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    // 1학년

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numArray = new int[N];
        for (int i = 0; i < N; ++i) {
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        int objectNum = numArray[N - 1];
        int startNum = numArray[0];

        long[] DP = new long[21];
        Arrays.fill(DP, 0);
        long[] tempDP = new long[21];
        Arrays.fill(tempDP, 0);

        for (int i = 1; i < N - 1; ++i) {
            int plus = numArray[i];
            int minus = -1 * numArray[i];
            if (i == 1) { // 첫 순회
                if (startNum + plus <= 20) {
                    DP[startNum + plus] = DP[startNum] + 1;
                }
                if (startNum + minus >= 0) {
                    DP[startNum + minus] = DP[startNum] + 1;
                }
                tempDP = DP.clone();
                Arrays.fill(DP, 0);
                continue;
            }

            for (int j = 0; j <= 20; ++j) {
                if (tempDP[j] != 0) {
                    if (j + plus <= 20) {
                        DP[j + plus] += tempDP[j];
                    }
                    if (j + minus >= 0) {
                        DP[j + minus] += tempDP[j];
                    }
                }
            }

            tempDP = DP.clone();
            Arrays.fill(DP, 0);
        }
        System.out.println(tempDP[objectNum]);
    }
}
