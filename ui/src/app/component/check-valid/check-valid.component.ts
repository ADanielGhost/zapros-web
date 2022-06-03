import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ZaprosService} from "../../service/zapros.service";
import {BuildingQesCheckResult} from "../../type/building-qes-check-result";
import {Assessment} from "../../type/assessment";
import {AnswerType} from "../../type/answer";

@Component({
  selector: 'app-check-valid',
  templateUrl: './check-valid.component.html',
  styleUrls: ['./check-valid.component.css']
})
export class CheckValidComponent implements OnInit {

  // @ts-ignore
  public userId: number;
  public checkResult: BuildingQesCheckResult | undefined;

  public assessmentI: Assessment | undefined;
  public assessmentJ: Assessment | undefined;
  public answerType: AnswerType | undefined;

  public textChoose: string | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _zaprosService: ZaprosService
  ) { }

  ngOnInit() {
    this.userId = parseInt(<string> this.route.snapshot.paramMap.get('id'));
    this._zaprosService.checkValid(this.userId).subscribe(x => {
      this.checkResult = x;
      this.initTextForCorrectingAnswer();
    })
  }

  private initTextForCorrectingAnswer() {
    if (!this.checkResult) return;
    if (this.checkResult.over) return;

    this.assessmentI = this.checkResult.answerForReplacing.i;
    this.assessmentJ = this.checkResult.answerForReplacing.j;
    this.answerType = this.checkResult.answerForReplacing.answerType;

    this.textChoose = `Ранее вы говорили, что ${this.assessmentI.name}(${this.assessmentI.rank}) ${this.answerType},`+
      ` чем ${this.assessmentJ.name}(${this.assessmentJ.rank}). Данный ответ вызывает противоречия. Что для вас лучше сейчас?`
  }

  rankAlternatives() {
    console.log('rankAlternatives!');
  }

  replaceAnswer(type: string) {
    if (!this.checkResult) return;

    this._zaprosService.replaceAnswer(this.userId, <AnswerType> type, this.checkResult).subscribe(x => {
      this.checkResult = x;
      this.initTextForCorrectingAnswer();
    });
  }
}
