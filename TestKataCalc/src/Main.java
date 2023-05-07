import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws  Exception{
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        System.out.println(calc(str1));
    }
    public static String calc(String input) throws IOException {
        String[] massiv = input.split(" ");
        if (massiv.length < 3) {
            throw new IOException("Строка не является математической операцией");
        }
        var firstNumber = massiv[0];
        var secondNumber = massiv[2];
        var operationSign = massiv[1];


        if (isNumeric(firstNumber) && isNumeric(secondNumber)) {
            int firstNumberInt = Integer.parseInt(firstNumber);
            int secondNumberInt= Integer.parseInt(secondNumber);
            if (firstNumberInt > 10 || secondNumberInt > 10) {
                throw new IOException("Введенно число больше 10");
            }
            return String.valueOf(resultOperation(firstNumberInt, secondNumberInt, operationSign)) ;
        } else if (isRoman(firstNumber) && isRoman(secondNumber)){
            int firstNumberInt = toArab(firstNumber);
            int secondNumberInt = toArab(secondNumber);
            int result = resultOperation(firstNumberInt, secondNumberInt, operationSign);
            if (result <= 0) {
                throw new IOException("В римской системе нет отрицательных чисел");
            }
            return toRoman(result);
        } else {
            throw new IOException("Неверное число");
        }

    }
    public enum Roman {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);

        private final int arab;

        Roman(int arab) {
            this.arab = arab;
        }

        public int getArab() {
            return arab;
        }
    }
    public enum RomanTens {
        X(1), XX(2), XXX(3), XL(4), L(5), LX(6), LXX(7), LXXX(8), XC(9), C(10);

        private final int arab;

        RomanTens(int arab) {
            this.arab = arab;
        }

        public int getArab() {
            return arab;
        }
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static boolean isRoman(String strNum) throws IOException {
        if (strNum == null) {
            return false;
        }
        for (Roman rom : Roman.values()) {
            if (strNum.equalsIgnoreCase(rom.toString())) {
                return true;
            }
        }
        throw new IOException("Неверное число");
    }
    public static int toArab(String strRom) {
        int b = 0;
        for (Roman rom : Roman.values()) {
            if (strRom.equalsIgnoreCase(rom.toString())) {
                b = rom.getArab();
            }
        }
        return b;
    }
    public static int resultOperation(int firstNumber, int secondNumber, String operationSign) throws IOException {
        return switch (operationSign) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> throw new IOException("Строка не является математической операцией");
        };
    }
    public static String toRoman(int result) {
        int units = result % 10;
        int tens = result / 10 % 100;
        String unitsRoman = "";
        String tensRoman = "";
        for (Roman x: Roman.values()){
            if (units == x.getArab()) {
                unitsRoman = x.name();
            }
        }
        for (RomanTens x: RomanTens.values()){
            if (tens == x.getArab()) {
                tensRoman = x.name();
            }
        }
        String resultRoman = tensRoman + unitsRoman;
        return resultRoman;
    }
}