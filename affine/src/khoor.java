import java.util.Scanner;

public class khoor {
    public static void maHoa(String nhap, int k) {
        int leng = nhap.length();
        int[] chuoiNhap = new int[leng];

        char[] chars = nhap.toCharArray();
        for (int i = 0; i < leng; i++) {
            chuoiNhap[i] = (int) chars[i];
        }

        for (int i = 0; i < leng; i++) {
            if (chuoiNhap[i] != 32) {
                chuoiNhap[i] += k;
                // dung module
                if (((chuoiNhap[i] > 90 && chuoiNhap[i] < 91+k) || (chuoiNhap[i] > 122 && chuoiNhap[i] < 123+k))) {
                    chuoiNhap[i] -= 26;
                }
            }
        }
        for (int i = 0; i < leng; i++) {
            System.out.print((char) chuoiNhap[i]);
        }
    }

    public static void giaiMa(String nhap, int k) {
        int leng = nhap.length();
        int[] chuoiNhap = new int[leng];

        char[] chars = nhap.toCharArray();
        for (int i = 0; i < leng; i++) {
            chuoiNhap[i] = (int) chars[i];
        }

        for (int i = 0; i < leng; i++) {
            if (chuoiNhap[i] != 32) {
                chuoiNhap[i] -= k;
                // dùng module
                if (((chuoiNhap[i] < 64 && 63-k < chuoiNhap[i]) || (chuoiNhap[i] < 98 && chuoiNhap[i] > 97-k))) {
                    chuoiNhap[i] += 26;
                }
            }
        }
        for (int i = 0; i < leng; i++) {
            System.out.print((char) chuoiNhap[i]);
        }
    }


    public static void main(String[] args) {
        boolean a = true;
        System.out.println("Nhap k:");
        int k = new Scanner(System.in).nextInt();
        while (a) {
            System.out.println("------------Menu------------");
            System.out.println("1. Ma Hoa");
            System.out.println("2. Giai Ma");
            System.out.println("3. Exit");
            String s = new Scanner(System.in).nextLine();
            switch (s) {
                case "1":
                    System.out.println("Nhap chuoi can ma hoa: ");
                    String chuoiMaHoa = new Scanner(System.in).nextLine();
                    System.out.println("Chuoi sau khi ma hoa la:");
                    maHoa(chuoiMaHoa, k);
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Nhap chuoi can giai ma");
                    String chuoiGiaiMa = new Scanner(System.in).nextLine();
                    System.out.println("Chuoi sau khi giai ma la: ");
                    giaiMa(chuoiGiaiMa, k);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Exit");
                    a = false;
            }

        }
    }
}
