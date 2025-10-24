# Quick Start Guide: CLI計算機

**Feature**: CLI計算機  
**Date**: 2025-10-23  
**Purpose**: 開発者向けクイックスタートガイドと統合シナリオ

## Development Setup

### Prerequisites
```bash
# Java 25のインストール確認
java --version
# Expected: openjdk 25.x.x or similar

# Gradleのインストール確認（Gradleラッパーを使用するため不要ですが、確認用）
gradle --version
# Expected: Gradle 8.x or later
```

### Project Setup
```bash
# プロジェクトのクローン
cd c:\Users\li3248\ghq\github.com\dentsusoken\fin-tech-leads-knowledge\ai\spec-kit\simple-calculator

# プロジェクト構造の作成
mkdir -p src/main/java/com/calculator
mkdir -p src/test/java/com/calculator
mkdir -p src/main/resources

# Gradleラッパーのセットアップ
./gradlew wrapper
```

### Build and Run
```bash
# プロジェクトのビルド
./gradlew build

# テストの実行
./gradlew test

# アプリケーションの実行
./gradlew run --args="10 + 5"

# 実行可能JARの作成
./gradlew shadowJar

# JARファイルの直接実行
java -jar build/libs/calculator.jar 10 + 5
```

## Integration Scenarios

### Scenario 1: Basic Calculation Flow

**Purpose**: 基本的な計算処理の統合テスト

**Flow**:
1. ユーザーがコマンドライン引数を入力
2. ArgumentParserが引数を解析
3. InputValidatorが入力値を検証
4. OperationFactoryが適切な演算オブジェクトを生成
5. Calculatorが計算を実行
6. 結果が標準出力に表示

**Test Commands**:
```bash
# 加算テスト
calculator 10 + 5
# Expected Output: 15

# 減算テスト  
calculator 20 - 8
# Expected Output: 12

# 乗算テスト
calculator 6 * 7
# Expected Output: 42

# 除算テスト
calculator 15 / 3
# Expected Output: 5

# 小数計算テスト
calculator 22.5 / 4.5
# Expected Output: 5
```

### Scenario 2: Error Handling Flow

**Purpose**: エラーハンドリングの統合テスト

**Flow**:
1. 不正な入力がArgumentParserに渡される
2. 各バリデーターが入力をチェック
3. 適切な例外が発生
4. ErrorHandlerが例外をキャッチ
5. ユーザーフレンドリーなエラーメッセージが標準エラー出力に表示

**Test Commands**:
```bash
# 無効な数値エラー
calculator abc + 5
# Expected Error: エラー: 数値ではありません: 'abc'

# 無効な演算子エラー
calculator 10 $ 5
# Expected Error: エラー: サポートされていない演算子です: '$'

# ゼロ除算エラー
calculator 10 / 0
# Expected Error: エラー: ゼロで除算することはできません

# 引数不足エラー
calculator 10 +
# Expected Error: エラー: 引数が不足しています

# 引数過多エラー
calculator 10 + 5 * 2
# Expected Error: エラー: 引数が多すぎます
```

### Scenario 3: Help and Version Flow

**Purpose**: ヘルプとバージョン情報表示の統合テスト

**Flow**:
1. --helpまたは--versionフラグが検出される
2. ArgumentParserが特別なフラグとして処理
3. 対応するDisplayクラスが呼び出される
4. 情報が標準出力に表示される

**Test Commands**:
```bash
# ヘルプ表示テスト
calculator --help
# Expected: 使用方法とオプションの説明が表示される

# ヘルプ表示テスト（短縮形）
calculator -h
# Expected: 同上

# バージョン表示テスト
calculator --version
# Expected: バージョン情報が表示される

# バージョン表示テスト（短縮形）
calculator -v
# Expected: 同上
```

## Development Workflow

### Test-Driven Development Cycle

1. **Red Phase** - テストを書いて失敗させる
```bash
# 新しいテストを作成
# src/test/java/com/calculator/operation/AdditionOperationTest.java

# テストを実行して失敗を確認
./gradlew test --tests AdditionOperationTest
```

2. **Green Phase** - 最小限の実装でテストを通す
```bash
# 実装を追加
# src/main/java/com/calculator/operation/AdditionOperation.java

# テストを実行して成功を確認
./gradlew test --tests AdditionOperationTest
```

3. **Refactor Phase** - コードを改善
```bash
# リファクタリング後のテスト実行
./gradlew test

# カバレッジレポートの生成
./gradlew jacocoTestReport
```

### Integration Testing

**Level 1: Unit Tests**
```bash
# 個別クラスのテスト
./gradlew test --tests "*OperationTest"
./gradlew test --tests "*ValidatorTest"
```

**Level 2: Component Tests**
```bash
# 関連コンポーネントのテスト
./gradlew test --tests "*CalculatorTest"
./gradlew test --tests "*ArgumentParserTest"
```

**Level 3: Integration Tests**
```bash
# エンドツーエンドテスト
./gradlew test --tests "*IntegrationTest"
```

**Level 4: System Tests**
```bash
# 実際のJAR実行テスト
./gradlew shadowJar
java -jar build/libs/calculator.jar 10 + 5
```

## Performance Validation

### Startup Time Measurement
```bash
# 起動時間の測定
time java -jar build/libs/calculator.jar 10 + 5
# 500ms以下であることを確認
```

### Memory Usage Measurement
```bash
# メモリ使用量の測定
java -Xmx128m -jar build/libs/calculator.jar 999999999 * 999999999
# 128MB以下で動作することを確認
```

### Load Testing
```bash
# 大量計算のテスト
for i in {1..100}; do
  java -jar build/libs/calculator.jar $i + $i
done
# 安定して動作することを確認
```

## Debugging Scenarios

### Debug Mode Execution
```bash
# デバッグ情報付きで実行
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 \
     -jar build/libs/calculator.jar 10 + 5
```

### Verbose Logging
```bash
# ログレベルを変更して実行
java -Djava.util.logging.level=FINE \
     -jar build/libs/calculator.jar 10 + 5
```

### Error Reproduction
```bash
# エラーの再現とデバッグ
java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps \
     -jar build/libs/calculator.jar invalid input
```

## Deployment Validation

### JAR File Verification
```bash
# JARファイルの内容確認
jar -tf build/libs/calculator.jar

# MANIFESTファイルの確認
jar -xf build/libs/calculator.jar META-INF/MANIFEST.MF
cat META-INF/MANIFEST.MF
```

### Cross-Platform Testing
```bash
# Windows
java -jar calculator.jar 10 + 5

# Linux (WSL)
wsl java -jar calculator.jar 10 + 5

# macOS (仮想環境があれば)
# java -jar calculator.jar 10 + 5
```

## Continuous Integration

### Build Pipeline
```bash
# CI/CDで実行されるコマンド例
./gradlew clean
./gradlew build
./gradlew test
./gradlew jacocoTestReport
./gradlew shadowJar
```

### Quality Gates
```bash
# コードカバレッジの確認
./gradlew jacocoTestCoverageVerification

# 静的解析の実行
./gradlew checkstyle
./gradlew spotbugs

# 依存関係の脆弱性チェック
./gradlew dependencyCheck
```

## Troubleshooting

### Common Issues

**Issue 1: Java Version Mismatch**
```bash
# 解決方法
export JAVA_HOME=/path/to/java25
java --version
```

**Issue 2: Gradle Wrapper Permission**
```bash
# 解決方法
chmod +x gradlew
./gradlew --version
```

**Issue 3: Test Failures**
```bash
# 詳細なテスト出力
./gradlew test --info --stacktrace
```

**Issue 4: Memory Issues**
```bash
# ヒープサイズの調整
export GRADLE_OPTS="-Xmx2g -XX:MaxMetaspaceSize=512m"
./gradlew build
```
