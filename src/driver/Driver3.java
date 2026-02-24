// Driver3.java
// Entry point program Laundry Del - main menu & loop
import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solution3 system = new Solution3(scanner); // inject scanner agar konsisten

        int pilihan;
        do {
            System.out.println("\n========================================");
            System.out.println("  SISTEM LAUNDRY DEL (Console)");
            System.out.println("  Minggu ke-" + system.getCurrentWeek());
            System.out.println("========================================");
            System.out.println("1. Login Mahasiswa (Daftar Laundry)");
            System.out.println("2. Login Driver (Jemput / Antar)");
            System.out.println("3. Login Petugas (Proses Laundry)");
            System.out.println("4. Login Admin (Laporan / Pengaturan)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka: ");
                scanner.next();
            }
            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1 -> system.menuMahasiswa();
                case 2 -> system.menuDriver();
                case 3 -> system.menuPetugas();
                case 4 -> system.menuAdmin();
                case 0 -> System.out.println("Keluar. Terima kasih menggunakan Laundry Del.");
                default -> System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 0);

        scanner.close();
    }
}
