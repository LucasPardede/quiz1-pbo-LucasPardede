import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Membaca jumlah total data (N)
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        // 2. Membaca deret nilai (n1, n2, ..., nN)
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        // Membersihkan buffer
        sc.nextLine();

        // 3. Membaca kode kelompok (Cewek atau Cowok)
        if (!sc.hasNextLine()) return;
        String groupCode = sc.nextLine().trim();

        // Hitung total
        Solution2 sol = new Solution2();
        int result = sol.calculateTotal(n, values, groupCode);

        // Output hasil
        System.out.println(result);

        sc.close();
    }
}