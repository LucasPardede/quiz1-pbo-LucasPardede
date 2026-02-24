import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Solution3 system = new Solution3(scanner);

        int pilihan;

        do {
            System.out.println("\n==================================");
            System.out.println("   SISTEM LAUNDRY DEL");
            System.out.println("   Minggu ke-" + system.getCurrentWeek());
            System.out.println("==================================");
            System.out.println("1. Login Mahasiswa");
            System.out.println("2. Login Driver");
            System.out.println("3. Login Petugas");
            System.out.println("4. Login Admin");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka: ");
                scanner.next();
            }

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    system.menuMahasiswa();
                    break;
                case 2:
                    system.menuDriver();
                    break;
                case 3:
                    system.menuPetugas();
                    break;
                case 4:
                    system.menuAdmin();
                    break;
                case 0:
                    System.out.println("Terima kasih menggunakan Laundry Del.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);

        scanner.close();
    }
}