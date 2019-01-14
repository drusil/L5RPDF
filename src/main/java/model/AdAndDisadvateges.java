package model;

import java.util.ArrayList;
import java.util.List;

public class AdAndDisadvateges {

    private List<Advantage> advantages;
    private List<Advantage> disadvantages;

    public AdAndDisadvateges() {
        advantages = new ArrayList<>();
        disadvantages = new ArrayList<>();
    }

    public void addAdvantage(Advantage advantage) {

        checkIfPresent(advantage, advantages);
        advantages.add(advantage);

    }

    public List<Advantage> getAdvantages() {
        return advantages;
    }

    public List<Advantage> getDisadvantages() {
        return disadvantages;
    }


    public void addDisadvantage(Advantage disadvantage) {
        if (!checkIfPresent(disadvantage, disadvantages)) {
            disadvantages.add(disadvantage);
        }
    }

    private boolean checkIfPresent(Advantage advantage, List<Advantage> list) {
        for (Advantage elememt : list) {
            if (elememt.getName().toLowerCase().equals(advantage.getName().toLowerCase())) {
                return true;
            }

        }
        return false;
    }
}
