package com.security.course.section1.utils;

import com.security.course.section1.dto.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter (autoApply = true)
public class RoLeConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn (final Role role) {
        if (role == null) return null;
        return role.getRole ();
    }

    @Override
    public Role convertToEntityAttribute (final String role) {
        if (role == null) return null;

        return Stream.of (Role.values ())
                .filter (r -> r.getRole ().equals (role))
                .findFirst ().orElseThrow (IllegalArgumentException::new);
    }
}
