package cn.wangz.antlr.visit;

import cn.wangz.antlr.SqlContext;
import cn.wangz.antlr.parser.SqlBaseBaseVisitor;
import cn.wangz.antlr.parser.SqlBaseParser;

/**
 * @author wang_zh
 * @date 2020/6/1
 */
public class StatementSqlVisitor extends SqlBaseBaseVisitor<SqlContext> {

    private SqlContext sqlContext;

    public StatementSqlVisitor(SqlContext sqlContext) {
        this.sqlContext = sqlContext;
    }

    @Override
    public SqlContext visitQuerySpecification(SqlBaseParser.QuerySpecificationContext ctx) {
//        return super.visitQuerySpecification(ctx);
        return new QuerySpecificationSqlVisitor(this.sqlContext).visit(ctx);
    }

    @Override
    public SqlContext visitInsertIntoTable(SqlBaseParser.InsertIntoTableContext ctx) {
//        return super.visitInsertIntoTable(ctx);
        return new InsertIntoTableSqlVisitor(this.sqlContext).visit(ctx);
    }
}
