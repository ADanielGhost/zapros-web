import {Assessment} from "./assessment";

export type Criteria = {
  id: number,
  name: string,
  assessments: Assessment[],
  orderId: number
}
