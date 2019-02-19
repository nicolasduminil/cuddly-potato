import {Customer} from "./customer";

export class PrimeCustomer implements Customer {
  constructor(public pk?, public firstName?, public lastName?, public street?, public city?, public state?, public zip?, public country?) {
  }
}
