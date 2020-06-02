package com.pumpit.app.data.local.converter;

import androidx.room.TypeConverter;

import com.pumpit.app.data.local.entity.Sex;

public class SexConverter {

    @TypeConverter
    public String fromSex(final Sex sex) {
        return String.valueOf(sex.ordinal());
    }

    @TypeConverter
    public Sex toSex(final String sex) {
        return Sex.values()[Integer.parseInt(sex)];
    }
}
