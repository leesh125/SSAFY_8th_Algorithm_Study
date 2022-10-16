import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684 {
	
	static int N, M, H, totalCnt, check;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[a][b + 1] = -1;
		}
		
		int res = -1;
		check = 0;
		for (int i = 0; i <= 3; i++) {
			totalCnt = i;
			solve(0);
			if (check > 0) {
				res = i;
				break;
			}
		}
		
		System.out.println(res);

	}
	
	private static void solve(int cnt) {
		
		if (cnt == totalCnt) {
			for (int i = 1; i <= N; i++) {
				if (!check(i)) {
					return;
				}
			}
			check++;
			return;
		}
		
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = -1;
					solve(cnt + 1);
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}
		
	}

	private static boolean check(int col) {
		
		int start = col;
		for (int i = 1; i <= H; i++) {
			col += map[i][col];
		}
		
		if (col == start) {
			return true;
		}
		
		return false;
	}

}