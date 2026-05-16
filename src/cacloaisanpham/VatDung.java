package cacloaisanpham;

import cacloaibaobi.LoaiBaoBi;

public class VatDung extends SanPham {
    // Hằng số cố định loại sản phẩm của class VatDung (không thể thay đổi)
    private static final String LOAI = "Vật dụng";

    public VatDung(String tenSP, int giaBan, LoaiBaoBi baoBi, int soLuong) {
        super(tenSP, giaBan, LOAI, baoBi, soLuong);
    }
}
