import {AlternativeWebRankingResult} from "./alternative-web-ranking-result";
import {MethodType} from "./method-type";

export type FullAlternativeResult = {
  methodType: MethodType,
  rankOrderResult: AlternativeWebRankingResult,
  rankQVResult: AlternativeWebRankingResult
}
