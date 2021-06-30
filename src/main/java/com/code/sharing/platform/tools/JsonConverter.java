/*
package com.code.sharing.platform.tools;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Optional;

public class JsonConverter extends StdConverter<Long, Long> {
    @Override
    public Long convert(final Long value) {
        return Optional.ofNullable(value)
                .filter(object -> object != 0)
                .orElse(null);
    }
}*/
