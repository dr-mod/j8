public class Goody {

    Mage mage;

    public Goody(Mage mage) {
        this.mage = mage;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing our old friend Goody: " + Thread.currentThread().getName());
        this.mage.setGoody(this);
    }
}
