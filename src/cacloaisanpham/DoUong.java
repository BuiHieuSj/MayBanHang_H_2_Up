package cacloaisanpham;

import cacloaibaobi.LoaiBaoBi;

public class DoUong extends SanPham {
    // Hằng số cố định loại sản phẩm của class DoUong (không thể thay đổi)
    private static final String LOAI = "Đồ uống";

    public DoUong(String tenSP, int giaBan, LoaiBaoBi baoBi, int soLuong) {
        super(tenSP, giaBan, LOAI, baoBi, soLuong);
    }
}
