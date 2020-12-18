package com.hugh.stream.common;

import com.hugh.stream.model.Trader;

import java.util.ArrayList;
import java.util.List;

public class TraderTestDataBuilder {
    public static List<Trader> builderList() {
        List<Trader> result = new ArrayList<>();
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        result.add(raoul);
        result.add(mario);
        result.add(alan);
        result.add(brian);
        return result;
    }
}
