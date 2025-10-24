# Data Model: CLI計算機

**Feature**: CLI計算機  
**Date**: 2025-10-23  
**Purpose**: データ構造とエンティティの定義

## Core Entities

### 1. Calculation (計算式)

2つの数値と1つの演算子から構成される数学的表現を表現します。

**Fields**:
- `leftOperand: BigDecimal` - 左オペランド（第一数値）
- `operator: String` - 演算子（+, -, *, /）  
- `rightOperand: BigDecimal` - 右オペランド（第二数値）
- `result: BigDecimal` - 計算結果（計算後に設定）

**Validation Rules**:
- leftOperandとrightOperandは必須
- operatorは "+", "-", "*", "/" のいずれかでなければならない
- 除算の場合、rightOperandは0であってはならない
- 数値は最大15桁まで対応

**State Transitions**:
1. Created: 入力値が設定された状態
2. Validated: 入力値の検証が完了した状態  
3. Calculated: 計算が実行され結果が設定された状態

### 2. Operator (演算子)

サポートされる算術演算子を定義します。

**Fields**:
- `symbol: String` - 演算子記号
- `name: String` - 演算子名（日本語）
- `precedence: int` - 演算子の優先度（将来の拡張用）

**Supported Values**:
- Addition: "+" (加算)
- Subtraction: "-" (減算)  
- Multiplication: "*" (乗算)
- Division: "/" (除算)

**Validation Rules**:
- symbolは定義された4つの値のいずれかでなければならない
- nameは空でない文字列でなければならない

### 3. CalculationInput (計算入力)

コマンドライン引数から解析された入力データを表現します。

**Fields**:
- `args: String[]` - 原のコマンドライン引数
- `leftOperandStr: String` - 左オペランドの文字列表現
- `operatorStr: String` - 演算子の文字列表現  
- `rightOperandStr: String` - 右オペランドの文字列表現

**Validation Rules**:
- argsは3つの要素を含まなければならない
- 各文字列フィールドは空でない値でなければならない
- leftOperandStrとrightOperandStrは有効な数値形式でなければならない

### 4. CalculationResult (計算結果)

計算処理の結果を表現します。

**Fields**:
- `success: boolean` - 計算の成功/失敗
- `result: BigDecimal` - 計算結果（成功時）
- `errorMessage: String` - エラーメッセージ（失敗時）
- `calculation: Calculation` - 元の計算式

**Validation Rules**:
- successがtrueの場合、resultは必須でerrorMessageはnull
- successがfalseの場合、errorMessageは必須でresultはnull

## Value Objects

### 1. CommandLineArgs

コマンドライン引数を解析した結果を表現する値オブジェクトです。

**Fields**:
- `isHelpRequested: boolean` - ヘルプ表示要求
- `isVersionRequested: boolean` - バージョン表示要求
- `calculationInput: CalculationInput` - 計算入力（通常の計算時）

### 2. ErrorInfo

エラー情報を構造化したオブジェクトです。

**Fields**:
- `code: String` - エラーコード
- `message: String` - ユーザー向けエラーメッセージ
- `detail: String` - 詳細なエラー情報（デバッグ用）

## Entity Relationships

```text
CommandLineArgs
    ├── CalculationInput
    │   └── Calculation
    │       ├── Operator
    │       └── CalculationResult
    └── ErrorInfo (エラー時)
```

## Data Flow

1. **Input Phase**: コマンドライン引数 → CommandLineArgs
2. **Parsing Phase**: CommandLineArgs → CalculationInput  
3. **Validation Phase**: CalculationInput → Calculation
4. **Execution Phase**: Calculation → CalculationResult
5. **Output Phase**: CalculationResult → 標準出力/エラー出力

## Persistence

このアプリケーションは状態を永続化しません。すべてのデータはメモリ内で処理され、計算完了後に破棄されます。

## Data Validation Strategy

### Input Level Validation
- コマンドライン引数の数と形式
- 基本的な文字列形式チェック

### Domain Level Validation  
- 数値の範囲と精度
- 演算子の有効性
- ビジネスルール（ゼロ除算など）

### Output Level Validation
- 結果の形式と精度
- エラーメッセージの適切性

## Error Handling Data

### Exception Hierarchy

```text
CalculatorException (基底例外)
├── InvalidInputException
│   ├── InvalidNumberException
│   └── InvalidOperatorException
├── CalculationException
│   └── DivisionByZeroException
└── SystemException
    └── ConfigurationException
```

各例外クラスは以下の情報を保持します：
- エラーコード
- ユーザー向けメッセージ
- 技術的詳細
- 発生場所の情報
