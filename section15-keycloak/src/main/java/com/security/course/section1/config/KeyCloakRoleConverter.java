package com.security.course.section1.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyCloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {


    @Override
    public Collection<GrantedAuthority> convert (final Jwt source) {
        final Map<String, Object> claims = (Map<String, Object>) source.getClaims ().get ("realm_access");

        if (claims == null || claims.isEmpty ()) {
            return List.of ();
        }

        final Collection<GrantedAuthority> roles = ((List<String>) claims.get ("roles"))
                .stream ()
                .map (roleName -> "ROLE_" + roleName)
                .map (SimpleGrantedAuthority::new)
                .collect (Collectors.toList ());

        return roles;
    }
}
