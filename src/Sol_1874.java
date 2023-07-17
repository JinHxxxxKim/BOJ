package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Sol_1874_Jin {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stk = new Stack<>();
        int cnt = 1;
        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine());
            // 첫 숫자
            if (stk.isEmpty()) {
                while (cnt <= num) {
                    stk.push(cnt++);
                    sb.append("+\n");
                }
            }
            if (stk.peek() == num) {
                stk.pop();
                sb.append("-\n");
            } else if (stk.peek() > num) {
                System.out.println("NO");
                return;
            } else {
                while (cnt <= num) {
                    stk.push(cnt++);
                    sb.append("+\n");
                }
                if (stk.peek() == num) {
                    stk.pop();
                    sb.append("-\n");
                }
            }

        }
        System.out.println(sb.toString());
    }
}
