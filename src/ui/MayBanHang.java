package ui;

import java.io.File;
import java.util.Scanner;

import quanly.QuanLySanPham;

import tien.GiaoDich;
import tien.QuanLyTien;
import util.InputHelper;

public class MayBanHang {

    private QuanLySanPham qlSP;
    private QuanLyTien qlTien;
    private GiaoDich giaoDich;

    private KhachHang khachHang;
    private Admin admin;

    private Scanner sc;

    // CONSTRUCTOR
    public MayBanHang() {

        qlSP = new QuanLySanPham();

        qlTien = new QuanLyTien();

        giaoDich = new GiaoDich(qlTien);

        khachHang = new KhachHang(qlSP, giaoDich);

        admin = new Admin(qlSP, qlTien);

        sc = new Scanner(System.in);
    }


    // CHẠY CHƯƠNG TRÌNH
    public void chay() {
        khoiTaoDuLieu();

        int choice;

        do {

            menu();

            choice = InputHelper.nhapSoNguyen(sc);

            switch (choice) {
                case 1:
                    khachHang.chay();
                    break;

                case 2:
                    admin.chay();
                    break;

                case 0:
                    qlSP.ghiFileObject("dataSP.dat");
                    System.out.println("Cảm ơn đã sử dụng máy bán hàng!");
                    break;

                default:
                    System.out.println("❌ Không hợp lệ!");
            }
        } while (choice != 0);
    }


    // KHỞI TẠO DỮ LIỆU
    private void khoiTaoDuLieu() {

        File f = new File("dataSP.dat");

        // Nếu đã có file object
        if (f.exists()) {
            qlSP.docFileObject("dataSP.dat");
        } else {
            System.out.println("Tạo dữ liệu từ file txt...");

            qlSP.docFile("dataSP.txt");

            qlSP.ghiFileObject("dataSP.dat");
        }
    }


    // MENU CHÍNH (MÁY BÁN HÀNG)
    private void menu() {

        System.out.println("\n===== MÁY BÁN HÀNG =====");

        System.out.println("1. Khách hàng");

        System.out.println("2. Admin");

        System.out.println("0. Thoát");

        System.out.print("\nChọn: ");
    }
}