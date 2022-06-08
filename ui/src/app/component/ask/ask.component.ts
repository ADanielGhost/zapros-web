import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../service/project.service";
import {ZaprosService} from "../../service/zapros.service";
import {AnswerCheckResult} from "../../type/answer-check-result";
import {Criteria} from "../../type/criteria";
import {Assessment} from "../../type/assessment";
import {AnswerType} from "../../type/answer";

@Component({
  selector: 'app-ask',
  templateUrl: './ask.component.html',
  styleUrls: ['./ask.component.css']
})
export class AskComponent implements OnInit {

  public userId: number;
  public checkResult: AnswerCheckResult | undefined;

  public criteriaI: Criteria | undefined;
  public criteriaJ: Criteria | undefined;
  public assessmentI: Assessment | undefined;
  public assessmentJ: Assessment | undefined;
  public bestAssessmentI: Assessment | undefined;
  public bestAssessmentJ: Assessment | undefined;

  public textCompare: string | undefined;
  public textChoose: string | undefined;

  public needAlert: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _projectService: ProjectService,
    private _zaprosService: ZaprosService
  ) {
    this.userId = parseInt(<string> this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    // this._projectService.getUserById(this.userId).subscribe(x => {
    //   this.user = x;
    // })
  }

  askFirst() {
    this._zaprosService.askFirst(this.userId).subscribe(x => {
      this.checkResult = x;
      this.initDataForAsking();
    }, () => {
      this.needAlert = true;
    });
  }

  addAnswer(type: string) {
    if (!this.checkResult) return;

    this._zaprosService.addAnswer(this.userId, <AnswerType> type, this.checkResult).subscribe(x => {
      this.checkResult = x;
      this.initDataForAsking();
    });
  }

  private initDataForAsking() {
    if (!this.checkResult) return;
    if (this.checkResult.over) return;

    this.criteriaI = this.checkResult.criteriaList[this.checkResult.pcriteriaI];
    this.criteriaJ = this.checkResult.criteriaList[this.checkResult.pcriteriaJ];

    this.assessmentI = this.criteriaI.assessments[this.checkResult.passessmentI];
    this.assessmentJ = this.criteriaJ.assessments[this.checkResult.passessmentJ];

    this.bestAssessmentI = this.criteriaI.assessments[0];
    this.bestAssessmentJ = this.criteriaJ.assessments[0];

    this.textCompare = `Сравниваем критерии "${this.criteriaI.name}" и "${this.criteriaJ.name}"`;
    this.textChoose = `Что для вас лучше: ${this.assessmentI.name}(ранг ${this.assessmentI.rank}) и ${this.bestAssessmentJ.name} или ` +
      `${this.bestAssessmentI.name} и ${this.assessmentJ.name}(ранг ${this.assessmentJ.rank}), если остальные критерии имеют лучшие показатели?`;
  }

  sendAnswers() {
    if (!this.checkResult || !this.checkResult.over) return;
    this._zaprosService.sendAnswers(this.userId, this.checkResult.answerList).subscribe(() => {
      this.router.navigate(['/check/valid', this.userId]);
    });
  }
}
