import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CartItem, Plato } from '../models/plato.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItemsSubject = new BehaviorSubject<CartItem[]>([]);
  public cartItems$ = this.cartItemsSubject.asObservable();

  constructor() {
    // Cargar carrito del localStorage si existe
    const savedCart = localStorage.getItem('cart');
    if (savedCart) {
      this.cartItemsSubject.next(JSON.parse(savedCart));
    }

    // Guardar en localStorage cuando cambie el carrito
    this.cartItems$.subscribe(items => {
      localStorage.setItem('cart', JSON.stringify(items));
    });
  }

  addItem(plato: Plato, cantidad: number = 1): void {
    const currentItems = this.cartItemsSubject.value;
    const existingItem = currentItems.find(item => item.plato.id === plato.id);

    if (existingItem) {
      existingItem.cantidad += cantidad;
      existingItem.subtotal = existingItem.cantidad * plato.precio;
    } else {
      const newItem: CartItem = {
        plato,
        cantidad,
        subtotal: cantidad * plato.precio
      };
      currentItems.push(newItem);
    }

    this.cartItemsSubject.next([...currentItems]);
  }

  removeItem(platoId: number): void {
    const currentItems = this.cartItemsSubject.value.filter(
      item => item.plato.id !== platoId
    );
    this.cartItemsSubject.next(currentItems);
  }

  updateQuantity(platoId: number, cantidad: number): void {
    const currentItems = this.cartItemsSubject.value;
    const item = currentItems.find(i => i.plato.id === platoId);

    if (item) {
      if (cantidad <= 0) {
        this.removeItem(platoId);
      } else {
        item.cantidad = cantidad;
        item.subtotal = cantidad * item.plato.precio;
        this.cartItemsSubject.next([...currentItems]);
      }
    }
  }

  clearCart(): void {
    this.cartItemsSubject.next([]);
  }

  getTotalItems(): number {
    return this.cartItemsSubject.value.reduce((sum, item) => sum + item.cantidad, 0);
  }

  getTotalPrice(): number {
    return this.cartItemsSubject.value.reduce((sum, item) => sum + item.subtotal, 0);
  }

  getCartItems(): CartItem[] {
    return this.cartItemsSubject.value;
  }
}

