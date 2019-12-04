export class User {
    id_user: number;
    username: string;
    email: string;
    name: string;
    surname: string;
    isActive: boolean;
    role: string;
    countOrders: number;
};

export class ChangePassword {
    oldPassword: string;
    newPassword: string;
}