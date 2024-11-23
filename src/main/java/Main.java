import dao.impl.AddressDaoImpl;

public class Main {
    public static void main(String[] args) {
        var addressDao = AddressDaoImpl.getInstance();
        var res = addressDao.findAll();
        System.out.println(res);

    }
}
