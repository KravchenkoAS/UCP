package Application.DTO.UsersDTO;

import Application.Entites.RoleName;
import Application.Entites.Users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface UserDTO {

    Long getId_user();

    void setId_user(Long id_user);

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    Boolean getActive();

    void setActive(Boolean active);

    String getRole();

    void setRole(String role);
//    public Integer getCountOrders() {
//        return countOrders;
//    }
//
//    public void setCountOrders(Integer countOrders) {
//        this.countOrders = countOrders;
//    }
//
    void init(User user);

//    UserDTO fromModel(User user);

}
