package util;

// Ghi dữ liệu vào file
import java.io.FileWriter;
// Các lỗi liên quan tới file, thư mục, quyền truy cập, ổ cứng
import java.io.IOException;

// Lấy ngày - giờ hiện tại
import java.time.LocalDateTime;
// Định dạng thời gian
import java.time.format.DateTimeFormatter;


public class LichSuHelper {
    private static final String FILE_NAME = "lichsu.txt";

    // GHI LỊCH SỬ
    public static void ghi(String noiDung) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);

            // Lấy thời gian hiện tại
            LocalDateTime now = LocalDateTime.now();

            // Định dạng thời gian
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Chuyển nội dung trong time thành String
            String time = now.format(formatter);

            // Ghi file
            fw.write("[" + time + "] " + noiDung + "\n");

            // Đóng file
            fw.close();

        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi lịch sử!");
        }
    }
}
