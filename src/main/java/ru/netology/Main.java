package ru.netology;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] prices = {60, 40, 80};
        Basket basket = new Basket(products, prices);
        ClientLog clientLog = new ClientLog();
//        File file = new File("basket.txt");
        File fileCsv = new File("log.csv");
        File fileJson = new File("basket.json");

        if (fileJson.exists()) {
            basket = Basket.loadFromJsonFile(fileJson);
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
            clientLog.log(productNum, amount);
        }

        Basket.saveJson(fileJson, basket);
        clientLog.exportAsCSV(fileCsv);
        basket.printCart();
    }
}