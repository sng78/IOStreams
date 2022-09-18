import java.io.*;

public class Basket {
    protected static String[] products;
    protected static int[] prices;
    protected static int[] productsBuy;

    public Basket(String[] products, int[] prices) {
        Basket.products = products;
        Basket.prices = prices;
        productsBuy = new int[products.length]; //кол-во купленного
    }

    public void addToCart(int productNum, int amount) {
        productsBuy[productNum] += amount;
    }

    public void printCart() {
        System.out.println();
        System.out.println("Ваша корзина:");
        int sumProducts = 0;
        for (int i = 0; i < productsBuy.length; i++) {
            sumProducts += productsBuy[i] * prices[i];
            if (productsBuy[i] > 0) {
                System.out.println(products[i] + " " + productsBuy[i] +
                        " шт. по " + prices[i] + " руб. - всего " +
                        (productsBuy[i] * prices[i]) + " руб.");
            }
        }
        System.out.println("   ---");
        System.out.println("Итого " + sumProducts + " руб.");
    }

    public void saveTxt(File file) {
        try (FileWriter fr = new FileWriter(file, false)) {
            for (int i = 0; i < products.length; i++) {
                fr.write(productsBuy[i] + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        String line;
        int items = 0;
        Basket basket = new Basket(products, prices);
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            while ((line = br.readLine()) != null) {
                productsBuy[items] = Integer.parseInt(line);
                items += 1;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return basket;
    }
}