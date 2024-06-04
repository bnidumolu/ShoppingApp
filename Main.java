import java.util.List;
import java.util.Scanner;

import Payment.PaymentProcessor;
import Product.Product;
import Product.ProductCatalog;
import ShoppingCart.CartBuilder;
import ShoppingCart.ShoppingCart;
import User.AuthenticateUser;

public class Main {
   public static void main(String[] args) {
      AuthenticateUser authenticator = new AuthenticateUser();
      System.out.println("Welcome to Shopper App");
      System.out.println("Use below options to log in to the application.");
      Scanner scanner = new Scanner(System.in);
      ShoppingCart cart = ShoppingCart.getInstance();
      PaymentProcessor paymentProcessor = new PaymentProcessor();
      String username = "";
      boolean loginFlag = true;
      do {
         System.out.println("Press 1 if you are existing user, or 2 if you are a new user.");
         int loginOption = scanner.nextInt();
         scanner.nextLine();
         switch (loginOption) {
            case 1:
               System.out.println("Enter username:");
               username = scanner.nextLine();
               System.out.println("Enter Password:");
               String password = scanner.nextLine();
               if (authenticator.authenticate(username, password)) {
                  System.out.println("User authenticated, login successful!");
                  loginFlag = false;
               } else {
                  System.out.println("Wrong Username/Password. Please try again!");
               }
               break;
            case 2:
               System.out.println("Enter Email:");
               String email = scanner.nextLine();
               System.out.println("Enter username:");
               username = scanner.nextLine();
               System.out.println("Enter Password:");
               String newPassword = scanner.nextLine();
               authenticator.addUser(username, newPassword, email);
               System.out.println("Signup successful! Please proceed!!!");
               Logger.log("User " + username + " a new account created.");
               loginFlag = false;
               break;
            default:
               System.out.println("Invalid option, please try again!");
               break;
         }
      } while (loginFlag);

      if (!loginFlag) {
         Logger.log("User " + username + " logged in to Shopping App.");
         // Initialize ProductCatalog
         ProductCatalog catalog = new ProductCatalog();
         System.out.println("Please choose from the below available Products:");
         List<Product> allProducts = catalog.getAllProducts();

         for (Product product : allProducts) {
            System.out.println(product.getId() + " - " + product.getName());
         }
         CartBuilder cartBuilder = new CartBuilder();

         System.out.println("Please add items to the cart (enter product id to add, 0 to finish):");
         int input;
         boolean inputFlag = true;
         do {
            input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0) {
               inputFlag = false;
               break;
            }
            Product product = catalog.getProduct(input);
            if (product != null) {
               cartBuilder = cartBuilder.addItem(product);
               System.out.println(product.getName() + " added to cart.");
            } else {
               System.out.println("Product not found.");
            }
         } while (inputFlag);

         cart = cartBuilder.build();

         System.out.println("Cart Contents:");
         String cartItemsOp = "";
         List<Product> cartItems = cart.getItems();
         for (Product product : cartItems) {
            System.out.println(product.getName());
            cartItemsOp += product.getName() + ", ";
         }
         cartItemsOp.substring(0, cartItemsOp.length() - 2);
         Logger.log("User " + username + " selected these items: " + cartItemsOp);
         double totalAmount = cart.calculateTotal();

         System.out.println("Place order? (yes/no):");
         String orderIp = scanner.nextLine();
         if (orderIp.equals("yes")) {
            System.out.println("Payment processing...");
            paymentProcessor.processPayment(totalAmount);
            Logger.log("User " + username + " payment processed for amount: " + totalAmount);
            cart.checkout();
            Logger.log("User " + username + " order placed successfully!");
            cart = null;
         } else {
            Logger.log("User " + username + " order not placed successfully!");
            System.out.println("Order not placed.");
         }
      }
      scanner.close();
   }
}
