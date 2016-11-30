package com.savko.fifth.entity;

public enum DepositType {

    DEMAND("Demand"),
    FIXED("Fixed"),
    ESTIMATED("Estimation"),
    ACCUMULATION("Accumulation"),
    SAVING("Saving"),
    METAL("Metal");

    private final String value;

    DepositType(String value) {
        this.value = value;
    }

    public static DepositType getEnumValue(String value) {
        for (DepositType depositType : DepositType.values()) {
            if (depositType.value.equals(value)) {
                return depositType;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return value;
    }

}
