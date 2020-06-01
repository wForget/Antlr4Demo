package cn.wangz.antlr.listener;

import cn.wangz.antlr.SqlContext;
import cn.wangz.antlr.parser.SqlBaseBaseListener;
import cn.wangz.antlr.parser.SqlBaseParser;

/**
 * @author wang_zh
 * @date 2020/4/14
 */
public class QuerySpecificationSqlListener extends SqlBaseBaseListener {

    private SqlContext sqlContext;

    public QuerySpecificationSqlListener(SqlContext sqlContext) {
        this.sqlContext = sqlContext;
    }

    @Override
    public void enterTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
        if (ctx.table == null) return;
        String database = ctx.db != null? ctx.db.getText(): null;
        String table = ctx.table != null? ctx.table.getText(): null;
        sqlContext.addSource(database, table);
        super.enterTableIdentifier(ctx);
    }

}
