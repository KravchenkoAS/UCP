package Application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordDTO {

    @JsonProperty("oldPassword")
    private String oldPassword;

    @JsonProperty("newPassword")
    private String newPassword;

    @JsonProperty("roleUser")
    private String roleUser;

    public ChangePasswordDTO() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRole() {
        return roleUser;
    }

    public void setRole(String role) {
        this.roleUser = roleUser;
    }

    public void init(String oldPassword, String newPassword, String roleUser) {
        this.setNewPassword(newPassword);
        this.setOldPassword(oldPassword);
        this.setRole(roleUser);
    }

    public static ChangePasswordDTO fromModel(String oldPassword, String newPassword, String roleUser) {
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.init(oldPassword, newPassword, roleUser);
        return dto;
    }
}
