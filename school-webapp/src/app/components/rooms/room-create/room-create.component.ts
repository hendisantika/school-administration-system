import {Component, OnInit} from '@angular/core';
import {RoomResponseDTO} from 'src/app/dto/response/roomResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {RoomService} from 'src/app/service/room.service';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-room-create',
  templateUrl: './room-create.component.html',
  styleUrls: ['./room-create.component.scss']
})
export class RoomCreateComponent implements OnInit {

  currentUser: any = {};
  isDataAvailable: boolean = false;
  room = new RoomResponseDTO();

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private roomService: RoomService) {
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
    this.roomService.create(this.room).subscribe(() => {
      this.reset();
      this.openSnackBar('Room created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.refresh();
  }

  reset() {
    this.room = new RoomResponseDTO();
  }

  refresh(): void {
    window.location.reload();
  }

  goBack() {
    this.router.navigate(['/rooms/all']);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
