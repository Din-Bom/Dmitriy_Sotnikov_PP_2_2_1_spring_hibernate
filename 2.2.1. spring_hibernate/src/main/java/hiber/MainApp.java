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

      User user1 = User.createUser("User1", "Lastname1", "user1@mail.ru");
      User user2 = User.createUser("User2", "Lastname2", "user2@mail.ru");
      User user3 = User.createUser("User2", "Lastname2", "user2@mail.ru");
      User user4 = User.createUser("User2", "Lastname2", "user2@mail.ru");

      Car car1 = new Car("BMW", 2020);
      Car car2 = new Car("Lexus", 2010);
      Car car3 = new Car("Lada", 2024);
      Car car4 = new Car("Moscvich", 1999);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

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
