package cn.wangz.antlr;

import cn.wangz.antlr.listener.StatementSqlListener;
import cn.wangz.antlr.parser.SqlBaseLexer;
import cn.wangz.antlr.parser.SqlBaseParser;
import cn.wangz.antlr.stream.ANTLRNoCaseStringStream;
import cn.wangz.antlr.visit.StatementSqlToStringVisitor;
import cn.wangz.antlr.visit.StatementSqlVisitor;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * @author wang_zh
 * @date 2020/4/14
 */
public class Main {

    public static void main(String[] args) {

        //language=sql
        String sql = "insert into in_db.in_tab select * from sc_db1.sc_tab1 a left join sc_db2.sc_tab2 b on a.id2 = b.id limit 10";

        // 词法分析器
//        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sql));
        SqlBaseLexer lexer = new SqlBaseLexer(new ANTLRNoCaseStringStream(sql));

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        SqlBaseParser.StatementContext statementContext = parser.statement();

        String rawText = statementContext.getText();
        System.out.println("raw text: " + rawText);
        StatementSqlToStringVisitor sqlToStringVisitor = new StatementSqlToStringVisitor();
        String visitorText = sqlToStringVisitor.visit(statementContext).toString();
        System.out.println("visitor to text:" + visitorText);

        // listener walker
        SqlContext listenerSqlContext = new SqlContext();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new StatementSqlListener(listenerSqlContext), statementContext);
        System.out.println("--------- listener source tables ---------");
        listenerSqlContext.getSourceTables().forEach(value -> System.out.println(value));
        System.out.println("--------- listener target tables ---------");
        listenerSqlContext.getTargetTables().forEach(value -> System.out.println(value));

        System.out.println();

        // visitor
        SqlContext visitorSqlContext = new SqlContext();
        StatementSqlVisitor visitor = new StatementSqlVisitor(visitorSqlContext);
        visitor.visit(statementContext);
        System.out.println("--------- visitor source tables ---------");
        visitorSqlContext.getSourceTables().forEach(value -> System.out.println(value));
        System.out.println("--------- visitor target tables ---------");
        visitorSqlContext.getTargetTables().forEach(value -> System.out.println(value));
    }

}
