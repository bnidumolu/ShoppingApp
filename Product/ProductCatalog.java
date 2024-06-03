package Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
   private List<Product> products = new ArrayList<>();

   public void addProduct(Product product) {
      products.add(product);
   }

   public Product getProduct(int productId) {
      for (Product product : products) {
         if (product.getId() == productId) {
            return product;
         }
      }
      return null;
   }
}
