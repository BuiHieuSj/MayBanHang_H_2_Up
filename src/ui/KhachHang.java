package ui;

import java.util.Scanner;
import java.util.ArrayList;

import cacloaisanpham.SanPham;
import quanly.QuanLySanPham;
import tien.GiaoDich;
import util.InputHelper;


public class KhachHang {

    private QuanLySanPham qlSP;

    private GiaoDich giaoDich;

    private Scanner sc;


    // CONSTRUCTOR
    public KhachHang(QuanLySanPham qlSP, GiaoDich giaoDich) {

        this.qlSP = qlSP;

        this.giaoDich = giaoDich;

        sc = new Scanner(System.in);
    }


    // MENU KHÁCH HÀNG
    public void chay() {
        int choice;

        do {
            menu();
            choice = InputHelper.nhapSoNguyen(sc);

            switch (choice) {
                case 1:
                    giaoDienNapTien();
                    break;

                case 2:
                    giaoDienXemSanPham();
                    break;

                case 3:
                    if (!giaoDich.daNapTien()) {
                        System.out.println("❌ Vui lòng nạp tiền trước!");
                        break;
                    }
                    giaoDienMuaHang();
                    break;

                case 4:
                    if (!giaoDich.daNapTien()) {
                        System.out.println("❌ Chưa có giao dịch!");
                        break;
                    }
                    giaoDich.huyGiaoDich();
                    break;

                case 0:

                    // Nếu còn tiền chưa sử dụng
                    if (giaoDich.daNapTien()) {
                        System.out.println("\n⚠️ Giao dịch chưa hoàn tất!");
                        giaoDich.huyGiaoDich();
                    }

                    System.out.println("Thoát khách hàng!");
                    break;

                default:
                    System.out.println("❌ Không hợp lệ!");
            }
        } while (choice != 0);
    }


    // MENU
    private void menu() {

        System.out.println("\n===== KHÁCH HÀNG =====");

        // Hiển thị số dư khách hàng hiện có
        System.out.println("Số dư hiện tại: " + giaoDich.tongTienTam() + " VND");

        System.out.println("1. Nạp tiền");
        System.out.println("2. Xem sản phẩm");

        if (giaoDich.daNapTien()) {

            System.out.println("3. Mua hàng");

            System.out.println("4. Hủy giao dịch");
        }

        System.out.println("0. Thoát");

        System.out.print("\nChọn: ");
    }


    // GIAO DIỆN NẠP TIỀN
    private void giaoDienNapTien() {

        System.out.print("Nhập mệnh giá: ");

        int tien = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        giaoDich.napTien(tien);

        System.out.println("Tổng tiền hiện tại: " + giaoDich.tongTienTam() + " VND");
    }

    // GIAO DIỆN XEM SẢN PHẨM
    private void giaoDienXemSanPham() {
        ArrayList<SanPham> ds = qlSP.getDanhSachArray();

        int i = 1;
        for (SanPham sp : ds) {
            System.out.println(i++ + ". " + sp);
        }
    }


    // GIAO DIỆN MUA HÀNG
    private void giaoDienMuaHang() {

        giaoDienXemSanPham();

        System.out.print("\nNhập STT sản phẩm: ");
        int index = InputHelper.nhapSoDuong(sc); // Chỉ nhập được các số nguyên dương

        ArrayList<SanPham> ds = qlSP.getDanhSachArray();

        // Kiểm tra hợp lệ
        if (index < 1 || index > ds.size()) {
            System.out.println("❌ STT không hợp lệ!");
            return;
        }

        // Lấy sản phẩm
        SanPham sp = ds.get(index - 1);

        // Thanh toán
        giaoDich.thanhToan(sp);

        // Lưu file
        qlSP.ghiFileObject("dataSP.dat");
    }
}