import { CustomersService } from './services/customers.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TableModule } from 'primeng/table';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { DataTableModule } from 'primeng/datatable';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from "@angular/router";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TableModule,
    AppRoutingModule,
    HttpClientModule,
    DataTableModule,
    ButtonModule,
    DialogModule,
    FormsModule,
    BrowserAnimationsModule,
    RouterModule
  ],
  providers: [HttpClientModule, CustomersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
