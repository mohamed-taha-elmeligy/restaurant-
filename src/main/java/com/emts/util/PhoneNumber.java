package com.emts.util;

import com.emts.exception.PhoneNumberException;
import java.util.List;
import java.util.Objects;

public class PhoneNumber {

    private String value ;
    private static final List<String> STARTS = List.of("015","012","011","010");

    public PhoneNumber(String value){
        validate(value);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public PhoneNumber setValue(String value) {
        validate(value);
        this.value = value;
        return this;
    }

    private void validate(String value){
        if (value == null || !value.matches("\\d{11}"))
            throw new PhoneNumberException("Phone number must be 11 digits");

        boolean validStart = STARTS.stream().anyMatch(value::startsWith);
        if (!validStart)
            throw new PhoneNumberException("Phone number must start with 010, 011, 012, or 015");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PhoneNumber that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
