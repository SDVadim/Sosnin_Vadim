package src.main.java.org.example;

/**
 * Интерфейс для пользователей
 */
public interface UserRepository {
  /**
   * Метод, который будет искать из всех пользователей человека с нужным MSISDN
   * @param msisdn
   * @return пользователя с данным MSISDN, или ничего, если такой пользователь не найден
   */
  User findByMsisdn(String msisdn);

  /**
   * Обновление пользователя с данным MSISDN
   * @param msisdn
   * @param user
   */
  void updateUserByMsisdn(String msisdn, User user);
}
