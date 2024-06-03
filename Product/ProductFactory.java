package Product;
public class ProductFactory {
   public static Product createProduct(String type, int id, String name, double price) {
      switch (type) {
         case "Electronics":
            return new Electronics(id, name, price);
         case "Clothing":
            return new Clothing(id, name, price);
         default:
            throw new IllegalArgumentException("Unknown product type");
      }
   }
}
