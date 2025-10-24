# Implementation Plan: CLI計算機

**Branch**: `001-cli-calculator` | **Date**: 2025-10-23 | **Spec**: [spec.md](./spec.md)
**Input**: Feature specification from `/specs/001-cli-calculator/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/commands/plan.md` for the execution workflow.

## Summary

CLI計算機は、ユーザーがコマンドラインで基本的な四則演算（加算、減算、乗算、除算）を実行できるJavaアプリケーションです。Java 25とGradle 8.xを使用し、最小限の外部依存関係で構築されます。アプリケーションはスタンドアロンの実行可能JARとして配布され、高速起動（500ms以下）と優れたエラーハンドリングを提供します。

## Technical Context

**Language/Version**: Java 25  
**Primary Dependencies**: JUnit 5 (テスト用のみ), Gradle 8.x (ビルドツール)  
**Storage**: N/A (ファイル保存機能なし)  
**Testing**: JUnit 5, Gradle test task  
**Target Platform**: Java 25がインストールされた環境（Windows、Linux、macOS）
**Project Type**: Single project - スタンドアロンCLIアプリケーション  
**Performance Goals**: 起動時間500ms以下、計算実行100ms以内、メモリ使用量128MB以下  
**Constraints**: 外部依存関係最小限、スタンドアロン実行、テストカバレッジ90%以上  
**Scale/Scope**: 単一ユーザー向けCLIツール、基本四則演算のみ、3つのユーザーストーリー

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

**✅ Library-First**: CLI計算機は独立したライブラリとして設計されます。計算ロジック、バリデーション、エラーハンドリングはそれぞれ独立したコンポーネントとして実装され、個別にテスト可能です。

**✅ CLI Interface**: アプリケーション全体がCLIインターフェースとして設計されています。stdin/args → stdout、エラー → stderrのプロトコルに従い、人間が読みやすい形式で出力を提供します。

**✅ Test-First (NON-NEGOTIABLE)**: TDD手法を厳格に実施します。各機能の実装前にテストを作成し、テストが失敗することを確認してから実装に進みます。Red-Green-Refactorサイクルを遵守します。

**✅ Integration Testing**: 主要な統合ポイント（CLI引数解析、計算エンジン、エラーハンドリング）に対して統合テストを実装します。

## Constitution Check (Post-Design Re-evaluation)

*GATE: Re-evaluated after Phase 1 design completion.*

**✅ Library-First**: 設計完了後も原則を維持しています。各パッケージ（operation、validation、util、factory、exception）は独立したライブラリコンポーネントとして設計され、明確なインターフェースと責任分離を持っています。

**✅ CLI Interface**: すべての機能がCLIインターフェースとして実装されています。contracts/cli-interface.mdで詳細に定義されたstdin/args → stdout、エラー → stderrプロトコルに準拠し、JSON出力は不要ですが人間が読みやすい形式を提供します。

**✅ Test-First (NON-NEGOTIABLE)**: contracts/test-contracts.mdで包括的なテスト仕様を定義しました。unit tests、integration tests、contract tests、performance testsすべてがTDD手法で実装される設計です。90%のテストカバレッジ要件も満たしています。

**✅ Integration Testing**: quickstart.mdで3つの主要統合シナリオ（Basic Calculation Flow、Error Handling Flow、Help and Version Flow）を定義し、エンドツーエンドの統合テストを保証しています。

**✅ Simplicity**: 設計は必要最小限の複雑さを維持しています。data-model.mdで定義されたエンティティは5つのみで、すべて明確な目的を持っています。YAGNI原則に従い、スコープ外機能は意図的に除外されています。

**✅ Observability**: Text I/Oプロトコルによりデバッグが容易で、エラーメッセージは構造化され、quickstart.mdでデバッグシナリオも定義されています。

**結論**: すべての憲法原則が設計レベルで満たされており、実装フェーズに進む準備が整っています。

## Project Structure

### Documentation (this feature)

```text
specs/[###-feature]/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```text
# Single project structure for CLI計算機
src/main/java/com/calculator/
├── Calculator.java                 # 主要な計算エンジン
├── CalculatorApp.java             # アプリケーションエントリーポイント
├── operation/                     # 演算操作
│   ├── Operation.java            # 演算インターフェース
│   ├── AdditionOperation.java    # 加算実装
│   ├── SubtractionOperation.java # 減算実装
│   ├── MultiplicationOperation.java # 乗算実装
│   └── DivisionOperation.java    # 除算実装
├── factory/
│   └── OperationFactory.java     # 演算オブジェクト生成
├── util/                         # ユーティリティ
│   ├── ArgumentParser.java       # コマンドライン引数解析
│   ├── InputValidator.java       # 入力値検証
│   ├── ErrorHandler.java         # エラーハンドリング
│   ├── HelpDisplay.java          # ヘルプ表示
│   └── VersionDisplay.java       # バージョン情報表示
├── validation/                   # 検証ロジック
│   ├── NumberValidator.java      # 数値検証
│   └── OperatorValidator.java    # 演算子検証
└── exception/                    # カスタム例外
    ├── InvalidNumberException.java
    ├── InvalidOperatorException.java
    └── DivisionByZeroException.java

src/test/java/com/calculator/
├── operation/                    # 演算テスト
├── validation/                   # 検証テスト
├── util/                        # ユーティリティテスト
├── help/                        # ヘルプ機能テスト
├── version/                     # バージョン機能テスト
└── CalculatorIntegrationTest.java # 統合テスト

src/main/resources/
└── version.properties           # バージョン情報

build.gradle                     # Gradleビルド設定
gradle/                         # Gradleラッパー
```

**Structure Decision**: シングルプロジェクト構造を選択しました。CLI計算機は単一の実行可能JARとしてパッケージ化される独立したアプリケーションであり、複数のサービスやプロジェクトに分割する必要がありません。パッケージ構造は責任に基づいて明確に分離されており、テストが容易で保守性が高い設計となっています。

## Complexity Tracking

> このプロジェクトは複雑性の制約に違反していません。シンプルで明確な単一目的のCLIアプリケーションです。

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| なし | すべての設計選択は必要最小限 | シンプルな設計を維持 |
