import java.util.Scanner;

public class Repl {
    public static void main( String[] args ) {
        boolean run = true;
        Scanner in = new Scanner( System.in );
        while ( run ) {
            System.out.print( "> " );
            String line = in.nextLine();
            if ( line.equals( "!x" ) ) {
                run = false;
                continue;
            }
            
            Lexer lexer = new Lexer( line );
            while ( lexer.hasNext() ) {
                SyntaxToken t = lexer.nextToken();

                System.out.println( t.value + "\t" + t.type + "\t" + t.pos );

            }


        }

        in.close();
    }
}