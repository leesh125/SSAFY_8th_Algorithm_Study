import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15663 {

	static int N, M;
	static int[] arr;
	static int[] selected;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<String> res = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		visited = new boolean[N];
		selected = new int[M];
		per(0);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}

	private static void per(int depth) {
		if (depth == M) {
			sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]).append(" ");
			}
			if (!res.contains(sb.toString())) {
				res.add(sb.toString());
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = arr[i];
				per(depth + 1);
				visited[i] = false;
			}
		}
	}

}
