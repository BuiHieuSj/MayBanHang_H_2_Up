package quanly;

import java.io.*;
import java.util.*;
import cacloaisanpham.*;
import cacloaibaobi.*;


public class QuanLySanPham {

    private LinkedHashMap<String, SanPham> ds;

    // CONSTRUCTOR
    public QuanLySanPham() {
        ds = new LinkedHashMap<>();
    }

    // Đọc dữ liệu từ file .txt
    public void docFile(String fileName) {
        try {
            // Mở file để đọc
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            // Đọc từng dòng trong file
            while ((line = br.readLine()) != null) {

                // Bỏ qua dòng rỗng
                if (line.trim().isEmpty()) continue;

                // Tách dữ liệu theo dấu phẩy
                String[] parts = line.split(","); // Ex: Coca Cola,12000,DO_UONG,CHAI_NHUA,6
                /*
                parts[0] = Coca Cola
                parts[1] = 12000
                parts[2] = DO_UONG
                parts[3] = CHAI_NHUA
                parts[4] = 6
                 */

                // Kiểm tra: nếu dữ liệu không đủ 5 phần thì bỏ qua (tên sản phẩm, giá bán, loại, bao bì, số lượng)
                if (parts.length != 5) continue;

                // Lấy thông tin từng trường
                String ten = parts[0].trim(); // Tên sản phẩm
                int gia = Integer.parseInt(parts[1].trim()); // Giá bán (chuyển từ String sang integer)
                String loai = parts[2].trim(); // Loại sản phẩm
                LoaiBaoBi baoBi = LoaiBaoBi.valueOf(parts[3].trim()); // Loại bao bì
                int soLuong = Integer.parseInt(parts[4]. trim()); // Số lượng sản phẩm (chuyển từ String sang integer)

                // Tạo object
                SanPham sp = switch (loai) {
                    case "DO_AN" -> new DoAn(ten, gia, baoBi, soLuong);
                    case "DO_UONG" -> new DoUong(ten, gia, baoBi, soLuong);
                    case "VAT_DUNG" -> new VatDung(ten, gia, baoBi, soLuong);
                    default -> null;

                    // Tạo đối tượng tương ứng với từng loại sản phẩm
                };

                // Nếu tạo thành công thì thêm vào danh sách
                if (sp != null) {
                    themSanPham(sp);
                }
            }

            // Đóng file
            br.close();

        } catch (Exception e) {
            // Thông báo lỗi nếu có vấn đề khi đọc file
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }


    // Đọc file Object
    @SuppressWarnings("unchecked")
    public void docFileObject(String fileName) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            ds = (LinkedHashMap<String, SanPham>) ois.readObject();
            System.out.println("✅ Đọc file .dat thành công");

        } catch (Exception e) {
            System.out.println("⚠️ Không đọc được file .dat");
            ds = new LinkedHashMap<>();
        }
    }


    // Ghi file Object
    public void ghiFileObject(String fileName) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            oos.writeObject(ds);
            System.out.println("✅ Đã lưu file .dat");

        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file .dat");
        }
    }


    // Trả về danh sách sản phẩm
    public LinkedHashMap<String, SanPham> getDanhSach() {
        return ds;
    }

    // THÊM SẢN PHẨM
    public void themSanPham(SanPham spMoi) {
        // Tạo key
        String key = spMoi.taoKey();

        // Nếu đã tồn tại
        if (ds.containsKey(key)) {
            SanPham sp = ds.get(key);

            // Cộng số lượng
            sp.setSoLuong(sp.getSoLuong() + spMoi.getSoLuong());
        } else {
            // Thêm mới
            ds.put(key, spMoi);
        }

        // CHÚ Ý: XEM XÉT ĐỂ XÓA (VÌ BỊ GỌI LẠI NHIỀU)
        ghiFileObject("dataSP.dat");
    }


    // XÓA SẢN PHẨM
    public void xoaSanPham(int index) {
        ArrayList<SanPham> dsTam = new ArrayList<>(ds.values());

        if (index < 0 || index >= dsTam.size()) {
            System.out.println("❌ Không tồn tại sản phẩm!");
            return;
        }

        SanPham sp = dsTam.get(index);
        ds.remove(sp.taoKey());

        ghiFileObject("dataSP.dat");

        System.out.println("✅ Đã xóa: " + sp.getTenSP());
    }


    // HIỂN THỊ DANH SÁCH SẢN PHẨM
    public void hienThiSanPham() {

        System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");

        int i = 1;

        for (SanPham sp : ds.values()) {

            System.out.println(i++ + ". " + sp);
        }
    }


    // CẬP NHẬT SỐ LƯỢNG
    public void capNhatSoLuong(int index, int soLuongMoi) {

        if (index < 0 || index >= ds.size()) {

            System.out.println("❌ Không tồn tại sản phẩm!");

            return;
        }

        if (soLuongMoi < 0) {

            System.out.println("❌ Số lượng không hợp lệ!");

            return;
        }

        ArrayList<SanPham> dsTam = new ArrayList<>(ds.values());
        SanPham sp = dsTam.get(index);
        sp.setSoLuong(soLuongMoi);

        ghiFileObject("dataSP.dat");

        System.out.println("✅ Đã cập nhật số lượng!");
    }

    public ArrayList<SanPham> getDanhSachArray() {
        return new ArrayList<>(ds.values());
    }
}