import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_3190 {

	// 상 우 하 좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N, time;
	static int[][] map, switchDir;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		switchDir = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switchDir[i][0] = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			if (str.equals("L")) {
				switchDir[i][1] = 3;
			} else if (str.equals("D")) {
				switchDir[i][1] = 1;
			}
		}
		time = 0;
		solve(new Location(1, 1));
		System.out.println(time + 1);

	}

	private static void solve(Location location) {

		Deque<Location> dq = new ArrayDeque<>();
		dq.offer(location);
		int x = location.x;
		int y = location.y;
		map[x][y] = 1;
		int idx = 1; // 처음엔 오른쪽 방향

		while (true) {
			for (int[] timeDir : switchDir) {
				if (timeDir[0] == time) {
					idx = (idx + timeDir[1]) % 4;
				}
			}
			int nx = x + dx[idx];
			int ny = y + dy[idx];
			if (0 < nx && nx <= N && 0 < ny && ny <= N && map[nx][ny] != 1) {
				if (map[nx][ny] == 0) {
					Location tail = dq.pollFirst();
					map[tail.x][tail.y] = 0;
				}
				dq.offer(new Location(nx, ny));
				map[nx][ny] = 1;
				time++;
				x = nx;
				y = ny;
			} else {
				return;
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