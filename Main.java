import java.util.ArrayList;
import java.util.List;

// Исключение для демонстрации
class InvalidBalanceException extends RuntimeException {
    public InvalidBalanceException() {
        super("Недопустимый баланс!");
    }
}

class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private float balance;

    // Статическое поле
    private static int userCount = 0;

    public User() {
        userCount++;
    }

    public User(int id, String name, String login, String password, float balance) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        if (balance < 0) {
            throw new InvalidBalanceException(); // Инициализация исключения
        }
        this.balance = balance;
        userCount++;
    }

    // Конструктор копии
    public User(User other) {
        this(other.id, other.name, other.login, other.password, other.balance);
    }

    // Метод для возврата значения через вспомогательный класс
    public BalanceInfo getBalanceInfo() {
        return new BalanceInfo(this.balance);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setBalance(float balance) {
        if (balance < 0) {
            throw new InvalidBalanceException(); // Инициализация исключения
        }
        this.balance = balance;
    }

    // Статический метод для получения количества пользователей
    public static int getUserCount() {
        return userCount;
    }

    // Демонстрация использования this
    public void displayUserInfo() {
        System.out.println("ID: " + this.id + ", Имя: " + this.name + ", Баланс: " + this.balance);
    }
}

// Вспомогательный класс для возврата значения
class BalanceInfo {
    private float balance;

    public BalanceInfo(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }
}

class Slot {
    private int id;
    private String name;
    private int maxBet;
    private int minBet;
    private float payoutRatio;

    public Slot(int id, String name, int maxBet, int minBet, float payoutRatio) {
        this.id = id;
        this.name = name;
        this.maxBet = maxBet;
        this.minBet = minBet;
        this.payoutRatio = payoutRatio;
    }

    public String getName() {
        return name;
    }

    public float getPayoutRatio() {
        return payoutRatio;
    }

    // Метод для вывода информации о слоте
    public void displaySlotInfo() {
        System.out.println("Слот: " + this.name + ", Максимальная ставка: " + this.maxBet + ", Минимальная ставка: " + this.minBet);
    }

    @Override
    public String toString() {
        return "Слот: " + name + ", Максимальная ставка: " + maxBet + ", Минимальная ставка: " + minBet;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Работа с классом User:");

            List<User> users = new ArrayList<>();
            users.add(new User(1, "Алексей", "alex123", "alexpass", 1500.0f));
            users.add(new User(2, "Мария", "maria123", "mypass", 1000.0f));
            

            for (User user : users) {
                user.displayUserInfo();
                BalanceInfo balanceInfo = user.getBalanceInfo(); // Возврат значения через вспомогательный класс
                System.out.println("Баланс: " + balanceInfo.getBalance());
            }

            // Обновление баланса и демонстрация использования исключения
            users.get(0).setBalance(2000.0f);
            System.out.println("Обновленный баланс: " + users.get(0).getBalanceInfo().getBalance());

            // Попытка установки недопустимого баланса
            users.get(0).setBalance(-100); // Это вызовет исключение

        } catch (InvalidBalanceException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        try {
            System.out.println("\nРабота с классом Slot:");
            Slot slot1 = new Slot(1, "Lucky 7", 100, 10, 2.5f);
            System.out.println(slot1); // Перегрузка toString()

            System.out.println("\nОбщее количество пользователей: " + User.getUserCount()); // Статический метод

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
