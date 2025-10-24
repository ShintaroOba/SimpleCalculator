# Test Contracts

**Application**: CLI計算機  
**Purpose**: テスト仕様とコントラクト定義  
**Date**: 2025-10-23

## Test Categories

### 1. Unit Tests

#### Operation Tests
各演算クラスの単体テスト仕様

**AdditionOperation Test Contract**:
```java
// Positive numbers
assert addition.calculate(5, 3) == 8
assert addition.calculate(10.5, 2.3) == 12.8

// Negative numbers  
assert addition.calculate(-5, 3) == -2
assert addition.calculate(-5, -3) == -8

// Zero
assert addition.calculate(0, 5) == 5
assert addition.calculate(5, 0) == 5
```

**SubtractionOperation Test Contract**:
```java
// Basic subtraction
assert subtraction.calculate(10, 3) == 7
assert subtraction.calculate(5.5, 2.2) == 3.3

// Negative results
assert subtraction.calculate(3, 10) == -7
assert subtraction.calculate(-5, 3) == -8
```

**MultiplicationOperation Test Contract**:
```java
// Basic multiplication
assert multiplication.calculate(6, 7) == 42
assert multiplication.calculate(2.5, 4) == 10

// Zero multiplication
assert multiplication.calculate(0, 5) == 0
assert multiplication.calculate(5, 0) == 0

// Negative multiplication
assert multiplication.calculate(-3, 4) == -12
assert multiplication.calculate(-3, -4) == 12
```

**DivisionOperation Test Contract**:
```java
// Basic division
assert division.calculate(15, 3) == 5
assert division.calculate(22, 7) == 3.142857142857143

// Decimal division
assert division.calculate(7.5, 2.5) == 3

// Division by zero (should throw exception)
assertThrows(DivisionByZeroException.class, () -> division.calculate(5, 0))
```

#### Validation Tests

**NumberValidator Test Contract**:
```java
// Valid numbers
assert numberValidator.isValid("123") == true
assert numberValidator.isValid("-456") == true
assert numberValidator.isValid("3.14159") == true
assert numberValidator.isValid("0") == true
assert numberValidator.isValid("-0.5") == true

// Invalid numbers
assert numberValidator.isValid("abc") == false
assert numberValidator.isValid("12.34.56") == false
assert numberValidator.isValid("") == false
assert numberValidator.isValid("1e10") == false
```

**OperatorValidator Test Contract**:
```java
// Valid operators
assert operatorValidator.isValid("+") == true
assert operatorValidator.isValid("-") == true
assert operatorValidator.isValid("*") == true
assert operatorValidator.isValid("/") == true

// Invalid operators
assert operatorValidator.isValid("$") == false
assert operatorValidator.isValid("%") == false
assert operatorValidator.isValid("add") == false
assert operatorValidator.isValid("") == false
```

### 2. Integration Tests

#### CLI Integration Test Contract

**Basic Calculation Flow**:
```java
// Test: calculator 10 + 5
String[] args = {"10", "+", "5"};
String output = runCalculator(args);
assert output.equals("15\n");
assert exitCode == 0;
```

**Error Handling Flow**:
```java
// Test: calculator abc + 5
String[] args = {"abc", "+", "5"};
String errorOutput = runCalculatorWithError(args);
assert errorOutput.contains("エラー: 数値ではありません");
assert exitCode == 1;
```

**Help Display Flow**:
```java
// Test: calculator --help
String[] args = {"--help"};
String output = runCalculator(args);
assert output.contains("CLI Calculator");
assert output.contains("使用方法:");
assert exitCode == 0;
```

### 3. Contract Tests

#### Input Contract Tests

**Valid Input Formats**:
```java
@Test
void shouldAcceptValidNumberFormats() {
    // Integer formats
    assertValidInput("123", "+", "456");
    assertValidInput("-123", "+", "456");
    assertValidInput("0", "+", "1");
    
    // Decimal formats  
    assertValidInput("123.45", "+", "67.89");
    assertValidInput("-123.45", "+", "67.89");
    assertValidInput("0.5", "+", "0.3");
}

@Test
void shouldRejectInvalidNumberFormats() {
    // Invalid number formats
    assertInvalidInput("abc", "+", "5");
    assertInvalidInput("12.34.56", "+", "5");
    assertInvalidInput("1e10", "+", "5");
    assertInvalidInput("", "+", "5");
}
```

#### Output Contract Tests

**Success Output Format**:
```java
@Test  
void shouldFormatOutputCorrectly() {
    // Simple integer result
    assertOutput("10", "+", "5", "15");
    
    // Decimal result
    assertOutput("22", "/", "7", "3.142857142857143");
    
    // Zero result
    assertOutput("5", "-", "5", "0");
}

@Test
void shouldFormatErrorCorrectly() {
    // Error format: "エラー: <詳細>\n<補足>"
    assertErrorFormat("abc", "+", "5", 
                     "エラー: 数値ではありません: 'abc'",
                     "正しい形式: calculator <数値> <演算子> <数値>");
}
```

### 4. Performance Tests

#### Performance Contract

**Startup Time Test**:
```java
@Test
void shouldStartupWithin500ms() {
    long startTime = System.currentTimeMillis();
    runCalculator("10", "+", "5");
    long duration = System.currentTimeMillis() - startTime;
    assert duration < 500; // 500ms以下
}
```

**Calculation Time Test**:
```java
@Test  
void shouldCalculateWithin100ms() {
    // 計算処理のみの時間測定
    long startTime = System.nanoTime();
    BigDecimal result = calculator.calculate(
        new BigDecimal("123.456"), 
        "+", 
        new BigDecimal("789.012"));
    long duration = (System.nanoTime() - startTime) / 1_000_000; // ms
    assert duration < 100; // 100ms以下
}
```

**Memory Usage Test**:
```java
@Test
void shouldUseMemoryWithin128MB() {
    // メモリ使用量の測定
    Runtime runtime = Runtime.getRuntime();
    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
    
    runCalculator("999999999999999", "*", "999999999999999");
    
    long afterMemory = runtime.totalMemory() - runtime.freeMemory();
    long usedMemory = afterMemory - beforeMemory;
    
    assert usedMemory < 128 * 1024 * 1024; // 128MB以下
}
```

## Test Data Sets

### Valid Calculation Test Cases
```yaml
test_cases:
  - input: ["10", "+", "5"]
    expected: "15"
  - input: ["20.5", "-", "8.2"] 
    expected: "12.3"
  - input: ["6", "*", "7"]
    expected: "42"
  - input: ["15", "/", "3"]
    expected: "5"
  - input: ["-10", "+", "5"]
    expected: "-5"
  - input: ["0", "*", "100"]
    expected: "0"
```

### Error Test Cases
```yaml
error_cases:
  - input: ["abc", "+", "5"]
    error_type: "InvalidNumberException"
    message_contains: "数値ではありません"
  - input: ["10", "$", "5"]  
    error_type: "InvalidOperatorException"
    message_contains: "サポートされていない演算子"
  - input: ["10", "/", "0"]
    error_type: "DivisionByZeroException"
    message_contains: "ゼロで除算"
```

## Test Coverage Requirements

- **Overall Coverage**: 90%以上
- **Branch Coverage**: 85%以上  
- **Method Coverage**: 95%以上
- **Class Coverage**: 100%

## Test Execution Contract

### Pre-conditions
- Java 25が利用可能
- Gradleビルドが成功している
- テスト用のtemporaryディレクトリが利用可能

### Post-conditions
- すべてのテストが独立して実行可能
- テスト実行後のクリーンアップが完了
- テストレポートが生成される

### Test Isolation
- 各テストは他のテストに依存しない
- 状態の共有を行わない
- 並列実行が可能
