import java.util.Scanner;

import Product.ProductCatalog;
import Product.ProductFactory;
import User.AuthenticateUser;

public class Main {
   public static void main(String[] args) {
      AuthenticateUser authenticator = new AuthenticateUser();
      System.out.println("Welcome to Shopper App");
      System.out.println("Use below options to log in to the application.");
      Scanner scanner = new Scanner(System.in);
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
               loginFlag = false;
               break;
            default:
               System.out.println("Invalid option, please try again!");
               break;
         }
      } while (loginFlag);

      if (!loginFlag) {
         ProductCatalog catalog = new ProductCatalog();
         catalog.addProduct(ProductFactory.createProduct("Electronics", 1, "Laptop", 1000.00));
         catalog.addProduct(ProductFactory.createProduct("Clothing", 2, "T-shirt", 20.00));
      }
      scanner.close();
   }
}
