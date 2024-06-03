package ShoppingCart;

import Product.Product;

public class CartBuilder {
   private ShoppingCart cart;

   public CartBuilder() {
      cart = ShoppingCart.getInstance();
   }

   public CartBuilder addItem(Product product) {
      cart.addItem(product);
      return this;
   }

   public CartBuilder removeItem(Product product) {
      cart.removeItem(product);
      return this;
   }

   public ShoppingCart build() {
      return cart;
   }
   
}
