# CLI Interface Contract

**Application**: CLI計算機  
**Interface Type**: Command Line Interface  
**Date**: 2025-10-23

## Command Syntax

### Basic Calculation

```bash
calculator <number1> <operator> <number2>
```

**Parameters**:
- `number1`: 左オペランド（整数または小数）
- `operator`: 演算子（+, -, *, /）
- `number2`: 右オペランド（整数または小数）

**Example**:
```bash
calculator 10 + 5
calculator 20.5 - 8.2
calculator 6 * 7
calculator 15 / 3
```

### Help Command

```bash
calculator --help
calculator -h
```

**Output**: 使用方法とオプションの説明

### Version Command

```bash
calculator --version
calculator -v
```

**Output**: アプリケーションのバージョン情報

## Input Specification

### Number Format
- **Integer**: `123`, `-456`, `0`
- **Decimal**: `123.45`, `-0.67`, `3.14159`
- **Range**: 最大15桁まで対応
- **Invalid**: `abc`, `12.34.56`, `1e10`, `infinity`

### Operator Format
- **Addition**: `+`
- **Subtraction**: `-`
- **Multiplication**: `*`
- **Division**: `/`
- **Invalid**: `$`, `%`, `^`, `add`, `plus`

### Argument Count
- **Valid**: 正確に3つの引数（計算時）
- **Valid**: 1つの引数（--help、--version）
- **Invalid**: 0個、2個、4個以上の引数

## Output Specification

### Success Cases

#### Normal Calculation
```bash
$ calculator 10 + 5
15
```

#### Decimal Result
```bash
$ calculator 22 / 7
3.142857142857143
```

#### Help Output
```bash
$ calculator --help
CLI Calculator - Java 25版四則演算計算機

使用方法:
  calculator <数値1> <演算子> <数値2>
  calculator --help | -h
  calculator --version | -v

演算子:
  +    加算
  -    減算
  *    乗算
  /    除算

例:
  calculator 10 + 5
  calculator 20.5 - 8.2
  calculator 6 * 7
  calculator 15 / 3
```

#### Version Output
```bash
$ calculator --version
CLI Calculator v1.0.0
Java 25対応版
```

### Error Cases

#### Invalid Number
```bash
$ calculator abc + 5
エラー: 数値ではありません: 'abc'
正しい形式: calculator <数値> <演算子> <数値>
```

#### Invalid Operator
```bash
$ calculator 10 $ 5
エラー: サポートされていない演算子です: '$'
使用可能な演算子: +, -, *, /
```

#### Division by Zero
```bash
$ calculator 10 / 0
エラー: ゼロで除算することはできません
```

#### Insufficient Arguments
```bash
$ calculator 10 +
エラー: 引数が不足しています
使用方法: calculator <数値> <演算子> <数値>
```

#### Too Many Arguments
```bash
$ calculator 10 + 5 * 2
エラー: 引数が多すぎます
複数の演算子を含む式はサポートされていません
```

## Exit Codes

- **0**: 成功（正常な計算、ヘルプ、バージョン表示）
- **1**: エラー（不正な入力、計算エラー）

## Stream Usage

- **stdout**: 計算結果、ヘルプ、バージョン情報
- **stderr**: エラーメッセージ
- **stdin**: 使用しません（すべてコマンドライン引数で処理）

## Performance Requirements

- **起動時間**: 500ms以下
- **計算時間**: 100ms以下
- **メモリ使用量**: 128MB以下

## Compatibility

- **Java**: Java 25以上が必要
- **Platform**: Windows、Linux、macOSで動作
- **文字エンコーディング**: UTF-8

## Error Handling Contract

### Error Message Format
```
エラー: <エラーの詳細>
<補足説明または解決方法>
```

### Error Categories
1. **入力エラー**: 不正な数値、演算子、引数数
2. **計算エラー**: ゼロ除算、オーバーフロー
3. **システムエラー**: 設定ファイル読み込み失敗など

### Graceful Degradation
- すべてのエラー状況で適切なメッセージを表示
- アプリケーションのクラッシュは発生させない
- ユーザーに次のアクションを明示
