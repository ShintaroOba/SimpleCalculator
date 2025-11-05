# SimpleCalculator

[![Java CI with Gradle](https://github.com/ShintaroOba/SimpleCalculator/actions/workflows/ci.yml/badge.svg)](https://github.com/ShintaroOba/SimpleCalculator/actions/workflows/ci.yml)

シンプルで高速なコマンドライン電卓アプリケーション

## 概要

SimpleCalculatorは、コマンドラインから基本的な四則演算を実行できる軽量な電卓アプリケーションです。Java 21で開発され、クリーンアーキテクチャの原則に従って設計されています。


## 技術スタック

- **言語**: Java 21
- **ビルドツール**: Gradle 8.x
- **テストフレームワーク**: JUnit 5
- **カバレッジツール**: JaCoCo
- **CI/CD**: GitHub Actions

## 必要要件

- Java 21以上
- Gradle 8.x以上（Gradleラッパーを使用する場合は不要）

## インストール

### リポジトリのクローン

```bash
git clone https://github.com/ShintaroOba/SimpleCalculator.git
cd SimpleCalculator
```

### ビルド

```bash
# Gradleラッパーを使用したビルド
./gradlew build

# 実行可能JARファイルの生成
./gradlew jar
```

## 使い方

### 基本的な計算

```bash
# Gradleを使用した実行
./gradlew run --args="10 + 5"

# JARファイルから直接実行
java -jar build/libs/SimpleCalculator-1.0.0.jar 10 + 5
```

### テストの実行

```bash
# すべてのテストを実行
./gradlew test

# カバレッジレポートの生成
./gradlew jacocoTestReport

# カバレッジレポートの閲覧
# build/reports/jacoco/test/html/index.htmlをブラウザで開く
```

