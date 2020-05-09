package Application.DTO.UsersDTO;

import Application.Entites.Users.Transporter;
import Application.Entites.Users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransporterDTO implements UserDTO {

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

    @JsonProperty("countOrders")
    private Integer countOrders;

    @Override
    public Long getId_user() {
        return id_user;
    }

    @Override
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Boolean getActive() {
        return isActive;
    }

    @Override
    public void setActive(Boolean active) {
        this.isActive = active;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    public void init(User user) {

        System.out.println("TransporterDTO - User - init");

        this.setId_user(user.getId_user());
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setSurname(user.getSurname());
        this.setActive(user.getIsLock());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole().getName().toString());

    }

//    @Override
    public TransporterDTO fromModel(Transporter transporter) {

        System.out.println("TransporterDTO - User - fromModel");

        TransporterDTO dto = new TransporterDTO();
        dto.init(transporter);
        return dto;
    }

}
