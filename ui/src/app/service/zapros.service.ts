import { Injectable } from '@angular/core';
import {BackendService} from "./backend.service";
import {Observable} from "rxjs";
import {AnswerCheckResult} from "../type/answer-check-result";
import {Answer, AnswerType} from "../type/answer";
import {BuildingQesCheckResult} from "../type/building-qes-check-result";

@Injectable({
  providedIn: 'root'
})
export class ZaprosService {

  private mapping: String = '/zapros';

  constructor(
    private _backendService: BackendService
  ) { }

  askFirst(userId: number): Observable<AnswerCheckResult> {
    return this._backendService.get<AnswerCheckResult>(this.mapping + `/ask/first/${userId}`);
  }

  addAnswer(userId: number, answerType: AnswerType, answerCheckResult: AnswerCheckResult): Observable<AnswerCheckResult> {
    return this._backendService.post<AnswerCheckResult>(this.mapping + `/add/answer/${userId}/${answerType}`, answerCheckResult);
  }

  sendAnswers(userId: number, answers: Answer[]): Observable<void> {
    return this._backendService.post<void>(this.mapping + `/send/answers/${userId}`, answers);
  }

  checkValid(userId: number): Observable<BuildingQesCheckResult> {
    return this._backendService.get<BuildingQesCheckResult>(this.mapping + `/check/valid/${userId}`);
  }

  replaceAnswer(userId: number, answerType: AnswerType, checkResult: BuildingQesCheckResult): Observable<BuildingQesCheckResult> {
    return this._backendService.post<BuildingQesCheckResult>(this.mapping + `/replace/answer/${userId}/${answerType}`, checkResult);
  }
}
