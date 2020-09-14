## Antlr4笔记

### 基本概念

+ 语法文件，定义语法规则，通过g4文件来定义。
+ Lexer 词法分析器，将字符序列转换为单词（Token）序列的过程。
+ Parser 语法分析器，是进行语法检查、并构建由输入的单词组成的数据结构（一般是语法分析树、抽象语法树等层次化的数据结构），构建语法树（AST）的过程。

### 语法文件说明

+ grammar 名称和文件名要一致
+ Parser 规则（即 non-terminal）以小写字母开始
+ Lexer 规则（即 terminal）以大写字母开始
+ 所有的 Lexer 规则无论写在哪里都会被重排到 Parser 规则之后
+ 所有规则中若有冲突，先出现的规则优先匹配
+ 用 'string' 单引号引出字符串
+ | 用于分隔两个产生式，(a|b) 括号用于指定子产生式，?+*用法同正则表达式
+ 在产生式后面 # label 可以给某条产生式命名，在生成的代码中即可根据标签分辨不同产生式
+ 不需要指定开始符号
+ 规则以分号终结
+ /* block comment */ 以及 // line comment
+ 默认的左结合，可以用 <assoc=right> 指定右结合
+ 可以处理直接的左递归，不能处理间接的左递归
+ 如果用 MUL: '*'; 指定了某个字符串的名字，在程序里面就能用这个名字了
+ 用 fragment 可以给 Lexer 规则中的公共部分命名

### 开发流程

1. 定义 .g4 语法文件；
2. 使用 ANTLR 4 生成词法分析器（Lexer）和语法分析器（Parser）目标编程语言代码，支持的编程语言：Java、JavaScript、Python、C 和 C++ 等；
3. 遍历 AST（Abstract Syntax Tree 抽象语法树），ANTLR 4 支持两种模式：访问者模式（Visitor）和监听器模式（Listener）。

## 参考

[ANTLR4实践笔记](https://www.jianshu.com/p/c2c42d933108)