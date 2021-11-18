package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        by.martysiuk.springBootApp.models.User user = userService.showUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    // Converts ..models.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(
            by.martysiuk.springBootApp.models.User user,
            List<GrantedAuthority> authorities) {

        return new User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<>(setAuths);
    }

}