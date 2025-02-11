
import java.util.List;

public class AccountantService {
    public static boolean registerAccountant(String name, String email, String phone, String password) {
        Accountant acc = new Accountant(0, name, email, phone, password);
        return AccountantDao.addAccountant(acc);
    }

    public static List<Accountant> getAllAccountants() {
        return AccountantDao.getAllAccountants();
    }
}
