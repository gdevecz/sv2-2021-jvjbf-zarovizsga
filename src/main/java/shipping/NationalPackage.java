package shipping;

public class NationalPackage implements Transportable {

    private static final int BASE_PRICE = 1000;

    private static final int BREAKABLE_PRICE_MULTIPLIER = 2;

    private int weight;

    private boolean breakable;

    public NationalPackage(int weight, boolean breakable) {
        this.weight = weight;
        this.breakable = breakable;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        if (isBreakable()) {
            return BASE_PRICE * BREAKABLE_PRICE_MULTIPLIER;
        } else {
            return BASE_PRICE;
        }
    }
}
