import {Criteria} from "./criteria";
import {Answer} from "./answer";

export type AnswerCheckResult = {
  over: boolean,
  answerList: Answer[],
  criteriaList: Criteria[],
  pcriteriaI: number,
  pcriteriaJ: number,
  passessmentI: number,
  passessmentJ: number
}
