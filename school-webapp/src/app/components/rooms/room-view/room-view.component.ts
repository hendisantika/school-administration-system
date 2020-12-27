import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Room} from 'src/app/model/room';
import {UserService} from 'src/app/service/user.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {RoomService} from 'src/app/service/room.service';
import {Router} from '@angular/router';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-room-view',
  templateUrl: './room-view.component.html',
  styleUrls: ['./room-view.component.scss']
})
export class RoomViewComponent implements OnInit {

  searchText;
  rooms: Observable<Room[]>;
  isDataAvailable: boolean = false;
  currentUser: any = {};

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private roomService: RoomService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.roomService.findAll().subscribe(data => {
        this.rooms = data;
        this.isDataAvailable = true;
      });
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  createRoom() {
    this.router.navigate(['/rooms/create']);
  }

  delete(classroom_id: number) {
    this.roomService.delete(classroom_id).subscribe(() => {
      this.refresh();
      this.openSnackBar('Room deleted.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  refresh(): void {
    window.location.reload();
  }

}
