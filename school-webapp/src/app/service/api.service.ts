import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {serialize} from '../shared/serialize';
import {Observable} from 'rxjs';
import {catchError, filter, map} from 'rxjs/operators';

export enum RequestMethod {
  Get = 'GET',
  Head = 'HEAD',
  Post = 'POST',
  Put = 'PUT',
  Delete = 'DELETE',
  Options = 'OPTIONS',
  Patch = 'PATCH'
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  });

  constructor(private httpClient: HttpClient) {

  }

  get(path: string, args?: any): Observable<any> {
    const OPTIONS = {
      headers: this.headers,
      withCredentials: true
    };

    if (args) {
      OPTIONS['params'] = serialize(args);
    }

    return this.httpClient.get(path, OPTIONS)
      .pipe(catchError(error => {
          throw error;
        }
      ));
  }

  post(path: string, body: any, customHeaders?: HttpHeaders): Observable<any> {
    return this.request(path, body, RequestMethod.Post, customHeaders);
  }

  put(path: string, body: any): Observable<any> {
    return this.request(path, body, RequestMethod.Put);
  }

  delete(path: string, body: any): Observable<any> {
    return this.request(path, body, RequestMethod.Delete);
  }

  private request(path: string, body: any, method = RequestMethod.Post, customHeaders?: HttpHeaders): Observable<any> {
    const REQUEST = new HttpRequest(method, path, body, {
      headers: customHeaders || this.headers,
      withCredentials: true
    });

    return this.httpClient.request(REQUEST)
      .pipe(filter(response => response instanceof HttpResponse))
      .pipe(map((response: HttpResponse<any>) => response.body))
      .pipe(catchError(error => {
          throw error;
        }
      ));
  }
}
