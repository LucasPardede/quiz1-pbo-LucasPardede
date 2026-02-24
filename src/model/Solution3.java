// Solution3.java
// Logika sistem, model Laundry, dan semua menu per role
import java.util.*;

public class Solution3 {

    // ----------------------------
    // Model & Enum
    // ----------------------------
    public enum Status {
        TERDAFTAR, DIJEMPUT, DICUCI, SIAP_DIANTAR, SELESAI, DITOLAK
    }

    public static class Laundry {
        private final String id;
        private final String nama;
        private final String asrama;
        private final int jumlahItem;
        private Status status;
        private final int minggu; // minggu pendaftaran (untuk cek kuota)

        public Laundry(String nama, String asrama, int jumlahItem, int minggu) {
            this.id = UUID.randomUUID().toString().substring(0, 8); // simulasikan QR id
            this.nama = nama;
            this.asrama = asrama;
            this.jumlahItem = jumlahItem;
            this.status = Status.TERDAFTAR;
            this.minggu = minggu;
        }

        public String getId() { return id; }
        public String getNama() { return nama; }
        public String getAsrama() { return asrama; }
        public int getJumlahItem() { return jumlahItem; }
        public Status getStatus() { return status; }
        public void setStatus(Status s) { this.status = s; }
        public int getMinggu() { return minggu; }

        @Override
        public String toString() {
            return String.format("ID: %s | Nama: %s | Asrama: %s | Item: %d | Status: %s | Minggu: %d",
                    id, nama, asrama, jumlahItem, status, minggu);
        }
    }

    // ----------------------------
    // State & Storage (in-memory)
    // ----------------------------
    private final List<Laundry> daftarLaundry = new ArrayList<>();
    private final Scanner scanner;
    private int mingguSekarang = 1;

    public Solution3(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getCurrentWeek() {
        return mingguSekarang;
    }

    // ----------------------------
    // MENU MAHASISWA (BP03)
    // ----------------------------
    public void menuMahasiswa() {
        System.out.println("\n--- Menu Mahasiswa: Daftar Laundry ---");
        System.out.print("Masukkan Nama lengkap: ");
        String nama = scanner.nextLine().trim();
        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong.");
            return;
        }

        System.out.print("Masukkan Asrama: ");
        String asrama = scanner.nextLine().trim();
        if (asrama.isEmpty()) {
            System.out.println("Asrama tidak boleh kosong.");
            return;
        }

        // cek kuota 1x per minggu
        if (!cekKuota(nama, mingguSekarang)) {
            System.out.println("❌ Kuota minggu ini sudah digunakan. Coba minggu berikutnya.");
            return;
        }

        System.out.print("Masukkan jumlah item pakaian (angka): ");
        int jumlahItem = readIntSafe();
        if (jumlahItem <= 0) {
            System.out.println("Jumlah item tidak valid.");
            return;
        }

        // buat dan simpan laundry
        Laundry l = new Laundry(nama, asrama, jumlahItem, mingguSekarang);
        daftarLaundry.add(l);

        System.out.println("✅ Pendaftaran berhasil.");
        System.out.println("ID Laundry (simulasi QR): " + l.getId());
        System.out.println("Status: " + l.getStatus());
        System.out.println("Catatan: Simpan ID untuk tracking / driver / petugas.");
    }

    private boolean cekKuota(String nama, int minggu) {
        // jika sudah ada record siswa ini di minggu yang sama -> tolak
        for (Laundry l : daftarLaundry) {
            if (l.getNama().equalsIgnoreCase(nama) && l.getMinggu() == minggu) {
                return false;
            }
        }
        return true;
    }

    // ----------------------------
    // MENU DRIVER (BP04 - penjemputan / pengantaran)
    // ----------------------------
    public void menuDriver() {
        System.out.println("\n--- Menu Driver: Jemput / Antar ---");
        System.out.print("Masukkan ID Laundry: ");
        String id = scanner.nextLine().trim();
        Laundry l = findLaundryById(id);
        if (l == null) {
            System.out.println("ID tidak ditemukan.");
            return;
        }

        System.out.println("Data ditemukan: " + l);
        System.out.println("1. Update ke Dijemput");
        System.out.println("2. Update ke Siap Diantar");
        System.out.println("3. Update ke Diantar & Selesai");
        System.out.print("Pilih aksi: ");
        int pilih = readIntSafe();

        switch (pilih) {
            case 1 -> {
                l.setStatus(Status.DIJEMPUT);
                System.out.println("✅ Status diupdate ke DIJEMPUT");
            }
            case 2 -> {
                l.setStatus(Status.SIAP_DIANTAR);
                System.out.println("✅ Status diupdate ke SIAP_DIANTAR");
            }
            case 3 -> {
                l.setStatus(Status.SELESAI);
                System.out.println("✅ Status diupdate ke SELESAI (sudah diantar)");
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    // ----------------------------
    // MENU PETUGAS (proses laundry)
    // ----------------------------
    public void menuPetugas() {
        System.out.println("\n--- Menu Petugas: Proses Laundry ---");
        System.out.print("Masukkan ID Laundry: ");
        String id = scanner.nextLine().trim();
        Laundry l = findLaundryById(id);
        if (l == null) {
            System.out.println("ID tidak ditemukan.");
            return;
        }

        System.out.println("Data ditemukan: " + l);
        System.out.println("1. Update ke Dicuci");
        System.out.println("2. Update ke Siap Diantar");
        System.out.println("3. Tandai Ditolak (Kuota/masalah)");
        System.out.print("Pilih aksi: ");
        int pilih = readIntSafe();

        switch (pilih) {
            case 1 -> {
                l.setStatus(Status.DICUCI);
                System.out.println("✅ Status diupdate ke DICUCI");
            }
            case 2 -> {
                l.setStatus(Status.SIAP_DIANTAR);
                System.out.println("✅ Status diupdate ke SIAP_DIANTAR");
            }
            case 3 -> {
                l.setStatus(Status.DITOLAK);
                System.out.println("✅ Status diupdate ke DITOLAK");
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    // ----------------------------
    // MENU ADMIN (laporan & kontrol minggu)
    // ----------------------------
    public void menuAdmin() {
        System.out.println("\n--- Menu Admin ---");
        System.out.println("1. Tampilkan semua laundry");
        System.out.println("2. Laporan statistik singkat");
        System.out.println("3. Simulasi ganti minggu (reset kuota)");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");
        int pilih = readIntSafe();
        switch (pilih) {
            case 1 -> tampilkanSemua();
            case 2 -> laporanStatistik();
            case 3 -> {
                advanceWeek();
                System.out.println("✅ Minggu diganti. Sekarang minggu ke-" + mingguSekarang);
            }
            case 0 -> {}
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    // ----------------------------
    // Helper / Reporting
    // ----------------------------
    private Laundry findLaundryById(String id) {
        for (Laundry l : daftarLaundry) {
            if (l.getId().equalsIgnoreCase(id)) return l;
        }
        return null;
    }

    private void tampilkanSemua() {
        System.out.println("\n--- Semua Data Laundry ---");
        if (daftarLaundry.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }
        for (Laundry l : daftarLaundry) {
            System.out.println(l);
        }
    }

    private void laporanStatistik() {
        int total = daftarLaundry.size();
        long selesai = daftarLaundry.stream().filter(l -> l.getStatus() == Status.SELESAI).count();
        long diproses = daftarLaundry.stream().filter(l -> l.getStatus() != Status.SELESAI && l.getStatus() != Status.DITOLAK).count();
        long ditolak = daftarLaundry.stream().filter(l -> l.getStatus() == Status.DITOLAK).count();

        System.out.println("\n--- Laporan Statistik ---");
        System.out.println("Total Laundry : " + total);
        System.out.println("Selesai       : " + selesai);
        System.out.println("Dalam Proses  : " + diproses);
        System.out.println("Ditolak       : " + ditolak);
    }

    private void advanceWeek() {
        mingguSekarang++;
    }

    private int readIntSafe() {
        while (!scanner.hasNextInt()) {
            System.out.print("Masukkan angka yang benar: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }
}
