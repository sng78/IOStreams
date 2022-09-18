import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] prices = {60, 40, 80};
        Basket basket = new Basket(products, prices);
        File file = new File("data.bin");

        if(file.exists()) {
            basket = Basket.loadFromBinFile(file);
            System.out.println("\nКорзина с покупками восстановлена из файла");
            basket.printCart();
        } else {
            System.out.println("\nСохраненной корзины c покупками нет");
        }

        System.out.println();
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + products[i] + " - " + prices[i] + " руб. за шт.");
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите 'end'");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1; // извлекаем № продукта
            int amount = Integer.parseInt(parts[1]); // извлекаем кол-во
            basket.addToCart(productNum, amount);
            basket.saveBin(file, basket);
        }

        basket.printCart();
    }
}