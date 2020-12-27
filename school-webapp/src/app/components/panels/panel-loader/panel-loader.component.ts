import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';

@Component({
  selector: 'app-panel-loader',
  templateUrl: './panel-loader.component.html',
  styleUrls: ['./panel-loader.component.scss']
})
export class PanelLoaderComponent implements OnInit {

  user: any = {};
  isDataAvailable: boolean = false;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.user = data;
      this.isDataAvailable = true;
    });
  }

  userRole(): string {
    return this.user.authorities[0].authority + '';
  }

}
