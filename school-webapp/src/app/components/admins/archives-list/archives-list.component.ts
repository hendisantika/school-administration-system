import {Component, OnInit} from '@angular/core';
import {Archive} from 'src/app/model/archive';
import {Observable} from 'rxjs';
import {UserService} from 'src/app/service/user.service';
import {AdminService} from 'src/app/service/admin.service';
import {Router} from '@angular/router';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-archives-list',
  templateUrl: './archives-list.component.html',
  styleUrls: ['./archives-list.component.scss']
})
export class ArchivesListComponent implements OnInit {

  searchText;
  archives: Observable<Archive[]>;
  isDataAvailable: boolean = false;
  currentUser: any = {};

  constructor(private userService: UserService, private adminService: AdminService,
              private router: Router) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.adminService.getArchive().subscribe(data => {
        this.archives = data;
        this.isDataAvailable = true;
      });
    });
  }

  details(archive_id: number) {
    this.router.navigate(['archive', archive_id]);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
