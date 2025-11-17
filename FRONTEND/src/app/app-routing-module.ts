import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { Menu } from './components/menu/menu';
import { Admin } from './components/admin/admin';
import { Home } from './components/home/home';
import { PedidoComponent } from './components/pedido/pedido';
import { MisPedidosComponent } from './components/mis-pedidos/mis-pedidos';
import { AdminGuard } from './guards/admin.guard';

const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: LoginComponent },
  { path: 'menu', component: Menu },
  { path: 'pedido', component: PedidoComponent },
  { path: 'mis-pedidos', component: MisPedidosComponent },
  { path: 'admin', component: Admin, canActivate: [AdminGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
