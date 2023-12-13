import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { Carro } from '../../Carro';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-carro-popup',
  templateUrl: './carro-popup.component.html',
  styleUrl: './carro-popup.component.css'
})
export class CarroPopupComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Carro[] , private dialog: MatDialog){
    this.carros = data
    }

  carros: Carro[];
  carro: Carro = new Carro;
  showPopover: boolean = true;

  @Output() eventoCarros = new EventEmitter;
  enviarCarros(){
    this.eventoCarros.emit(this.carros);
  }


  salvarCarros(){
    console.log("Registrando Carro");
    this.carros.push(this.carro);
    console.log(this.carro);
    this.enviarCarros();
    console.log(this.carros);
  }
}
