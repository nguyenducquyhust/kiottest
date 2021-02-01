import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class thaythe {
    public static void maHoa(char[] chars, char[] chars1, String nhap) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < 25; i++) {
            map.put(chars[i], chars1[i]);
        }

        char[] chars2 = nhap.toCharArray();
        System.out.println("Chuoi sau ma hoa la: ");
        for (int i = 0; i < nhap.length(); i++) {
            System.out.print(map.get(chars2[i]));

        }
    }

    public static void giaiMa(char[] chars, char[] chars1, String nhap) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < 25; i++) {
            map.put(chars1[i], chars[i]);
        }
        char[] chars2 = nhap.toCharArray();
        System.out.println("Chuoi sau giai ma la: ");
        for (int i = 0; i < nhap.length(); i++) {
            System.out.print(map.get(chars2[i]));
        }
    }

    public static void main(String[] args) {
        char[] chars = new char[26];
        int k = 65;
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) k++;
        }
        System.out.println("Nhap lien tiep bang ma hoa cho kí tự từ A->Z");
        Scanner scanner = new Scanner(System.in);
        String bangMaHoa = scanner.nextLine();
        char[] chars1 = bangMaHoa.toCharArray();
        boolean b = true;
        while (b){
            System.out.println("--------Menu--------");
            System.out.println("    1. Ma hoa");
            System.out.println("    2. Giai ma");
            System.out.println("    3. Thoat");
            String chon= scanner.nextLine();
            switch (chon){
                case "1":
                    System.out.println("Nhap chuoi can ma hoa");
                    String maHoa= scanner.nextLine();
                    maHoa(chars, chars1, maHoa);
                    System.out.println();
                    break;
                case  "2":
                    System.out.println("Nhap chuoi can giai ma:");
                    String giaiMa= scanner.nextLine();
                    giaiMa(chars, chars1, giaiMa);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Ban da thoat");
                    b= false;
            }
        }

    }
}

