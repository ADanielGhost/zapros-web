import {AlternativeResult} from "./alternative-result";
import {MethodType} from "./method-type";

export type FullAlternativeResult = {
  methodType: MethodType,
  rankOrderResult: AlternativeResult[],
  rankQVResult: AlternativeResult[]
}
