package cacloaibaobi;

public enum LoaiBaoBi {
    // Mỗi gái trị ENUM sẽ có mô tả riêng
    CHAI_NHUA("Chai nhựa"), // CHAI_NHUA sẽ có mô tả = "Chai nhựa"
    CHAI_THUY_TINH("Chai thủy tinh"),
    HOP_GIAY("Hộp giấy"),
    LON("Lon"),
    TUYP("Tuýp"),
    TUI_NILON("Túi nilon");


    private final String moTa;

    LoaiBaoBi(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return moTa;
    }
}