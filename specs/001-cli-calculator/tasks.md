---
description: "CLI計算機実装のタスクリスト"
---

# タスク: CLI計算機

**入力**: `/specs/001-cli-calculator/`からの設計ドキュメント
**前提条件**: spec.md（ユーザーストーリーと要件）

**技術スタック**: Java 25, Gradle 8.x, 最小限の外部依存関係
**構成**: タスクはユーザーストーリーごとにグループ化され、各ストーリーの独立した実装とテストを可能にします。

## フォーマット: `[ID] [P?] [Story] 説明`

- **[P]**: 並列実行可能（異なるファイル、依存関係なし）
- **[Story]**: このタスクが属するユーザーストーリー（例：US1、US2、US3）
- 説明には正確なファイルパスを含める

## パス規約

Javaプロジェクト構造:
- `src/main/java/` - メインソースコード
- `src/test/java/` - テストソースコード
- `build.gradle` - ビルド設定
- `gradle/` - Gradleラッパーファイル

---

## フェーズ1: セットアップ（共有インフラストラクチャ）

**目的**: プロジェクトの初期化と基本構造

- [X] T001 Java 25互換性を持つGradleプロジェクト構造を作成
- [X] T002 Java 25とアプリケーションプラグイン設定でbuild.gradleを初期化
- [X] T003 [P] JavaプロジェクトのGradleラッパーとgitignoreをセットアップ
- [X] T004 [P] src/main/java/com/calculator/にメインパッケージ構造を作成

---

## フェーズ2: 基盤（ブロッキング前提条件）

**目的**: すべてのユーザーストーリーを実装する前に完了する必要があるコアインフラストラクチャ

**⚠️ 重要**: このフェーズが完了するまで、ユーザーストーリーの作業は開始できません

- [X] T005 src/main/java/com/calculator/Calculator.javaに基本Calculatorクラス構造を作成
- [X] T006 [P] src/main/java/com/calculator/util/ArgumentParser.javaにArgumentParserユーティリティを作成
- [X] T007 [P] src/main/java/com/calculator/util/InputValidator.javaにInputValidatorユーティリティを作成
- [X] T008 [P] src/main/java/com/calculator/util/ErrorHandler.javaにErrorHandlerユーティリティを作成
- [X] T009 src/main/java/com/calculator/CalculatorApp.javaにメインアプリケーションエントリーポイントを作成

**チェックポイント**: 基盤準備完了 - ユーザーストーリーの実装を並列で開始可能

---

## フェーズ3: ユーザーストーリー1 - 基本四則演算の実行 (優先度: P1) 🎯 MVP

**目標**: ユーザーがコマンドラインで数値と演算子を入力し、加算、減算、乗算、除算の結果を即座に取得する

**独立テスト**: `calculator 5 + 3`のようなコマンド実行で`8`という結果が返されることを確認し、基本的な計算機能の価値を提供する

### ユーザーストーリー1のテスト

> **注意: これらのテストを最初に書き、実装前に失敗することを確認してください**

- [X] T010 [P] [US1] src/test/java/com/calculator/operation/AdditionOperationTest.javaに加算操作の単体テストを作成
- [X] T011 [P] [US1] src/test/java/com/calculator/operation/SubtractionOperationTest.javaに減算操作の単体テストを作成
- [X] T012 [P] [US1] src/test/java/com/calculator/operation/MultiplicationOperationTest.javaに乗算操作の単体テストを作成
- [X] T013 [P] [US1] src/test/java/com/calculator/operation/DivisionOperationTest.javaに除算操作の単体テストを作成
- [X] T014 [P] [US1] src/test/java/com/calculator/CalculatorIntegrationTest.javaに基本計算の統合テストを作成

### ユーザーストーリー1の実装

- [X] T015 [P] [US1] src/main/java/com/calculator/operation/Operation.javaにOperationインターフェースを作成
- [X] T016 [P] [US1] src/main/java/com/calculator/operation/AdditionOperation.javaにAdditionOperationクラスを作成
- [X] T017 [P] [US1] src/main/java/com/calculator/operation/SubtractionOperation.javaにSubtractionOperationクラスを作成
- [X] T018 [P] [US1] src/main/java/com/calculator/operation/MultiplicationOperation.javaにMultiplicationOperationクラスを作成
- [X] T019 [P] [US1] src/main/java/com/calculator/operation/DivisionOperation.javaにDivisionOperationクラスを作成
- [X] T020 [US1] src/main/java/com/calculator/factory/OperationFactory.javaにOperationFactoryを作成
- [X] T021 [US1] Calculator.javaに基本計算ロジックを実装
- [X] T022 [US1] "数値 演算子 数値"形式のコマンドライン引数解析を実装
- [X] T023 [US1] CalculatorApp.javaでメインアプリケーションフローと操作を統合

**チェックポイント**: この時点で、ユーザーストーリー1は完全に機能し、独立してテスト可能になる

---

## フェーズ4: ユーザーストーリー2 - エラーハンドリング (優先度: P2)

**目標**: ユーザーが不正な入力を行った場合に、わかりやすいエラーメッセージを表示し、正しい使用方法を案内する

**独立テスト**: `calculator abc + 5`のような不正入力で適切なエラーメッセージが表示され、ユーザーが正しい使用方法を理解できる

### ユーザーストーリー2のテスト

- [X] T024 [P] [US2] src/test/java/com/calculator/validation/InputValidationTest.javaに無効な数値入力のテストを作成
- [X] T025 [P] [US2] src/test/java/com/calculator/validation/OperatorValidationTest.javaに無効な演算子入力のテストを作成
- [X] T026 [P] [US2] src/test/java/com/calculator/operation/DivisionByZeroTest.javaにゼロ除算のテストを作成
- [X] T027 [P] [US2] src/test/java/com/calculator/util/ErrorHandlerTest.javaにエラーメッセージフォーマットのテストを作成

### ユーザーストーリー2の実装

- [ ] T028 [P] [US2] src/main/java/com/calculator/validation/NumberValidator.javaにNumberValidatorクラスを作成
- [ ] T029 [P] [US2] src/main/java/com/calculator/validation/OperatorValidator.javaにOperatorValidatorクラスを作成
- [ ] T030 [P] [US2] src/main/java/com/calculator/exception/パッケージに例外クラスを作成
- [ ] T031 [US2] ゼロ除算検出機能でDivisionOperationを拡張
- [ ] T032 [US2] ErrorHandler.javaに包括的なエラーハンドリングを実装
- [ ] T033 [US2] ArgumentParser.javaに検証ロジックを追加
- [ ] T034 [US2] メインアプリケーションフローとエラーハンドリングを統合

**チェックポイント**: この時点で、ユーザーストーリー1と2の両方が独立して動作する

---

## フェーズ5: ユーザーストーリー3 - ヘルプとバージョン情報 (優先度: P3)

**目標**: ユーザーがヘルプオプションを使用して使用方法を確認し、バージョン情報を取得できる

**独立テスト**: `calculator --help`で使用方法が表示され、`calculator --version`でバージョン情報が表示される

### ユーザーストーリー3のテスト

- [ ] T035 [P] [US3] src/test/java/com/calculator/help/HelpDisplayTest.javaにヘルプオプション表示のテストを作成
- [ ] T036 [P] [US3] src/test/java/com/calculator/version/VersionDisplayTest.javaにバージョンオプション表示のテストを作成

### ユーザーストーリー3の実装

- [ ] T037 [P] [US3] src/main/java/com/calculator/util/HelpDisplay.javaにHelpDisplayユーティリティを作成
- [ ] T038 [P] [US3] src/main/java/com/calculator/util/VersionDisplay.javaにVersionDisplayユーティリティを作成
- [ ] T039 [P] [US3] src/main/resources/version.propertiesにversion.propertiesリソースファイルを作成
- [ ] T040 [US3] --helpと--versionオプションを処理するためArgumentParserを拡張
- [ ] T041 [US3] メインアプリケーションとヘルプ・バージョン表示を統合

**チェックポイント**: すべてのユーザーストーリーが独立して機能するようになる

---

## フェーズ6: 仕上げと横断的関心事

**目的**: 複数のユーザーストーリーに影響する改善と本番環境準備の確保

- [ ] T042 [P] 実行可能JAR生成のためGradleアプリケーションプラグインを設定
- [ ] T043 [P] 使用方法とビルド情報を含むREADME.mdを作成
- [ ] T044 [P] すべてのパブリッククラスとメソッドに包括的なJavaDocコメントを追加
- [ ] T045 [P] 数値解析と計算のパフォーマンス最適化を実装
- [ ] T046 [P] デバッグ用ログ設定を追加（オプション、最小限）
- [ ] T047 すべてのビルド要件を検証（./gradlew build, test, run）
- [ ] T048 最終実行可能JARを作成し、java -jar実行を検証
- [ ] T049 すべてのユーザーストーリーを横断する完全統合テストを実行
- [ ] T050 90%テストカバレッジ要件を検証

---

## 依存関係と実行順序

### フェーズ依存関係

- **セットアップ（フェーズ1）**: 依存関係なし - 即座に開始可能
- **基盤（フェーズ2）**: セットアップ完了に依存 - すべてのユーザーストーリーをブロック
- **ユーザーストーリー（フェーズ3-5）**: すべて基盤フェーズ完了に依存
  - ユーザーストーリーは並列で進行可能（人員が十分な場合）
  - または優先度順に順次実行（P1 → P2 → P3）
- **仕上げ（フェーズ6）**: 必要なすべてのユーザーストーリーの完了に依存

### ユーザーストーリー依存関係

- **ユーザーストーリー1（P1）**: 基盤（フェーズ2）後に開始可能 - 他のストーリーへの依存なし
- **ユーザーストーリー2（P2）**: 基盤（フェーズ2）後に開始可能 - US1を拡張するが独立してテスト可能
- **ユーザーストーリー3（P3）**: 基盤（フェーズ2）後に開始可能 - 独立したユーティリティ機能

### 各ユーザーストーリー内

- テストは実装前に書かれ、失敗することを確認する必要がある
- 操作の前にユーティリティクラス
- ファクトリーの前に操作
- 統合の前にコア実装
- 次の優先度に移る前にストーリー完了

### 並列実行機会

- [P]マークのすべてのセットアップタスクは並列実行可能
- [P]マークのすべての基盤タスクは並列実行可能（フェーズ2内）
- 基盤フェーズ完了後、すべてのユーザーストーリーを並列開始可能（チーム容量が許せば）
- [P]マークのユーザーストーリーのすべてのテストは並列実行可能
- US1内の[P]マークの操作クラスは並列実行可能
- US2内の[P]マークの検証クラスは並列実行可能
- US3内の[P]マークのヘルプ/バージョンユーティリティは並列実行可能

---

## 並列実行例: ユーザーストーリー1

```bash
# ユーザーストーリー1のすべてのテストを同時に起動:
タスク: "src/test/java/com/calculator/operation/AdditionOperationTest.javaに加算操作の単体テストを作成"
タスク: "src/test/java/com/calculator/operation/SubtractionOperationTest.javaに減算操作の単体テストを作成"
タスク: "src/test/java/com/calculator/operation/MultiplicationOperationTest.javaに乗算操作の単体テストを作成"
タスク: "src/test/java/com/calculator/operation/DivisionOperationTest.javaに除算操作の単体テストを作成"

# ユーザーストーリー1のすべての操作クラスを同時に起動:
タスク: "src/main/java/com/calculator/operation/AdditionOperation.javaにAdditionOperationクラスを作成"
タスク: "src/main/java/com/calculator/operation/SubtractionOperation.javaにSubtractionOperationクラスを作成"
タスク: "src/main/java/com/calculator/operation/MultiplicationOperation.javaにMultiplicationOperationクラスを作成"
タスク: "src/main/java/com/calculator/operation/DivisionOperation.javaにDivisionOperationクラスを作成"
```

---

## 実装戦略

### MVPファースト（ユーザーストーリー1のみ）

1. フェーズ1の完了: セットアップ
2. フェーズ2の完了: 基盤（重要 - すべてのストーリーをブロック）
3. フェーズ3の完了: ユーザーストーリー1
4. **停止して検証**: ユーザーストーリー1を独立してテスト
5. 実行可能JARを作成して基本計算をデモ

### 段階的デリバリー

1. セットアップ + 基盤の完了 → 基盤準備完了
2. ユーザーストーリー1を追加 → 独立してテスト → デプロイ/デモ（MVP - 基本計算機！）
3. ユーザーストーリー2を追加 → 独立してテスト → デプロイ/デモ（エラーハンドリング付きロバスト計算機）
4. ユーザーストーリー3を追加 → 独立してテスト → デプロイ/デモ（フル機能CLIツール）
5. 仕上げフェーズ → 本番環境対応計算機

### 並列チーム戦略

複数の開発者がいる場合:

1. チーム全体でセットアップ + 基盤を完了
2. 基盤完了後:
   - 開発者A: ユーザーストーリー1（基本操作）
   - 開発者B: ユーザーストーリー2（エラーハンドリング）
   - 開発者C: ユーザーストーリー3（ヘルプ/バージョン）
3. ストーリーを独立して完了し統合

---

## まとめ

- **総タスク数**: 6フェーズで50タスク
- **ユーザーストーリーごとのタスク数**:
  - US1（基本四則演算）: 14タスク（実装9 + テスト5）
  - US2（エラーハンドリング）: 11タスク（実装7 + テスト4）  
  - US3（ヘルプとバージョン）: 7タスク（実装5 + テスト2）
- **並列実行機会**: フェーズ内で[P]マークの25タスクが並列実行可能
- **独立テスト条件**: 各ユーザーストーリーには明確な受け入れテストがあり、独立して検証可能
- **推奨MVPスコープ**: ユーザーストーリー1のみ（基本四則演算の実行）
- **フォーマット検証**: すべてのタスクはID、[P]マーカー、[Story]ラベル、ファイルパスを含む必須チェックリストフォーマットに従う

タスクはLLMが即座に実行可能に設計されており、具体的なファイルパスと明確なフェーズ間依存関係を提供しています。
