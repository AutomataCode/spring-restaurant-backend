import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { App } from './app';
import { AppRoutingModule } from './app-routing-module';
import { LoginComponent } from './components/login/login.component';
import { Menu } from './components/menu/menu';
import { Admin } from './components/admin/admin';
import { Home } from './components/home/home';
import { PedidoComponent } from './components/pedido/pedido';
import { MisPedidosComponent } from './components/mis-pedidos/mis-pedidos';

@NgModule({
  declarations: [
    App,
    LoginComponent,
    Menu,
    Admin,
    Home,
    PedidoComponent,
    MisPedidosComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    RouterModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi())
  ],
  bootstrap: [App]
})
export class AppModule { }


