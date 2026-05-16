package tien;

import java.io.Serializable;
import java.util.TreeMap;

public class KhoTien implements Serializable {

    private static final long serialVersionUID = 1L;

    // Mệnh giá -> số lượng
    private TreeMap<Integer, Integer> dsTien;

    public KhoTien() {

        dsTien = new TreeMap<>();

        // Khởi tạo mệnh giá
        dsTien.put(10000, 0);
        dsTien.put(20000, 0);
        dsTien.put(50000, 0);
    }

    public TreeMap<Integer, Integer> getDsTien() {
        return dsTien;
    }

    public void setDsTien(TreeMap<Integer, Integer> dsTien) {
        this.dsTien = dsTien;
    }
}