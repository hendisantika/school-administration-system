import {Component, OnInit} from '@angular/core';
import {MessageResponseDTO} from 'src/app/dto/response/MEssageResponseDTO';
import {MessageService} from 'src/app/service/message.service';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-message',
  templateUrl: './create-message.component.html',
  styleUrls: ['./create-message.component.scss']
})
export class CreateMessageComponent implements OnInit {

  currentUser: any = {};
  isDataAvailable: boolean = false;
  message = new MessageResponseDTO();

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.isDataAvailable = true;
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  onSubmit() {
    this.messageService.create(this.message).subscribe(() => {
      this.reset();
      this.openSnackBar('Message created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.refresh();
  }

  reset() {
    this.message = new MessageResponseDTO();
  }

  refresh(): void {
    window.location.reload();
  }

  goBack() {
    this.router.navigate(['home']);
  }

  userRole(): string {
    return this.currentUser.authorities[0].authority + '';
  }

}
