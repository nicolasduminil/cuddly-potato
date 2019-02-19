import { Component, OnInit } from '@angular/core';
import { Customer } from './domain/customer';
import { PrimeCustomer } from './domain/prime-customer';
import { CustomersService } from './services/customers.service';
import { map } from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'Customers';
  customers: Array<Customer>;
  cols: any[];
  displayDialog: boolean;
  customer: Customer = new PrimeCustomer();
  newCustomer: boolean;

  constructor(private customersService: CustomersService) {}

  ngOnInit() {
    this.fetchData().subscribe(customers => this.customers = customers);
    this['cols'] = [
      { field: 'firstName', header: 'First Name' },
      { field: 'lastName', header: 'Last Name' },
      { field: 'street', header: 'Address - Street' },
      { field: 'city', header: 'Address - City' },
      { field: 'state', header: 'Address - State' },
      { field: 'zip', header: 'Address - Zip' },
      { field: 'country', header: 'Address - Country' },
    ];
  }

  fetchData() {
    return this.customersService.getAll().pipe(map(cs => {return cs} ));
 }

  showDialogToAdd() {
    this.newCustomer = true;
    this.customer = new PrimeCustomer();
    this.displayDialog = true;
  }

  save() {
    if(this.newCustomer) {
      this.customersService.create(this.customer);
      this.customers.splice(0,0, this.customer);
    }
    else {
      this.customersService.update(this.customer.pk, this.customer);
      this.customers.splice(this.customers.findIndex(c => c.pk === this.customer.pk), 1, this.customer)
    }
    this.displayDialog = false;
  }

  delete() {
    console.log("### delete() - PK:", this.customer.pk);
    this.customersService.delete(this.customer.pk);
    this.customers.splice(this.customers.findIndex(c => c.pk === this.customer.pk), 1);
    this.displayDialog = false;
  }

  onRowSelect(event) {
    this.newCustomer = false;
    this.customer = this.cloneCustomer(event.data);
    this.displayDialog = true;
  }

  cloneCustomer(c: Customer): Customer {
    let customer = new PrimeCustomer();
    for(let prop in c)
      customer[prop] = c[prop];
    return customer;
  }
}

