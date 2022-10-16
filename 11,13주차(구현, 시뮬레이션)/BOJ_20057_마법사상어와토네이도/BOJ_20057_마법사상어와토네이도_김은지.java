import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20057 {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 4][N + 4];
		for (int i = 2; i < N + 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 2; j < N + 2; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int start = (N + 4) / 2;
		solve(start, start);
		System.out.println(getCount());

	}

	private static void solve(int x, int y) {

		int cnt = 0;
		while (true) {
			for (int i = 0; i < 4; i++) {
				if (x == 2 && y == 2) {
					return;
				}
				if ((i == 0 || i == 2) && cnt != N - 1) {
					cnt++;
				}
				for (int c = 0; c < cnt; c++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					sandCal(x, y, nx, ny, i);
					x = nx;
					y = ny;
				}
			}
		}

	}

	static int[][] rx = { { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 }, { 0, 0, 1, 1, 1, 1, 2, 2, 3, 2 },
			{ -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 }, { 0, 0, -1, -1, -1, -1, -2, -2, -3, -2 } };
	static int[][] ry = { { 0, 0, -1, -1, -1, -1, -2, -2, -3, -2 }, { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 2, 2, 3, 2 }, { -1, 1, -1, 1, -2, 2, -1, 1, 0, 0 } };
	static int[] r = { 1, 1, 7, 7, 2, 2, 10, 10, 5 };

	private static void sandCal(int x, int y, int nx, int ny, int i) {

		map[nx][ny] += map[x][y];
		map[x][y] = 0;

		int sand = map[nx][ny];
		int a = sand;

		for (int n = 0; n < 9; n++) {
			int row = x + rx[i][n];
			int col = y + ry[i][n];
			int amount = (int) (r[n] * 0.01 * sand);
			map[row][col] += amount;
			a -= amount;
		}

		map[x + rx[i][9]][y + ry[i][9]] += a;
		map[nx][ny] = 0;

	}

	private static int getCount() {

		int count = 0;
		for (int i = 0; i < N + 4; i++) {
			for (int j = 0; j < N + 4; j++) {
				if (!(2 <= i && i < N + 2 && 2 <= j && j < N + 2) && map[i][j] != 0) {
					count += map[i][j];
				}
			}
		}
		return count;

	}

}