import java.io.*;

public class Basket implements Serializable {
    private final long serialVersionUID = 1L;
    protected String[] products;
    protected int[] prices;
    protected int[] productsBuy;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
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
            if (productsBuy[i] > 0) {
                sumProducts += productsBuy[i] * prices[i];
                System.out.println(products[i] + " " + productsBuy[i] +
                        " шт. по " + prices[i] + " руб. - всего " +
                        (productsBuy[i] * prices[i]) + " руб.");
            }
        }
        System.out.println("   ---");
        System.out.println("Итого " + sumProducts + " руб.");
    }

    public void saveBin(File file, Basket basket) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(basket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return basket;
    }
}