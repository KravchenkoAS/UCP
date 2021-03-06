package Application.Security.Service;

import Application.Entites.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id_user;

    private String login;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private Boolean active;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id_user, String login,
                         String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id_user = id_user;
        this.login = login;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        //
        this.name = null;
        this.surname = null;
        this.active = true;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
//                user.getId_user(),ucp
//                user.getLogin(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities

                user.getId_user(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id_user, user.id_user);
    }
}
