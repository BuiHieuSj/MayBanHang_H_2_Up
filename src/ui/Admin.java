package ui;

import quanly.QuanLySanPham;
import tien.QuanLyTien;

import cacloaisanpham.*;
import cacloaibaobi.LoaiBaoBi;
import util.InputHelper;

import java.util.Scanner;

public class Admin {

    private QuanLySanPham qlSP;

    private QuanLyTien qlTien;

    private Scanner sc;

    public Admin(QuanLySanPham qlSP, QuanLyTien qlTien) {
        this.qlSP = qlSP;
        this.qlTien = qlTien;

        sc = new Scanner(System.in);
    }

    // MENU ADMIN
    public void chay() {
        int choice;

        do {
            menu();
            choice = InputHelper.nhapSoNguyen(sc);

            switch (choice) {
                case 1:
                    qlTien.hienThiKhoTien();
                    break;

                case 2:
                    themTienVaoKho();
                    break;

                case 3:
                    botTienKhoiKho();
                    break;

                case 4:
                    qlSP.hienThiSanPham();
                    break;

                case 5:
                    themSanPham();
                    break;

                case 6:
                    xoaSanPham();
                    break;

                case 7:
                    capNhatSoLuong();
                    break;

                case 0:
                    System.out.println("Thoát admin!");
                    break;

                default:
                    System.out.println("❌ Không hợp lệ!");
            }
        } while (choice != 0);
    }

    // MENU ADMIN
    private void menu() {

        System.out.println("\n===== ADMIN =====");

        System.out.println("1. Xem kho tiền");
        System.out.println("2. Nạp tiền cho máy");
        System.out.println("3. Bớt tiền khỏi máy");

        System.out.println("4. Xem sản phẩm");
        System.out.println("5. Thêm sản phẩm");
        System.out.println("6. Xóa sản phẩm");
        System.out.println("7. Cập nhật số lượng sản phẩm");

        System.out.println("0. Thoát");

        System.out.print("\nChọn: ");
    }

    // GIAO DIỆN THÊM TIỀN VÀO KHO (ADMIN)
    private void themTienVaoKho() {

        System.out.print("Nhập mệnh giá: ");
        int menhGia = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        System.out.print("Nhập số lượng tờ: ");
        int soLuong = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        // Nhận giá trị đúng/sai của kết quả kiểm tra mệnh giá tiền khi Admin nạp vào
        boolean ketQuaKiemTra = qlTien.themToTien(menhGia, soLuong);

        // Hiển thị thông báo khi mệnh giá phù hợp
        if (ketQuaKiemTra) {
            System.out.println("✅ Đã thêm " + soLuong + " tờ " + menhGia + " VND");
        }
    }

    // GIAO DIỆN BỚT TIỀN KHỎI KHO (ADMIN)
    private void botTienKhoiKho() {

        System.out.print("Nhập mệnh giá: ");
        int menhGia = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        System.out.print("Nhập số lượng tờ: ");
        int soLuong = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        boolean ketQua = qlTien.botToTien(menhGia, soLuong);

        if (ketQua) {
            System.out.println("✅ Đã bớt " + soLuong + " tờ " + menhGia + "đ");
        }
    }

    // THÊM SẢN PHẨM
    private void themSanPham() {

        System.out.print("Tên sản phẩm: ");
        String ten = sc.nextLine();

        System.out.print("Giá bán: ");
        int gia = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        System.out.print("Số lượng: ");
        int soLuong = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        System.out.println("\nLoại sản phẩm:");
        System.out.println("1. Đồ ăn");
        System.out.println("2. Đồ uống");
        System.out.println("3. Vật dụng");

        int loai = InputHelper.nhapSoNguyen(sc); // Chỉ nhập được các số nguyên dương

        System.out.println("\nLoại bao bì:");

        LoaiBaoBi[] dsBaoBi = LoaiBaoBi.values();

        for (int i = 0; i < dsBaoBi.length; i++) {
            System.out.println((i + 1) + ". " + dsBaoBi[i]);
        }

        int bb = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        LoaiBaoBi baoBi = dsBaoBi[bb - 1];

        SanPham sp = null;

        switch (loai) {

            case 1:
                sp = new DoAn(ten, gia, baoBi, soLuong);
                break;

            case 2:
                sp = new DoUong(ten, gia, baoBi, soLuong);
                break;

            case 3:
                sp = new VatDung(ten, gia, baoBi, soLuong);
                break;

            default:
                System.out.println("❌ Loại không hợp lệ!");
                return;
        }

        qlSP.themSanPham(sp);
    }

    // XÓA SẢN PHẨM
    private void xoaSanPham() {

        qlSP.hienThiSanPham();

        System.out.print("\nNhập STT sản phẩm cần xóa: ");

        int index = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        qlSP.xoaSanPham(index - 1);
    }

    // CẬP NHẬT SỐ LƯỢNG
    private void capNhatSoLuong() {

        qlSP.hienThiSanPham();

        System.out.print("\nNhập STT sản phẩm: ");

        int index = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        System.out.print("Nhập số lượng mới: ");

        int soLuong = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        qlSP.capNhatSoLuong(index - 1, soLuong);
    }
}