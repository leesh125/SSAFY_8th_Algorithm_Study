import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11559 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][] vst;
	static int res;
	static boolean puyo;

	public static void main(String[] args) throws IOException {

		map = new char[12][6];
		vst = new boolean[12][6];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		res = 0;
		solve();
		System.out.println(res);

	}

	private static void solve() {

		while (true) {
			puyo = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (!vst[i][j] && map[i][j] != '.') {
						char color = map[i][j];
						bfs(new Location(i, j), color);
					}
				}
			}
			if (puyo) {
				res++;
			} else {
				return;
			}
			drop();
		}

	}

	private static void bfs(Location location, char color) {

		vst = new boolean[12][6];
		Queue<Location> que = new LinkedList<>();
		que.offer(location);
		vst[location.x][location.y] = true;
		int count = 1;

		while (!que.isEmpty()) {
			Location loc = que.poll();
			int x = loc.x;
			int y = loc.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 <= nx && nx < 12 && 0 <= ny && ny < 6 && !vst[nx][ny] && map[nx][ny] == color) {
					que.offer(new Location(nx, ny));
					vst[nx][ny] = true;
					count++;
				}
			}
		}

		if (count >= 4) {
			puyo = true;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (vst[i][j] && map[i][j] == color) {
						map[i][j] = '.';
					}
				}
			}
		}

	}

	private static void drop() {

		for (int y = 0; y < 6; y++) {
			for (int x = 10; x >= 0; x--) {
				if (map[x][y] != '.') {
					while (true) {
						int nx = x + 1;
						int ny = y;
						if (nx < 12 && map[nx][ny] == '.') {
							map[nx][ny] = map[x][y];
							map[x][y] = '.';
							x = nx;
						} else {
							break;
						}
					}
				}
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