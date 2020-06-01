package cn.wangz.antlr.listener;

import cn.wangz.antlr.SqlContext;
import cn.wangz.antlr.parser.SqlBaseBaseListener;
import cn.wangz.antlr.parser.SqlBaseParser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * @author wang_zh
 * @date 2020/4/14
 */
public class StatementSqlListener extends SqlBaseBaseListener {

    private SqlContext sqlContext;

    public StatementSqlListener(SqlContext sqlContext) {
        this.sqlContext = sqlContext;
    }

    @Override
    public void enterQuerySpecification(SqlBaseParser.QuerySpecificationContext ctx) {
        ParseTreeWalker queryWalker = new ParseTreeWalker();
        queryWalker.walk(new QuerySpecificationSqlListener(sqlContext), ctx);
        super.enterQuerySpecification(ctx);
    }

    @Override
    public void enterInsertIntoTable(SqlBaseParser.InsertIntoTableContext ctx) {
        ParseTreeWalker queryWalker = new ParseTreeWalker();
        queryWalker.walk(new InsertIntoTableSqlListener(sqlContext), ctx);
        super.enterInsertIntoTable(ctx);
    }
}
