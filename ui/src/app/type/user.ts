import {MethodType} from "./method-type";

export type User = {
  id: number,
  name: string,
  email: string,
  methodType: MethodType,
  threshold?: number
}
