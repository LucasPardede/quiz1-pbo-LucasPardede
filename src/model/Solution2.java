public class Solution2 {

    /**
     * Menghitung total nilai berdasarkan kelompok.
     * Pola:
     * - Index genap (0,2,4,...)  = Cewek
     * - Index ganjil (1,3,5,...) = Cowok
     */
    public int calculateTotal(int n, int[] values, String groupCode) {
        int total = 0;

        for (int i = 0; i < n; i++) {

            if (groupCode.equalsIgnoreCase("Cewek")) {
                if (i % 2 == 0) {
                    total += values[i];
                }

            } else if (groupCode.equalsIgnoreCase("Cowok")) {
                if (i % 2 != 0) {
                    total += values[i];
                }
            }
        }

        return total;
    }
}

//