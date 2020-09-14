package cn.wangz.antlr.visit;

import cn.wangz.antlr.parser.SqlBaseBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author wang_zh
 * @date 2020/6/1
 */
public class StatementSqlToStringVisitor extends SqlBaseBaseVisitor<StringBuilder> {

    private StringBuilder sb = new StringBuilder();

//    @Override
//    public SqlContext visitChildren(RuleNode node) {
//        if (node != null && node instanceof TerminalNodeImpl) {
//            String text = node.getText();
//            if (text != null && !"".equals(text.trim())) {
//                sb.append(text).append(" ");
//            }
//        }
//        return super.visitChildren(node);
//    }


    @Override
    public StringBuilder visitTerminal(TerminalNode node) {
        String text = node.getText();
        if (text != null && !"".equals(text.trim())) {
            if (shouldAddSpace(text.trim())) {
                sb.append(" ");
            }
            sb.append(text);
        }
        return sb;
    }

    private boolean shouldAddSpace(String text) {
        if (sb.length() == 0) {
            return false;
        }
        char lastChar = sb.charAt(sb.length() - 1);
        switch (lastChar) {
            case '.':
            case '(':
                return false;
            default:
                break;
        }
        switch (text) {
            case ".":
            case ")":
                return false;
            default:
                break;
        }
        return true;
    }

}
