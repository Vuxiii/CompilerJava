import javax.swing.text.Position;

public class Lexer {
    private String text;

    private int pointer = 0;

    private Token currentToken;
    private Object currentVal;


    public Lexer( String text ) {
        
        this.text = text;

    }

    public Token token() { return currentToken; };
    public Object val() { return currentVal; };

    private char peek() {
        return text.charAt( pointer );
    }

    private char current() {
        if ( pointer >= text.length() ) return '\0';
        return text.charAt( pointer );
    }

    private void next() {
        pointer++;
    }

    public SyntaxToken nextToken() {

        if ( !hasNext() ) return new SyntaxToken( Token.EndOfFile, pointer, "\0", null );

        while ( Character.isWhitespace( current() ) ) next();
        
        switch ( current() ) {
            case '+': return new SyntaxToken( Token.OperandPlus, pointer++, "+", null ); 
            case '-': return new SyntaxToken( Token.OperandMinus, pointer++, "-", null ); 
            case '*': return new SyntaxToken( Token.OperandTimes, pointer++, "*", null );  
            case '/': return new SyntaxToken( Token.OperandSlash, pointer++, "/", null );  
            case '(': return new SyntaxToken( Token.OpenParenthesis, pointer++, "(", null ); 
            case ')': return new SyntaxToken( Token.CloseParenthesis, pointer++, ")", null ); 
            case '1': case '2': case '3': case '4': case '5': 
            case '6': case '7': case '8': case '9': case '0': {
                int start = pointer;
                String data = parseNumber();
                return new SyntaxToken( Token.Number, start, data, Integer.parseInt( data ) ); 
            } default: {
                if ( Character.isAlphabetic( current() ) ) {
                    currentToken = Token.String;
                    int start = pointer;
                    String data = parseString();
                    return new SyntaxToken( Token.String, start, data, data );
                }
                else {
                    return new SyntaxToken( Token.BadToken, pointer++, null, null );
                }
            } 
        }
    }

    private String parseNumber() {
        int start = pointer;
        while ( Character.isDigit( current() ) ) next();
        return text.substring( start, pointer );
    }

    private String parseString() {
        int start = pointer;
        while ( Character.isAlphabetic( current() ) ) next();
        return text.substring( start, pointer );
    }

    public boolean hasNext() {
        return pointer < text.length();
    }

}
 