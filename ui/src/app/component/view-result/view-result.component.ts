import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ZaprosService} from "../../service/zapros.service";
import {FullAlternativeResult} from "../../type/full-alternative-result";

@Component({
  selector: 'app-view-result',
  templateUrl: './view-result.component.html',
  styleUrls: ['./view-result.component.css']
})
export class ViewResultComponent implements OnInit {

  // @ts-ignore
  public userId: number;
  // @ts-ignore
  public fullAlternativeResult: FullAlternativeResult;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _zaprosService: ZaprosService
  ) { }

  ngOnInit(): void {
    this.userId = parseInt(<string> this.route.snapshot.paramMap.get('id'));
    this._zaprosService.rankAlternatives(this.userId).subscribe(x => {
      this.fullAlternativeResult = x;
    });
  }
}
