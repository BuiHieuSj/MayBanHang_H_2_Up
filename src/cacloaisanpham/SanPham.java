package cacloaisanpham;

import cacloaibaobi.LoaiBaoBi;
import java.io.Serializable;
import java.util.Objects;


public class SanPham implements Serializable {
    private static final long serialVersionUID = 1L;

    // Thuộc tính
    private String tenSP;
    private int giaBan;
    private final String loai;
    private LoaiBaoBi baoBi;
    private int soLuong;


    // Constructor
    public SanPham(String tenSP, int giaBan, String loai, LoaiBaoBi baoBi, int soLuong) {
        setTenSP(tenSP);
        setGiaBan(giaBan);

        if (loai == null || loai.trim().isEmpty()) {
            throw new IllegalArgumentException("Loại sản phẩm không hợp lệ");
        }
        this.loai = loai;

        setLoaiBaoBi(baoBi);
        setSoLuong(soLuong);
    }

    // Getter
    public String getTenSP() {
        return tenSP;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public String getLoai() {
        return loai;
    }

    public LoaiBaoBi getLoaiBaoBi() {
        return baoBi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Setter & Ràng buộc giá trị
    public void setTenSP(String tenSP) {
        if (tenSP != null && !tenSP.trim().isEmpty()) {
            this.tenSP = tenSP;
        } else {
            throw new IllegalArgumentException("Tên sản phẩm không hợp lệ");
        }
    }

    public void setGiaBan(int giaBan) {
        if (giaBan <= 0) {
            throw new IllegalArgumentException("Giá bán phải lớn hơn 0");
        }
        this.giaBan = giaBan;
    }

    public void setLoaiBaoBi(LoaiBaoBi baoBi) {
        if (baoBi == null) {
            throw new IllegalArgumentException("Bao bì không được null");
        }
        this.baoBi = baoBi;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("Số lượng không hợp lệ");
        }
        this.soLuong = soLuong;
    }

    // Hien thi
    @Override
    public String toString() {
        return getTenSP() + " | " + getGiaBan() + " | " + "Phân loại: "
                + getLoai() + " | " + "Bao bì: " + getLoaiBaoBi() + " | " + "Số lượng: " + getSoLuong();
    }

    @Override
    public int hashCode() {
        // Tạo mã đại diện cho từng sản phẩm
        return Objects.hash(tenSP, giaBan, loai, baoBi);

        /*
        int result = 1;

        result = 31 * result + tenSP.hashCode();
        result = 31 * result + Integer.hashCode(giaBan);
        result = 31 * result + loai.hashCode();
        result = 31 * result + baoBi.hashCode();

        return result;
         */
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
                return false;

        // Ép kiểu về SanPham
        /* Vì o đang có kiểu Object -> cần ép về kiểu SanPham -> truy cập đưuọc o2.giaBan, o2.tenSP,... */
        SanPham o2 = (SanPham) o;

        return giaBan == o2.giaBan && Objects.equals(tenSP, o2.tenSP) && Objects.equals(loai, o2.loai) && baoBi == o2.baoBi;

    }

}
