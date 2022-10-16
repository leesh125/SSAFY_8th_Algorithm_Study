import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21610 {

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int N, M;
	static int[][] map, move;
	static boolean[][] vst;
	static ArrayList<Location> lst;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		vst = new boolean[N + 1][N + 1];
		move = new int[M][2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}

		lst = new ArrayList<>();
		lst.add(new Location(N, 1));
		lst.add(new Location(N, 2));
		lst.add(new Location(N - 1, 1));
		lst.add(new Location(N - 1, 2));

		solve(lst);

		int res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] != 0) {
					res += map[i][j];
				}
			}
		}

		System.out.println(res);

	}

	private static void solve(ArrayList<Location> lst) {

		for (int m = 0; m < M; m++) {

			int d = move[m][0] - 1;
			int s = move[m][1];

			for (Location loc : lst) {
				for (int l = 0; l < s; l++) {
					int nx = loc.x + dx[d];
					int ny = loc.y + dy[d];
					if (nx < 1) {
						nx = N;
					}
					if (ny < 1) {
						ny = N;
					}
					if (nx > N) {
						nx = 1;
					}
					if (ny > N) {
						ny = 1;
					}
					loc.x = nx;
					loc.y = ny;
				}
			}

			for (Location loc : lst) {
				int x = loc.x;
				int y = loc.y;
				map[x][y]++;
				vst[x][y] = true;
			}
			
			for (Location loc : lst) {
				checkCross(loc.x, loc.y);
			}

			lst = new ArrayList<>();

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (!vst[i][j] && map[i][j] >= 2) {
						lst.add(new Location(i, j));
						map[i][j] -= 2;
					}
				}
			}

			for (int i = 1; i <= N; i++) {
				Arrays.fill(vst[i], false);
			}

		}

	}

	private static void checkCross(int x, int y) {

		int[] dx = { -1, -1, 1, 1 };
		int[] dy = { -1, 1, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 < nx && nx <= N && 0 < ny && ny <= N && map[nx][ny] != 0) {
				map[x][y]++;
			}
		}

	}

	static class Location {

		int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}