import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../usuario.service';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

class Usuario {
  constructor(
    public id: number,
    public senha: string = '',
    public nome: string = '',
    public dataNascimento: string = '',
    public cpf: string = '',
    public dataUltimoLogin: string = '') { }
}


@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})

export class CadastroUsuarioComponent implements OnInit {

  registrations: Usuario[] = [];
  regModel: Usuario;  
  showNew: Boolean = false;  
  submitType: string = 'Incluir';  
  selectedRow: number;
  u: any;
  postData :  string;
  cont : number;

  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {    
    this.listar();
  }

  // Botão Novo Usuário
  onNew() {  
    this.cont = ((this.usuarios.length) + 1);
    this.regModel = new Usuario(this.cont);
    this.submitType = 'Incluir';
    this.showNew = true;
  }

  listar() {
    this.usuarioService.listar().subscribe(dados => this.usuarios = dados);
  }

  onCreate() {    
    this.usuarioService.criar(this.regModel)
    .subscribe(
       data => this.postData = JSON.stringify(data),
       error => alert(error)
    );
    this.listar();
  }

  onEdit(index: number) {
    console.log("i = "+index);
    this.selectedRow = index;
    this.regModel = new Usuario(index);
    console.log("Registrations = "+this.usuarios);
    this.regModel = Object.assign({}, this.usuarios[this.selectedRow]);
    this.submitType = 'Alterar';
    this.showNew = true;
  }

  onDelete(usuario: Usuario): void {
    console.log("u id = "+usuario.id);
    this.usuarioService.deleteUser(usuario.id)
      .subscribe( data => {
        this.usuarios = this.usuarios.filter(u => u !== usuario);
      });
  };

  // This method associate to Cancel Button.
  onCancel() {
    // Hide registration entry section.
    this.showNew = false;
  }

}
