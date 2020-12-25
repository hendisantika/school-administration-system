import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {MessageService} from 'src/app/service/message.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Observable} from 'rxjs';
import {Message} from '@angular/compiler/src/i18n/i18n_ast';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  user: any;
  isDataAvailable: boolean = false;
  messages: Observable<Message[]>;

  constructor(private userService: UserService, private messageService: MessageService,
              private router: Router, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.messageService.findAll().subscribe(data => {
      this.messages = data;
    });
    this.userService.getMyInfo().toPromise().then(data => {
      this.user = data;
      this.isDataAvailable = true;
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  ngOnDestroy() {
    this.user = null;
  }

  userRole(): string {
    return this.user.authorities[0].authority + '';
  }

  create() {
    this.router.navigate(['message/create']);
  }

  update(id: number) {
    this.router.navigate(['message/update', id]);
  }

  delete(id: number) {
    this.messageService.delete(id).subscribe(() => {
      this.refresh();
      this.openSnackBar('Message deleted.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  refresh(): void {
    window.location.reload();
  }

}
