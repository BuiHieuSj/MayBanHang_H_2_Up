package util;

import java.util.Scanner;

public class InputHelper {
    // NHẬP SỐ NGUYÊN
    public static int nhapSoNguyen(Scanner sc) {
        while (true) {
            try {
                int n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println("❌ Vui lòng nhập số nguyên: ");
            }
        }
    }

    // NHẬP SỐ DƯƠNG
    public static int nhapSoDuong(Scanner sc) {
        while (true) {
            int n = nhapSoNguyen(sc);

            if (n > 0) {
                return n;
            }

            System.out.println( "❌ Vui lòng nhập số lớn hơn 0: ");
        }
    }
}
