package cn.wangz.antlr.visit;

import cn.wangz.antlr.SqlContext;
import cn.wangz.antlr.parser.SqlBaseBaseVisitor;
import cn.wangz.antlr.parser.SqlBaseParser;

/**
 * @author wang_zh
 * @date 2020/6/1
 */
public class InsertIntoTableSqlVisitor extends SqlBaseBaseVisitor<SqlContext> {

    private SqlContext sqlContext;

    public InsertIntoTableSqlVisitor(SqlContext sqlContext) {
        this.sqlContext = sqlContext;
    }

    @Override
    public SqlContext visitTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
//        return super.visitTableIdentifier(ctx);
        if (ctx.table == null) return this.sqlContext;
        String database = ctx.db != null? ctx.db.getText(): null;
        String table = ctx.table != null? ctx.table.getText(): null;
        sqlContext.addTarget(database, table);
        return this.sqlContext;
    }

}
