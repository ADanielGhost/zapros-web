import {Answer} from "./answer";

export type BuildingQesCheckResult = {
  id: number,
  over: boolean,
  answerList: Answer[],
  answerForReplacing: Answer
}
