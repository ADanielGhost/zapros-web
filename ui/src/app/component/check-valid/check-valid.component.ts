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

  public textChoose1: string | undefined;
  public textChoose2: string | undefined;
  public textChoose3: string | undefined;

  public needAlert: boolean = false;
  public isDataLoad: boolean = false;

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
      this.isDataLoad = true;
    }, () => {
      this.needAlert = true;
    });
  }

  private initTextForCorrectingAnswer() {
    if (!this.checkResult) return;
    if (this.checkResult.over) return;

    this.assessmentI = this.checkResult.answerForReplacing.i;
    this.assessmentJ = this.checkResult.answerForReplacing.j;
    this.answerType = this.checkResult.answerForReplacing.answerType;

    const answerTypeStr = this.answerType == 'BETTER' ? 'лучше' : 'WORSE' ? 'хуже' : "одинакова по предпочтению";
    // this.textChoose = `Ранее вы говорили, что оценка\n${this.assessmentI.name}(ранг ${this.assessmentI.rank})\n${answerTypeStr},`+
    //   ` чем \n${this.assessmentJ.name}(ранг ${this.assessmentJ.rank}).\n Данный ответ вызывает противоречия. Что для вас лучше сейчас?`

    this.textChoose1 = `${this.assessmentI.name}(ранг ${this.assessmentI.rank})`;
    this.textChoose2 = `${answerTypeStr}, чем`;
    this.textChoose3 = `${this.assessmentJ.name}(ранг ${this.assessmentJ.rank})`;
  }

  rankAlternatives() {
    this.isDataLoad = false;
    this._zaprosService.rankAlternatives(this.userId).subscribe(() => {
      this.router.navigate(['/view/result', this.userId]);
    });
  }

  replaceAnswer(type: string) {
    if (!this.checkResult) return;

    this.isDataLoad = false;
    this._zaprosService.replaceAnswer(this.userId, <AnswerType> type, this.checkResult).subscribe(x => {
      this.checkResult = x;
      this.initTextForCorrectingAnswer();
      this.isDataLoad = true;
    });
  }
}
