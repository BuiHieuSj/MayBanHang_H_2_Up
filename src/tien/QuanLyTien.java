package tien;

import java.io.*;
import java.util.Map;

public class QuanLyTien {

    private static final String FILE_NAME = "khotien.dat";

    private KhoTien khoTien;

    public QuanLyTien() {

        khoTien = docFile();

        // Nếu chưa có file
        if (khoTien == null) {
            khoTien = new KhoTien();

            luuFile();
        }
    }

    // THÊM TIỀN VÀO KHO (KHÁCH HÀNG)
    public void themToTien(int menhGia) {

        int soLuong = khoTien.getDsTien().get(menhGia);

        khoTien.getDsTien().put(menhGia, soLuong + 1);

        luuFile();
    }

    // BỚT TIỀN KHỎI KHO (KHÁCH HÀNG)
    public void botToTien(int menhGia) {

        int soLuong = khoTien.getDsTien().get(menhGia);

        khoTien.getDsTien().put(menhGia, soLuong - 1);

        luuFile();
    }

    // THÊM NHIỀU TỜ TIỀN (ADMIN)
    public boolean themToTien(int menhGia, int soLuong) {

        // Kiểm tra mệnh giá hợp lệ
        if (!khoTien.getDsTien().containsKey(menhGia)) {

            System.out.println("❌ Mệnh giá không hợp lệ!");

            return false;
        }

        // Kiểm tra số lượng
        if (soLuong <= 0) {

            System.out.println("❌ Số lượng phải > 0!");

            return false;
        }

        int hienTai = khoTien.getDsTien().get(menhGia);

        khoTien.getDsTien().put(menhGia, hienTai + soLuong);

        // Cập nhật số tiền mới trong kho
        luuFile();

        return true;
    }


    // BỚT NHIỀU TỜ TIỀN (ADMIN)
    public boolean botToTien(int menhGia, int soLuong) {

        // Kiểm tra mệnh giá
        if (!khoTien.getDsTien().containsKey(menhGia)) {

            System.out.println("❌ Mệnh giá không hợp lệ!");

            return false;
        }

        int hienTai = khoTien.getDsTien().get(menhGia);

        // Không đủ tiền để bớt
        if (hienTai < soLuong) {

            System.out.println("❌ Không đủ số lượng tờ tiền!");

            return false;
        }

        khoTien.getDsTien().put(menhGia, hienTai - soLuong);

        // Cập nhật số tiền mới trong kho
        luuFile();

        return true;
    }

    // HIỂN THỊ TIỀN CÒN TRONG KHO
    public void hienThiKhoTien() {

        System.out.println("\n===== KHO TIỀN =====");

        for (Map.Entry<Integer, Integer> entry : khoTien.getDsTien().entrySet()) {
            System.out.println(
                    entry.getKey() + "đ : " + entry.getValue() + " tờ");
        }
    }

    // KIỂM TRA THỐI TIỀN
    public boolean coTheThoiTien(int soTienCanThoi) {

        for (int tien : khoTien.getDsTien().descendingKeySet()) {

            int soLuongTrongKho = khoTien.getDsTien().get(tien);

            while (soTienCanThoi >= tien && soLuongTrongKho > 0) {

                soTienCanThoi -= tien;

                soLuongTrongKho--;
            }
        }

        return soTienCanThoi == 0;
    }

    // LƯU FILE
    private void luuFile() {

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME)
                    );

            oos.writeObject(khoTien);

            oos.close();

        } catch (Exception e) {

            System.out.println("Lỗi lưu file!");
        }
    }

    // ĐỌC FILE
    private KhoTien docFile() {

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME)
                    );

            KhoTien khoTien =
                    (KhoTien) ois.readObject();

            ois.close();

            return khoTien;

        } catch (Exception e) {

            return null;
        }
    }

    public KhoTien getKhoTien() {
        return khoTien;
    }
}