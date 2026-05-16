package cacloaisanpham;

import cacloaibaobi.LoaiBaoBi;

public class DoAn extends SanPham {
    // Hằng số cố định loại sản phẩm của class DoAn (không thể thay đổi)
    private static final String LOAI = "Đồ ăn";

    public DoAn(String tenSP, int giaBan, LoaiBaoBi baoBi, int soLuong) {
        super(tenSP, giaBan, LOAI, baoBi, soLuong);
    }
}
