import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {UserService} from '../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Message} from '../../shared/message';
import {takeUntil} from 'rxjs/operators';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  title = "Login";
  form: FormGroup;
  notification: Message;

  submitted = false;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private authService: AuthService, private userService: UserService,
              private router: Router, private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.activatedRoute.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: Message) => {
        this.notification = params;
      });

    this.returnUrl = this.activatedRoute.snapshot.queryParams['returnUrl'] || '/';

    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(16)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(64)])]
    });
  }

  ngOnDestroy() {

    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {

    this.notification = undefined;
    this.submitted = true;

    this.authService.login(this.form.value)
      .subscribe(data => {
          this.userService.getMyInfo().subscribe();
          this.router.navigate(['home']);
        },
        error => {
          this.submitted = false;
          this.notification = {msgType: 'error', msgBody: 'Incorrect username or password.'};
        });
  }

}
