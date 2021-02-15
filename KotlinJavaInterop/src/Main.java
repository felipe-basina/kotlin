import java.util.List;

public class Main {

    public static void main(String[] args) {
        Customer phil = new Customer(0, "Phil");
        CustomerDatabase db = new CustomerDatabase();
        List<Customer> customers = db.getCustomers();
        // customers.add(phil); Cannot do that because the class Customer is based on an immutable list
        try {
            db.addCustomer(phil);
        } catch (IllegalAccessException iae) {
            System.out.println("Caught exception");
        }

        customers.forEach(System.out::println);

        CustomerDatabase.Companion.helloWorld();
        CustomerDatabase.helloWorld();
    }

}
