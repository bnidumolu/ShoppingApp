package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import Product.Product;

public class ShoppingCart {
   private static ShoppingCart instance;
   private List<Product> items;

   private ShoppingCart() {
      items = new ArrayList<>();
   }

   public static ShoppingCart getInstance() {
      if (instance == null) {
         instance = new ShoppingCart();
      }
      return instance;
   }

   public void addItem(Product product) {
      items.add(product);
   }

   public void removeItem(Product product) {
      items.remove(product);
   }

   public void checkout() {
      System.out.println("Order processed successfully!");
   }

   public List<Product> getItems() {
      return items;
   }

   public double calculateTotal() {
      double totalCost = 0;
      for (Product p : items) {
         totalCost += p.getPrice();
      }
      return totalCost;
   }
}
