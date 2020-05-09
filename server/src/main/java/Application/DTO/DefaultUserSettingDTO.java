package Application.DTO;

import Application.Entites.RoleName;
import Application.Entites.Users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultUserSettingDTO {

    @JsonProperty("id_user")
    private Long id_user;

    @JsonProperty("username")
    private String username;

//    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonProperty("role")
    private String role;

    public DefaultUserSettingDTO() { }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void init(User user) {
        this.setId_user(user.getId_user());
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setSurname(user.getSurname());
        this.setActive(user.getIsLock());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole().getName().name());
    }

    public static DefaultUserSettingDTO fromModel(User user) {
        DefaultUserSettingDTO dto = new DefaultUserSettingDTO();
        dto.init(user);
        return dto;
    }

}
