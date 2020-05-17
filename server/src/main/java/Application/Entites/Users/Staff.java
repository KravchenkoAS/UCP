package Application.Entites.Users;

import Application.Entites.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "staff", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Staff implements User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_user;

        @NotBlank(message = "Логин не может быть пустым")
        @Size(min=3, max = 50)
        private String username;

        @NotBlank(message = "E-mail не может быть пустым")
        @Size(max = 50)
        @Email
        private String email;

        @Size(min=1, max = 50)
        private String name;

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min=6, max = 100)
        private String password;

        @Size(min=1, max = 100)
        private String surname;

        @NotNull
        private Boolean locked;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "fk_users_id_role"))
        private Role role;

        public Staff() { super(); }

        public Staff(String username, String email, String password) {
                super();
                this.username = username;
                this.email = email;
                this.password = password;
        }

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

        public Role getRole() {
                return role;
        }

        public void setRole(Role role) {
                this.role = role;
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
        public String getPassword() {
                return password;
        }

        @Override
        public void setPassword(String password) {
                this.password = password;
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
        public Boolean getIsLock() {
               return getLocked();
        }

        @Override
        public void setIsLock(Boolean isLock) {
                setLocked(isLock);
        }

        @Override
        public Boolean getLocked() {
                return locked;
        }

        @Override
        public void setLocked(Boolean locked) {
                this.locked = locked;
        }
}
