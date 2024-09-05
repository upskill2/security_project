package com.security.course.section1.controller.user;

import com.security.course.section1.dto.*;
import com.security.course.section1.model.Customer;
import com.security.course.section1.service.CustomerService;
import com.security.course.section1.utils.CustomerMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static com.security.course.section1.filter.JwtGeneratorFilter.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerService service;
    private final CustomerMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    @PostMapping (value = "/registerUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerResponse> registerUser (@RequestBody CustomerRequest customerRequest) {
        Customer customer = null;
        try {
            customer = service.saveCustomer (toCustomer (customerRequest));
        } catch (Exception e) {
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR)
                    .body (
                            CustomerResponse
                                    .builder ()
                                    .email (customerRequest.getEmail ())
                                    .status (CreateStatus.FAILED)
                                    .build ()
                    );
        }
        return ResponseEntity.ok (mapper.toCustomerResponse (customer));
    }

    private Customer toCustomer (CustomerRequest customerRequest) {
        return mapper.toCustomer (customerRequest);
    }

    @RequestMapping ("/user")
    public CustomerResponse getUserDetailsAfterLogin (Authentication authentication) {
        return mapper.toCustomerResponse (service.findByEmail (authentication.getName ()).orElse (null));
    }

    @RequestMapping (value = "/apiLogin", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> apiLogin (@RequestBody LoginRequest loginRequest) {
        String jwt = null;
        Authentication auth = UsernamePasswordAuthenticationToken.unauthenticated (loginRequest.username (), loginRequest.password ());
        final Authentication authentication = authenticationManager.authenticate (auth);

        final String secret = environment.getProperty (JWT_SECRET_KEY, JWT_DEFAULT_SECRET);
        SecretKey secretKey = Keys.hmacShaKeyFor (secret.getBytes (StandardCharsets.UTF_8));

        if (authentication != null && authentication.isAuthenticated ()) {
            jwt = Jwts.builder ()
                    .issuer ("Demo")
                    .subject ("JWT Token")
                    .claim ("username", authentication == null ? "1@admin.com" : authentication.getName ())
                    .claim ("authorities", authentication != null ? authentication.getAuthorities ().stream ().map (
                            GrantedAuthority::getAuthority).collect (Collectors.joining (",")) : "ADMIN")
                    .issuedAt (new Date ())
                    .expiration (new Date (new Date ().getTime () + 30000000))
                    .signWith (secretKey)
                    .compact ();
        }
        return ResponseEntity.ok ().header (JWT_HEADER, jwt)
                .body (new LoginResponse (HttpStatus.OK.getReasonPhrase (), jwt));
    }

}
