import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

export class Usuario {
    id: number;
    senha: string;
    nome: string;
    dataNascimento: string;
    cpf: string;
    dataUltimoLogin: string;
}

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  usuariosListUrl = 'http://localhost:8080/usuario';
  usuariosCreateUrl = 'http://localhost:8080/usuario';
  usuariosEditUrl = 'http://localhost:8080/updateusuario';
  usuariosDeleteUrl = 'http://localhost:8080/deleteusuario/';

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<any[]>(`${this.usuariosListUrl}`);
  } 

criar(usuario: any) {
    var json = JSON.stringify({id: usuario.id, senha: usuario.senha, nome: usuario.nome, dataNascimento: usuario.dataNascimento, cpf: usuario.cpf, dataUltimoLogin: '2018-11-10'});
    console.log(json);
    return this.http.post<Usuario>(this.usuariosCreateUrl, json, httpOptions)
  .pipe(
    catchError(this.handleError('add', json))
  );
}

deleteUser(id: number): Observable<Usuario> {
  console.log(this.usuariosDeleteUrl + id);
  return this.http.delete<Usuario>(this.usuariosDeleteUrl + id);
}

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); 

      return of(result as T);
    };
  } 


}
