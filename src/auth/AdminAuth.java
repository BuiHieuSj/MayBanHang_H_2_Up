package auth;

public class AdminAuth {
    // Mật khẩu Admin
    private static final String PASSWORD = "admin@123";

    // Kiểm tra mật khẩu
    public static boolean dangNhap(String password) {
        // So sánh PASSWORD với password (so sánh nội dung chuỗi, không so sánh địa chỉ RAM)
        return PASSWORD.equals(password);
    }
}
