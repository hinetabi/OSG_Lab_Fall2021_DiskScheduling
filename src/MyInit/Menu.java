package MyInit;

public class Menu {
    public static int choice(String[] ops, String msg)
    {
        System.out.println("==========" + msg + "===========");
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i+1) + "-" + ops[i]);
        }
        int choice = Validation.Inputter.inputIntegerInRange("Enter choice: ", 1, ops.length);
        return choice;
    }
}
