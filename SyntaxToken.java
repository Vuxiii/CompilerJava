public class SyntaxToken {
    
    public final Token type;
    public final int pos;
    public final String text;
    public final Object value;

    public SyntaxToken( Token type, int pos, String text, Object val ) {
        this.pos = pos;
        this.text = text;
        this.type = type;
        this.value = val;
    }
}
