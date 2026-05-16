package tien;

import java.util.ArrayList;
import cacloaisanpham.SanPham;

public class GiaoDich {

    // Tiền khách đã nạp tạm
    private ArrayList<Integer> dsTienTam;

    private QuanLyTien quanLyTien;

    // Constructor
    public GiaoDich(QuanLyTien quanLyTien) {

        this.quanLyTien = quanLyTien;

        dsTienTam = new ArrayList<>();
    }

    // NẠP TIỀN
    public void napTien(int menhGia) {

        // Kiểm tra mệnh giá hợp lệ
        if (!quanLyTien.getKhoTien().getDsTien().containsKey(menhGia)) {
            System.out.println("❌ Mệnh giá không hợp lệ!");

            return;
        }

        dsTienTam.add(menhGia);

        System.out.println("Đã nạp " + menhGia + "đ");
    }

    // HIỂN THỊ TIỀN KHÁCH ĐÃ NẠP
    public void hienThiTienTam() {

        System.out.println("\n===== TIỀN ĐÃ NẠP =====");

        for (int tien : dsTienTam) {
            System.out.println(tien + "đ");
        }
    }

    // TỔNG TIỀN KHÁCH ĐÃ NẠP
    public int tongTienTam() {

        int tong = 0;

        for (int tien : dsTienTam) {
            tong += tien;
        }

        return tong;
    }

    // HỦY GIAO DỊCH
    public void huyGiaoDich() {

        System.out.println("\nĐã trả lại tiền cho khách:");

        for (int tien : dsTienTam) {
            System.out.println(tien + "đ");
        }

        dsTienTam.clear();
    }

    // THỐI TIỀN
    // =========================

    private void thoiTien(int soTienCanThoi) {

        int tongTienThoi = soTienCanThoi;

        System.out.println(
                "\n===== TIỀN THỐI ====="
        );

        for (int tien :
                quanLyTien.getKhoTien()
                        .getDsTien()
                        .descendingKeySet()) {

            int soLuongToTien = 0;

            while (soTienCanThoi >= tien
                    && quanLyTien.getKhoTien()
                    .getDsTien()
                    .get(tien) > 0) {

                quanLyTien.botToTien(tien);

                soTienCanThoi -= tien;

                soLuongToTien++;
            }

            // Nếu có dùng mệnh giá này
            if (soLuongToTien > 0) {
                System.out.println(tien + "đ : " + soLuongToTien + " tờ");
            }
        }

        System.out.println("\nTổng tiền thối: " + tongTienThoi + "đ");
    }

    // THANH TOÁN
    public void thanhToan(SanPham sanPham) {

        // Kiểm tra còn hàng không
        if (sanPham.getSoLuong() <= 0) {
            System.out.println("❌ Sản phẩm đã hết!");
            return;
        }

        int giaSanPham = sanPham.getGiaBan();

        int tongTien = tongTienTam();

        // Không đủ tiền
        if (tongTien < giaSanPham) {
            System.out.println("❌ Không đủ tiền!");
            return;
        }

        // Tiền cần thối
        int tienThoi = tongTien - giaSanPham;

        // Kiểm tra máy có đủ tiền thối không
        if (!quanLyTien.coTheThoiTien(tienThoi)) {
            System.out.println("❌ Máy không đủ tiền thối!");
            return;
        }

        // Đưa tiền khách vào kho
        for (int tien : dsTienTam) {
            quanLyTien.themToTien(tien);
        }

        // Thối tiền
        if (tienThoi > 0) {
            thoiTien(tienThoi);
        }

        // Giảm số lượng sản phẩm
        sanPham.setSoLuong(sanPham.getSoLuong() - 1);

        // Xóa tiền tạm
        dsTienTam.clear();

        System.out.println("\n✅ Mua " + sanPham.getTenSP() + " thành công!");
    }

    // KIỂM TRA ĐÃ NẠP TIỀN CHƯA
    public boolean daNapTien() {

        return !dsTienTam.isEmpty(); // trả về đúng khi danh sách lưu tiền tạm đã có
    }
}
