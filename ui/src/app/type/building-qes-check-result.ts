import {Answer} from "./answer";

export type BuildingQesCheckResult = {
  id: number,
  over: boolean,
  answerForReplacing: Answer,
  answerList: Answer[]
}
