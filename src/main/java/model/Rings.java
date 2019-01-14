package model;

import model.enums.RingsEnum;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rings implements Serializable {
    private List<ElementRing> elementRings;
    private RingOfVoid ringOfVoid;

    public Rings() {
        this.elementRings = Stream.of(RingsEnum.values())
                .map(ElementRing::new)
                .collect(Collectors.toList());
        this.ringOfVoid = new RingOfVoid();
    }

    public List<ElementRing> getElementRings() {
        return elementRings;
    }

    public RingOfVoid getRingOfVoid() {
        return ringOfVoid;
    }

    public ElementRing getRing(String ring) {
        return elementRings.stream()
                .filter(r -> r.getName().toLowerCase().equalsIgnoreCase(ring))
                .findFirst().orElse(new ElementRing());
    }

    public ElementRing getRingWithAtribute(String name) {
        return elementRings.stream()
                .filter(r -> r.getPhysicalAttributeName().equalsIgnoreCase(name))
                .findFirst().orElse(elementRings.stream()
                        .filter(r -> r.getMentalAttributeName().equalsIgnoreCase(name))
                        .findFirst().orElse(new ElementRing()));


    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        elementRings.forEach(sb::append);

        sb.append(ringOfVoid);
        return sb.toString();
    }
}
