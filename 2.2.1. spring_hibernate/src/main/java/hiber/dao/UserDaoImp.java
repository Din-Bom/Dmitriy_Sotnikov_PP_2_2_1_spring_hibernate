package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      String queryStr = "SELECT DISTINCT u FROM User u JOIN FETCH u.car";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(queryStr, User.class);

      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      String queryStr = "SELECT u FROM User u JOIN FETCH u.car WHERE u.car.model = :model AND u.car.series = :series";

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(queryStr, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);

      return query.getSingleResult();
   }

}
