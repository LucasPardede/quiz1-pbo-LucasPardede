package model;

import java.util.ArrayList;

public class Solution {

    private ArrayList<String> listMenu = new ArrayList<>();
    private ArrayList<Integer> listHarga = new ArrayList<>();
    private ArrayList<Integer> listPorsiTotal = new ArrayList<>();
    private int totalKeseluruhan = 0;

    public void tambahPesanan(String kode, int porsiButet) {
        String namaMenu = "";
        int hargaSatuan = 0;

        // Mencocokkan kode dengan Nama dan Harga (Harga dalam ribuan sesuai menu.jpeg)
        switch (kode) {
            case "NGS": namaMenu = "Nasi Goreng Spesial"; hargaSatuan = 15000; break;
            case "AP":  namaMenu = "Ayam Penyet";        hargaSatuan = 20000; break;
            case "SA":  namaMenu = "Sate Ayam";          hargaSatuan = 25000; break;
            case "BU":  namaMenu = "Bakso Urat";         hargaSatuan = 18000; break;
            case "MAP": namaMenu = "Mie Ayam Pangsit";   hargaSatuan = 15000; break;
            case "GG":  namaMenu = "Gado-Gado";          hargaSatuan = 15000; break;
            case "SAM": namaMenu = "Soto Ayam";          hargaSatuan = 17000; break;
            case "RD":  namaMenu = "Rendang Daging";     hargaSatuan = 25000; break;
            case "IB":  namaMenu = "Ikan Bakar";         hargaSatuan = 35000; break;
            case "NUK": namaMenu = "Nasi Uduk Komplit";  hargaSatuan = 20000; break;
            default: return;
        }

        // Porsi total = Butet + Ucok (1 + 2 = 3 kali porsi Butet)
        int porsiTotal = porsiButet * 3;

        listMenu.add(namaMenu);
        listHarga.add(hargaSatuan);
        listPorsiTotal.add(porsiTotal);

        totalKeseluruhan += (hargaSatuan * porsiTotal);
    }

    public void cetakStruk() {
        // Header sesuai gambar output.jpeg / image_00e496.png
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Menu", "Porsi", "Harga", "Total");
        System.out.println("============================================================");

        for (int i = 0; i < listMenu.size(); i++) {
            int subTotal = listHarga.get(i) * listPorsiTotal.get(i);
            // Menggunakan printf agar kolom sejajar rapi
            System.out.printf("%-20s %-10d %-10d %-10d\n", 
                listMenu.get(i), 
                listPorsiTotal.get(i), 
                listHarga.get(i), 
                subTotal);
        }

        System.out.println("============================================================");
        System.out.printf("%-42s %-10d\n", "Total Pembayaran", totalKeseluruhan);
    }
}

//