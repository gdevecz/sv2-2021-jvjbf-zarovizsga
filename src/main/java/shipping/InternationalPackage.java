package shipping;

public class InternationalPackage implements Transportable {

    private static final int BASE_PRICE = 1200;

    private static final int BREAKABLE_PRICE_MULTIPLIER = 2;

    private static final int PRICE_OF_A_KM = 10;

    private int weight;

    private boolean breakable;

    private String targetCountry;

    private int distance;

    public InternationalPackage(int weight, boolean breakable, String targetCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.targetCountry = targetCountry;
        this.distance = distance;
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
        int priceOfDistance = PRICE_OF_A_KM * distance;
        if (isBreakable()) {
            return BASE_PRICE * BREAKABLE_PRICE_MULTIPLIER + priceOfDistance;
        } else {
            return BASE_PRICE + priceOfDistance;
        }
    }

    @Override
    public String getDestinationCountry() {
        return targetCountry;
    }

    public int getDistance() {
        return distance;
    }
}
