package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User(new Car("BMW", 2020) ,"User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(new Car("Lexus", 2010),"User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(new Car("Lada", 2024),"User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User(new Car("Moscvich", 1999),"User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User user = userService.getUserByCar("BMW", 2020);
      if (user == null) {
         System.out.println("Пользователь с таким автомобилем не найден");
      } else {
         System.out.println(user);
      }

      context.close();
   }
}
