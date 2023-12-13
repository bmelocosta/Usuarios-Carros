import { Carro } from "./Carro";
export class Usuario {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  birthday?: Date;
  login?: string;
  password?: string;
  phone?: string;
  cars?: Carro[];
  createdAt?: Date;
  lastLogin?: Date;
  authorities?: any[];
  username?: string;
  accountNonExpired?: boolean;
  accountNonLocked?: boolean;
  credentialsNonExpired?: boolean;

constructor(id?: number,firstName?: string,lastName?: string,email?: string,birthday?: Date,login?: string,password?: string,
  phone?: string,cars?: Carro[],createdAt?: Date,lastLogin?: Date,authorities?: any[],username?: string,accountNonExpired?: boolean,
  accountNonLocked?: boolean,credentialsNonExpired?: boolean ){
this.id=id;
this.firstName=firstName;
this.lastName=lastName;
this.email=email;
this.birthday=birthday;
this.login=login;
this.password=password;
this.phone=phone;
this.cars=cars;
this.createdAt=createdAt;
this.lastLogin=lastLogin;
this.authorities=authorities;
this.username=username;
this.accountNonExpired=accountNonExpired;
this.accountNonLocked=accountNonLocked;
this.credentialsNonExpired=credentialsNonExpired;
}

}
