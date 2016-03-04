import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Mage mage = new Mage();
        new Goody(mage);
        System.gc();

        mage.setGoody(null);
        System.gc();
        Thread.sleep(100);
        System.out.println(mage.getGoody());

        mage.setGoody(null);
        System.gc();
        Thread.sleep(100);
        System.out.println(mage.getGoody());
    }
}
