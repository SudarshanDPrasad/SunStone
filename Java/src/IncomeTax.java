import java.util.Scanner;

public class IncomeTax {

    static int calculate = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Amount to calculate : ");
        int amount = scanner.nextInt();
        System.out.println("Income Tax is " + TaxToCalculate(amount) + "/-");
    }

    private static int TaxToCalculate(int amount) {
        if (amount <= 50000) calculate = 0;
        else if (amount <= 60000) calculate = (int) ((amount - 50000) * 0.1);
        else if (amount <= 150000) calculate = (int) (1000 + ((amount - 60000) * 0.2));
        else calculate = (int) (19000 + ((amount - 150000) * 0.3));
        return calculate;
    }
}
