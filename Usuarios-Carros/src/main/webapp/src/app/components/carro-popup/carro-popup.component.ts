import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { Carro } from '../../Carro';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { DadosCarros } from '../../DadosCarros';

@Component({
  selector: 'app-carro-popup',
  templateUrl: './carro-popup.component.html',
  styleUrl: './carro-popup.component.css'
})
export class CarroPopupComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: DadosCarros , private dialog: MatDialog){
    this.carros = data.carros||[];
    this.carro = data.carro||new Carro;
    }

  carros: Carro[];
  carro: Carro;
  showPopover: boolean = true;

  @Output() eventoCarros = new EventEmitter;
  enviarCarros(){
    this.eventoCarros.emit(this.carros);
  }


  salvarCarros(){
    console.log("Registrando Carro");
    if(this.carro.id){
    this.carros.forEach(item =>{
      if(item.id == this.carro.id){
          item = this.carro
        }
      });
    }else this.carros.push(this.carro);
    console.log(this.carro);
    this.enviarCarros();
    console.log(this.carros);
  }
}
