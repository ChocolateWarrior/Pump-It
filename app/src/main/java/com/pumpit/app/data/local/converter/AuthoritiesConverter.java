package com.pumpit.app.data.local.converter;

import androidx.room.TypeConverter;

import com.pumpit.app.data.local.entity.Authority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthoritiesConverter {

    private static final String DELIMITER = ":";

    @TypeConverter
    public String fromAuthorities(final Set<Authority> authorities) {
        return authorities.stream()
                .map(Enum::ordinal)
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER));
    }

    @TypeConverter
    public Set<Authority> toAuthorities(final String authorities) {
        return Arrays.stream(authorities.split(DELIMITER))
                .map(authority -> Authority.values()[Integer.parseInt(authority)])
                .collect(Collectors.toSet());
    }
}
