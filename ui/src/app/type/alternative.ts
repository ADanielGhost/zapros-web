import {Assessment} from "./assessment";

export type Alternative = {
  id: number,
  name: string,
  assessments: Assessment[]
}
