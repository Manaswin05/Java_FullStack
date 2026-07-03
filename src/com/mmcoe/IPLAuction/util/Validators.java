package com.mmcoe.IPLAuction.util;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validators {
    public static final Predicate<Double> isBudgetValid =
            budget -> budget != null && budget > 0;

    public static final Predicate<String> isNameValid =
            name -> name != null && !name.trim().isEmpty();

    public static final BiPredicate<Double, Double> canAffordPlayer =
            (teamBudget, bidAmount) -> teamBudget != null
                    && bidAmount != null
                    && teamBudget >= bidAmount
                    && bidAmount > 0;
                    
    public static final Predicate<Double> isBidValid =
            bid -> bid != null && bid > 0;

    private Validators() {} 
}
