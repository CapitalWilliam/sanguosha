package cards.strategy;

import cards.Card;
import cards.Color;
import cards.Strategy;
import manager.IO;

import java.util.ArrayList;

public class ShunShouQianYang extends Strategy {

    public ShunShouQianYang(Color color, int number) {
        super(color, number, 1);
    }

    @Override
    public Object use() {
        if (!gotWuXie()) {
            IO.printAllCards(getTarget());
            String option;
            if (!getTarget().getEquipments().isEmpty() && !getTarget().getJudgeCards().isEmpty()) {
                option = IO.chooseFromProvided(getSource(),
                        "hand cards", "equipments", "judge cards");
            } else if (!getTarget().getEquipments().isEmpty()) {
                option = IO.chooseFromProvided(getSource(),
                        "hand cards", "equipments");
            } else if (!getTarget().getJudgeCards().isEmpty()) {
                option = IO.chooseFromProvided(getSource(),
                        "hand cards", "judge cards");
            } else {
                option = "hand cards";
            }
            Card c;
            if (option.equals("hand cards")) {
                c = IO.chooseCard(getSource(), getTarget().getCards());
            } else if (option.equals("equipments")) {
                c = IO.chooseCard(getSource(),
                        new ArrayList<>(getTarget().getEquipments().values()));
            } else {
                c = IO.chooseCard(getSource(), new ArrayList<>(getTarget().getJudgeCards()));
            }
            getTarget().loseCard(c);
            getSource().addCard(c);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "过河拆桥";
    }

    @Override
    public boolean needChooseTarget() {
        return true;
    }
}
