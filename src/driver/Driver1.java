package driver;

import java.util.Scanner;
import model.Solution;

public class Driver1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Solution solusi = new Solution();

        while (true) {
            String kode = input.nextLine();

            if (kode.equals("END")) {
                break;
            }

            int porsi = Integer.parseInt(input.nextLine());

            solusi.tambahPesanan(kode, porsi);
        }

        solusi.cetakStruk();
        input.close();
    }
}