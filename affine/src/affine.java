import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class affine {

    public static Map<Integer, Character> bangChuMaHoa(int a, int b, Map<Integer, Character> map) {

        Map<Integer, Character> map1 = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            int index = (a * i + b) % 26;
            map1.put(i, map.get(index));
        }
        return map1;
    }

    public static Map<Integer, Character> bangChuCaiThuong() {
        char[] chars = new char[26];
        Map<Integer, Character> map = new HashMap<>();
        int k = 65;
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) k++;
        }
        for (int i = 0; i < 26; i++) {
            map.put(i, chars[i]);
        }
        return map;
    }

    public static void maHoa(Map<Integer, Character> map1, Map<Integer, Character> map2, String nhap) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < 26; i++) {
            map.put(map1.get(i), map2.get(i));
        }
        char[] chars2 = nhap.toCharArray();
        System.out.println("Chuoi sau ma hoa la: ");
        for (int i = 0; i < nhap.length(); i++) {
            //System.out.println(chars2[i]);
            System.out.print(map.get(chars2[i]));
        }
    }

    public static void giaiMa(Map<Integer, Character> map1, Map<Integer, Character> map2, String nhap) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(map2.get(i), map1.get(i));
        }
        char[] chars2 = nhap.toCharArray();
        System.out.println("Chuoi sau giai ma la: ");
        for (int i = 0; i < nhap.length(); i++) {
            System.out.print(map.get(chars2[i]));
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Nhap a, b cho ham tao khoa ax+b: ");
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        Map<Integer, Character> map1 = new HashMap<Integer, Character>();
        Map<Integer, Character> map2 = new HashMap<Integer, Character>();
        map1 = bangChuCaiThuong();
        map2 = bangChuMaHoa(a, b, map1);
        boolean b1 = true;
        while (b1) {
            System.out.println("--------Menu--------");
            System.out.println("    1. Ma hoa");
            System.out.println("    2. Giai ma");
            System.out.println("    3. Thoat");
            String chon = scanner.nextLine();
            switch (chon) {
                case "1":
                    System.out.println("Nhap chuoi can ma hoa");
                    String maHoa = scanner.nextLine();
                    maHoa(map1, map2, maHoa);
                    //System.out.println();
                    break;
                case "2":
                    System.out.println("Nhap chuoi can giai ma:");
                    String giaiMa = scanner.nextLine();
                    giaiMa(map1, map2, giaiMa);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Ban da thoat");
                    b1 = false;

            }
        }
    }
}
