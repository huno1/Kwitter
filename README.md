# Kwitter開発プロジェクト
橘川 裕太
高橋海音<br>
及川大輔

### GitHub Desktopの使い方

    ・ [File]-[Clone a repository...]-[URL]-[https://github.com/huno1/Kwitter][C:\repository]-[Clone]
	・ [Fetch Origin](2行目の右)
    ・ 適当にこのファイル(README.md)に名前を書いてCommitしてみよう。
	・　Summaryには修正内容
	・ Fetch(pull) = ダウンロード / Commit & Push = アップロード. pushまでしないといみない。


## Database

	* 投稿表
		・　投稿ID(number)
		・　作成者
		・　内容
		・　日付
		・　表示/非表示/削除状態
		
	* ユーザー表
		・　ユーザーID(number)
		・　ログイン用 ID
		・　PASSWORD
		・　ニックネーム
		・　登録日、最終接続日など？
		・　紹介欄
		・　ICON, 写真、　画像など
		・　その他設定？
		
	* 返信表
		・　返信ID
		・　対象投稿ID
		・　対象ユーザーID(No必須)
		・　作成者ID
		・　日付
		・　内容
		
	* フォロー表
		・　した人ID
		・　された人ID
		・　＋＠？
	
	* 添付表
		・　添付ID
		・　対象投稿ID
		・　ファイルPATH


## ディレクトリとファイルの概要
* documentsディレクトリ
    * UMLなど。
    
* WEB-INFディレクトリ
    * .java とか .classとか？


## 開発環境について
### 環境概要
* OS
    * 開発環境: Windows10
    * 本番環境: 
* 使用言語およびフレームワーク
    * Java8
    * HTML5
    * CSS3
    * JavaScript
    
* ミドルソフトウェア
    * tomcat7
    
* データベース
    * Oracle Database

## 開発の流れ

下準備

    1. KwitterリモートリポジトリをGitHub上でフォーク（自分のリモートにコピー）※最初だけ
    2. ローカルにクローン（自分のリモートから自分のローカルにコピー）※最初だけ


開発サイクル

    1. 他の人の変更がマスターにあるかもしれないので、それを自分のマスターに反映させる（GitHub DeskTopならupdate from ~）
    2. 自分のする作業に合わせた名前でブランチを切る（ブランチとはパラレルワールドみたいなものです）
    3. コーディング　※この部分の流れについては後述します
    4. 作業が終わったら、自分のリモートと同期してから自分の作業をプルリクエストで送る
    5. レビューを受け、修正があった場合はそれを自分のローカルで直して、もう一度プルリク。
    6. 1.に戻る


## コーディングの流れ

	1. テストから書く（実装するクラスのメソッド1つに対して）
	2. テストを実行。（失敗させる）
	3. テストを成功させるための最低限のシンプルな実装をする
	4. テストを実行（成功させる）

テストクラスを書くのにはJUnitというテスト用のフレームワークを使います。

使用するフレームワーク

**JUnit**

JUnitの基本ルール

	1. クラス名は必ず「テストしたいクラス名+Test」で書き、publicで作成
	2. テストメソッドは、org.junit.Testアノテーションを付与したpublicメソッドとする
    3. テストメソッドは、戻り値がvoidであり、引数を持たない。
    4. throws句は自由に定義可能



テストメソッド内の処理

	1. 初期化・・・テスト対象オブジェクトの初期化、必要な入力値、期待される結果などを準備　
	2. 実行・・・テスト対象のメソッドを１つだけ実行
	3. 検証・・・テストの結果として得られた実測値が期待値と等価であるかを検証
	4. 終了処理（必要な場合）・・・次のテストに影響が内容に後始末


	
	
### (旧)Gitの使い方
分散型バージョン管理システムです。何が分散してるかというとリポジトリが、です。

    ・ローカルリポジトリへのコミットはこまめに。
    ・コミットメッセージはちゃんと具体的に。何を変更したのかわかるように
    ・自分のリポジトリに他の人のプッシュをこまめに反映させよう
    ・自分の作業に合わせたブランチ名をつけてから作業を始めよう
    ・メインリポジトリにpushできないときはまずローカルにメインの変更を反映させて、競合を解決してからマージしよう。
    ・なんかエラーはいてる→open Git shell→git status



------------------------------------------------------------------------
この文書はTeraNavi(https://github.com/blue-210/TeraNavi) からパクりました。
