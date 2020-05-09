//package Application.DTO;
//
//import Application.DTO.UsersDTO.UserDTO;
//import Application.Entites.Users.Staff;
//import Application.Entites.Users.User;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class EmployeeDTO extends  {
//
//    @JsonProperty("id_user")
//    private Long id_user;
//
//    @JsonProperty("username")
//    private String username;
//
//    private String email;
//
//    @JsonProperty("name")
//    private String name;
//
//    @JsonProperty("surname")
//    private String surname;
//
//    @JsonProperty("isActive")
//    private Boolean isActive;
//
//    @JsonProperty("role")
//    private String role;
//
//    @JsonProperty("password")
//    private String password;
//
//    public EmployeeDTO() { }
//
//    public Long getId_user() {
//        return id_user;
//    }
//
//    public void setId_user(Long id_user) {
//        this.id_user = id_user;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void init(User user) {
//        this.setId_user(user.getId_user());
//        this.setName(user.getName());
//        this.setUsername(user.getUsername());
//        this.setSurname(user.getSurname());
//        this.setActive(user.getIsLock());
//        this.setEmail(user.getEmail());
//        this.setRole(user.getRole().getName().toString());
//    }
//
//    public Staff parse() {
//        Staff user = new Staff();
//        user.setId_user(this.getId_user());
//        user.setName(this.getName());
//        user.setUsername(this.getUsername());
//        user.setSurname(this.getSurname());
//        user.setIsLock(this.getActive());
//        user.setEmail(this.getEmail());
//        user.setPassword(this.getPassword());
//        return user;
//    }
//
//    public static UserDTO fromModel(User user) {
//        UserDTO dto = new EmployeeDTO();
//        dto.init(user);
//        return dto;
//    }
//
//}
