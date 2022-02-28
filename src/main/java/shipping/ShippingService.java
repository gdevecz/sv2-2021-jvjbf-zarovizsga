package shipping;

import java.util.*;
import java.util.stream.Collectors;

public class ShippingService {

    private List<Transportable> transportables = new ArrayList<>();

    public void addPackage(Transportable transportable) {
        transportables.add(transportable);
    }

    public List<Transportable> getPackages() {
        return transportables;
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return transportables.stream()
                .filter(i -> i.isBreakable() == breakable && i.getWeight() >= weight)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Long> tmp = transportables.stream()
                .collect(Collectors.groupingBy(Transportable::getDestinationCountry, Collectors.counting()));
        Map<String, Integer> result = new TreeMap<>();
        tmp.forEach((key, value) -> result.put(key, (value).intValue()));
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return transportables.stream()
                .filter(InternationalPackage.class::isInstance)
                .sorted(Comparator.comparing(t -> ((InternationalPackage) t).getDistance()))
                .toList();
    }
}

