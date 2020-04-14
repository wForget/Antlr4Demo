package cn.wangz.antlr;

import cn.wangz.antlr.listener.SqlContext;
import cn.wangz.antlr.listener.StatementSqlListener;
import cn.wangz.antlr.parser.SqlBaseLexer;
import cn.wangz.antlr.parser.SqlBaseParser;
import cn.wangz.antlr.stream.ANTLRNoCaseStringStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * @author wang_zh
 * @date 2020/4/14
 */
public class Main {

    public static void main(String[] args) {
        String sql = "insert into in_db.in_tab select * from sc_db1.sc_tab1 a left join sc_db2.sc_tab2 b on a.id2 = b.id limit 10";

        // 词法分析器
//        SqlBaseLexer lexer = new SqlBaseLexer(CharStreams.fromString(sql));
        SqlBaseLexer lexer = new SqlBaseLexer(new ANTLRNoCaseStringStream(sql));

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        SqlContext sqlContext = new SqlContext();
        walker.walk(new StatementSqlListener(sqlContext), parser.statement());

        System.out.println("--------- source tables ---------");
        sqlContext.getSourceTables().forEach(value -> System.out.println(value));
        System.out.println("--------- target tables ---------");
        sqlContext.getTargetTables().forEach(value -> System.out.println(value));
    }

}
