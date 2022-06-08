import {AlternativeResult} from "./alternative-result";

export type AlternativeWebRankingResult = {
  alternativeResults: AlternativeResult[],
  nanoTime: number,
  countCompareBetter: number[],
  countCompareWorse: number[],
  countCompareEqual: number[],
  countCompareNotComparable: number[]
}
