package Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
   private String filePath = "shoppingappProducts.txt";
   private List<Product> products = new ArrayList<>();

   public ProductCatalog() {
      getProductsFromFile();
   }

   public Product getProduct(int productId) {
      for (Product product : products) {
         if (product.getId() == productId) {
            return product;
         }
      }
      return null;
   }

   public List<Product> getAllProducts() {
      return this.products;
   }

   private void getProductsFromFile() {
      try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = br.readLine()) != null) {
            String[] productDetails = line.split(",");
            if (productDetails.length == 4) {
               int id = Integer.parseInt(productDetails[1]);
               String name = productDetails[2];
               double price = Double.parseDouble(productDetails[3]);
               Product product = ProductFactory.createProduct(productDetails[0], id, name, price);
               products.add(product);
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
