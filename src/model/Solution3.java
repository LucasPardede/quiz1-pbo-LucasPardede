package model;

import java.util.*;

public class Solution3 {

    public enum Status {
        TERDAFTAR, DIJEMPUT, DICUCI, SIAP_DIANTAR, SELESAI, DITOLAK
    }

    public static class Laundry {
        private String id;
        private String nama;
        private String asrama;
        private int jumlahItem;
        private Status status;
        private int minggu;

        public Laundry(String nama, String asrama, int jumlahItem, int minggu) {
            this.id = UUID.randomUUID().toString().substring(0, 8);
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
        public void setStatus(Status status) { this.status = status; }
        public int getMinggu() { return minggu; }

        public String toString() {
            return "ID: " + id +
                   " | Nama: " + nama +
                   " | Asrama: " + asrama +
                   " | Item: " + jumlahItem +
                   " | Status: " + status +
                   " | Minggu: " + minggu;
        }
    }

    private List<Laundry> daftarLaundry = new ArrayList<Laundry>();
    private Scanner scanner;
    private int mingguSekarang = 1;

    public Solution3(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getCurrentWeek() {
        return mingguSekarang;
    }

    // =========================
    // MENU MAHASISWA
    // =========================
    public void menuMahasiswa() {

        System.out.println("\n--- Daftar Laundry ---");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Asrama: ");
        String asrama = scanner.nextLine();

        if (!cekKuota(nama)) {
            System.out.println("Kuota minggu ini sudah digunakan.");
            return;
        }

        System.out.print("Jumlah item: ");
        int jumlahItem = readIntSafe();

        Laundry laundry = new Laundry(nama, asrama, jumlahItem, mingguSekarang);
        daftarLaundry.add(laundry);

        System.out.println("Pendaftaran berhasil.");
        System.out.println("ID Laundry: " + laundry.getId());
    }

    private boolean cekKuota(String nama) {
        for (Laundry l : daftarLaundry) {
            if (l.getNama().equalsIgnoreCase(nama) && l.getMinggu() == mingguSekarang) {
                return false;
            }
        }
        return true;
    }

    // =========================
    // MENU DRIVER
    // =========================
    public void menuDriver() {

        System.out.print("Masukkan ID Laundry: ");
        String id = scanner.nextLine();

        Laundry l = findById(id);

        if (l == null) {
            System.out.println("ID tidak ditemukan.");
            return;
        }

        System.out.println("1. Update ke DIJEMPUT");
        System.out.println("2. Update ke SELESAI");
        System.out.print("Pilih: ");
        int pilih = readIntSafe();

        switch (pilih) {
            case 1:
                l.setStatus(Status.DIJEMPUT);
                break;
            case 2:
                l.setStatus(Status.SELESAI);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }

        System.out.println("Status sekarang: " + l.getStatus());
    }

    // =========================
    // MENU PETUGAS
    // =========================
    public void menuPetugas() {

        System.out.print("Masukkan ID Laundry: ");
        String id = scanner.nextLine();

        Laundry l = findById(id);

        if (l == null) {
            System.out.println("ID tidak ditemukan.");
            return;
        }

        System.out.println("1. Update ke DICUCI");
        System.out.println("2. Update ke SIAP_DIANTAR");
        System.out.print("Pilih: ");
        int pilih = readIntSafe();

        switch (pilih) {
            case 1:
                l.setStatus(Status.DICUCI);
                break;
            case 2:
                l.setStatus(Status.SIAP_DIANTAR);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }

        System.out.println("Status sekarang: " + l.getStatus());
    }

    // =========================
    // MENU ADMIN
    // =========================
    public void menuAdmin() {

        System.out.println("1. Tampilkan semua laundry");
        System.out.println("2. Ganti minggu");
        System.out.print("Pilih: ");
        int pilih = readIntSafe();

        switch (pilih) {
            case 1:
                tampilkanSemua();
                break;
            case 2:
                mingguSekarang++;
                System.out.println("Minggu sekarang: " + mingguSekarang);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private Laundry findById(String id) {
        for (Laundry l : daftarLaundry) {
            if (l.getId().equalsIgnoreCase(id)) {
                return l;
            }
        }
        return null;
    }

    private void tampilkanSemua() {
        if (daftarLaundry.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }
        for (Laundry l : daftarLaundry) {
            System.out.println(l);
        }
    }

    private int readIntSafe() {
        while (!scanner.hasNextInt()) {
            System.out.print("Masukkan angka yang benar: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}