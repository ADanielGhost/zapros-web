import {Assessment} from "./assessment";

export type Answer = {
  i: Assessment,
  j: Assessment,
  answerType: AnswerType,
  answerAuthor: AnswerAuthor
}

export type AnswerType = 'BETTER' | 'WORSE' | 'EQUAL';
export type AnswerAuthor = 'USER' | 'TRANSIENT' | 'REPLACED';
