package cn.wangz.antlr.stream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.IntStream;

/**
 * @author wang_zh
 * @date 2020/4/1
 */
public class ANTLRNoCaseStringStream extends ANTLRInputStream {
    public ANTLRNoCaseStringStream(String input){
        super(input);
    }

    @Override
    public int LA(int i){
        int la = super.LA(i);
        if (la == 0 || la == IntStream.EOF){
            return la;
        } else {
            return Character.toUpperCase(la);
        }
    }
}
