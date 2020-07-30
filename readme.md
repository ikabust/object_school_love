# ゲームの要件定義

## イメージ
- マリオ風

## シナリオ
学校に遅刻しそうな女の子がパンを加えながら学校まで走るゲーム。  
登校中に運命の人が現れるので華麗に避けて学校に遅刻しないようにしよう！  


## 機能

### 初期マリオ -> 左右にスクロール
- 主人公が一定の位置から右に動けば右に背景移動

### 動かない障害物 -> 動く障害物
- 当たり判定をつける


### とげとげのいちころ敵->やれる敵
- 運命の人（いちころ）
- 犬（おびえて動けくなる）


### 時間制限
- 

## 主人公の操作方法
- 左右+ジャンプ
  - ヒップドロップ
  - 二段ジャンプ
  
# 仕様

## メインキャラ
### 移動操作
- スペースボタン：ジャンプ
- ジャンプの重力
- 右ボタン：右移動
- 左ボタン：左移動
- シフト＋右ボタン：ダッシュ*

### ライフについて
- 時間制限
- 敵にあたると時間を失う
- スタミナゲージ*

## 運命の人
### 設定
- ランダムで固定、右から左移動
- ヒットでゲームオーバー

## 背景
### 移動
- 一定の位置から右にスライド
- 左には動けない
- 主人公に追従

## わんわん
### 設定
- ランダムに移動左右(上下)*
- ヒットで消滅

## 地面・障害物
### 足場設定
- 主人公だけ
- 犬は地面だけ
