import {Criteria} from "./criteria";
import {AlternativePackage} from "./alternative-package";

export type Project = {
  id: number,
  name: string,
  criteriaList: Criteria[],
  alternativePackageList: AlternativePackage[]
}
