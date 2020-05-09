export class User {
    id_user: number;
    username: string;
    email: string;
    name: string;
    surname: string;
    isActive: boolean;
    role: string;
    countOrders: number;

    init(user: User) {
            this.id_user = user.id_user;
            this.username = user.username;
            this.email = user.email;
            this.name = user.name;
            this.surname = user.surname;
            this.isActive = user.isActive;
            this.role = user.role;
            this.countOrders = user.countOrders;
    }
};

export class ChangePassword {
    oldPassword: string;
    newPassword: string;
    roleUser: string;
}