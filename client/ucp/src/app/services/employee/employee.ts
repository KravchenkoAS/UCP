export class Employee {
    id_employee: number;
    username: string;
    email: string;
    name: string;
    surname: string;
    isActive: boolean;
    password: string;
    role: string;

    init(employee: any) {
        this.username = employee.username;
        this.email = employee.email;
        this.name = employee.name;
        this.surname = employee.surname;
        this.role = employee.role;
        this.password = employee.password;
        this.isActive = true;
    }
};