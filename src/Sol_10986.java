package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_10986 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        long[] cnt = new long[M];
        st = new StringTokenizer(br.readLine());
        arr[0] = Long.parseLong(st.nextToken()) % M;
        for (int i = 1; i < N; ++i) {
            arr[i] = (arr[i-1] + Long.parseLong(st.nextToken())) % M;
        }
        Arrays.fill(cnt, 0);
        for (int i = 0; i < N; ++i) {
            cnt[(int)(arr[i] % M)]++;
        }
        long ans = 0;
        for (int i = 0; i < M; ++i) {
            if (cnt[i] > 1) {
                ans += (cnt[i]*(cnt[i]-1))/2;
            }
        }
        ans += cnt[0];
        System.out.println(ans);
    }
}