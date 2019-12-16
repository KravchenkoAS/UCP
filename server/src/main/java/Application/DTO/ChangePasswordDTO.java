package Application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordDTO {

    @JsonProperty("oldPassword")
    private String oldPassword;

    @JsonProperty("newPassword")
    private String newPassword;

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

    public void init(String oldPassword, String newPassword) {
        this.setNewPassword(newPassword);
        this.setOldPassword(oldPassword);
    }

    public static ChangePasswordDTO fromModel(String oldPassword, String newPassword) {
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.init(oldPassword, newPassword);
        return dto;
    }
}
